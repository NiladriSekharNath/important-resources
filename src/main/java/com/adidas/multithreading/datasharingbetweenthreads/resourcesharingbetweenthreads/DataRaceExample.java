package com.adidas.multithreading.datasharingbetweenthreads.resourcesharingbetweenthreads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataRaceExample {
    /**
     *
     * There are data races happening here that will cause problems unless it is handled accurately
     *
     * The reason it is happening is that compilers and CPU reorder instructions to make our code efficient so in order to save performance
     * time which is important for us
     *
     * Also compilers does not cause problems or reorder instructions in these scenarios as shown below for our understanding
     * here
     *  x = 10
     *  y = x + 2
     *  z = y + 10 CPU does not reorder this kind of instructions here as there are dependencies from one to another
     *
     *  but in these below example here if we see something like this,
     *
     *  public void increment(){
     *             x++;
     *             y++;
     *   }
     *
     *   Also this can happen and for the compiler and the CPU these instructions are same as
     *   in the below order
     *
     *   public void increment(){
     *                  y++;
     *                  x++;
     *   }
     *
     *  Now this can happen in this order 1 -> 2 -> 3 -> 4
     *
     *  checkForDataRace()            increment()
     *
     *                               1. y++
     * 2. y = 1
     * 3. x = 0
     *                               4. x++
     *
     *
     *   Now in this case when the Visualization thread reads the
     *
     *   y > x and there it is happening Data Race
     *
     *   One solution to this approach is work is declaration of the shared variables ( in our case x, y) with the keyword
     *   "volatile" this will guarantee the order
     *
     *   like this example here
     *
     *  <code> class Testing {
     *       private volatile int sharedVariable ;
     *       public void testingMethod(){
     *
     *           ... // All instructions will be executed before
     *
     *           read/write(sharedVariable) ;
     *
     *           ... // All instructions will be executed after --> this does not work properly but the previous one does
     *       }
     *
     *   } </code>
     *
     *   Also one more interesting analysis here if in the above point "all instructions will be executed before" and
     *   from the Oracle Java Docs
     *
     *   A Write to a volatile variable happens before any subsequent number of reads from it in the method()
     *
     *   so in one case if we do this :
     *
     *   private volatile int x = 0;
     *   public void someMethod(){
     *       x++;                        --- instruction 1
     *       read(x);                    --- instruction 2
     *
     *   }
     *
     *   this ordering won't be interchanged but if we do something like this in the following example below
     *
     *
     *   private static class Testing{
     *
     *
     *         private int x = 0 ;
     *         private volatile int y = 0;
     *
     *
     *
     *         public void increment(){
     *             x++;
     *             y++;
     *
     *         }
     *
     *         public void checkForDataRace(){
     *             if(x<y) // (y>x)
     *                 log.info("Data Race is ocurring: ");
     *         }
     *     }
     *
     *     Now in the above method checkForDataRace() reading of x can be done by another Thread before writing of another Thread
     *
     *     Also for this case data race is happening :
     *
     *     Scenario if two Threads in our case incrementingThread(let's name this A)
     *     and visualizationThread(let's name this B) which are running concurrently,
     *     tries to access the shared variable x currently both x = 0 and y = 0
     *
     *     ThreadA starts incrementing using the increment() method before it completes incrementing, ThreadB gets scheduled
     *     on the CPU and ThreadB reads x = 0 and when it tries to read y, again ThreadA get Scheduled which is
     *     ThreadA increments y
     *     making it y = 1 and so x < y -> 0 < 1 so it holds and we get dataRace
     *
     *     but we don't get datarace in this case if (y>x) in the comments in line 99 because of the order of operations
     *     x++ will happen before y++ as y is volatile so even if y does not complete before x (incrementing )
     *
     *     Case 1: If y does not complete so y = 0 and x if completed x = 0
     *     Case 2: If x does not complete so the order in which the comparisions operator is read from left to right
     *              which means the x < y then it reads x first and then y
     *              x = 0, y = 0 so no race condition
     *
     *     Understanding the Order of Operations:
     *          increment() method: Always increments x before y.
     *          Both comparisons: Read y and x in the same order. if( y > x ) read y first then x
     *
     *
     */

    public static void main(String args[]){
        Testing testing = new Testing();
        Thread incrementingThread = new Thread(() -> {
            for(int i = 0 ; i<Integer.MAX_VALUE; i++)
                testing.increment();
        });
        Thread visualizationThread = new Thread(() -> {
            for(int i = 0 ; i<Integer.MAX_VALUE; i++)
            testing.checkForDataRace();
        });
        incrementingThread.start();
        visualizationThread.start();
    }
    private static class Testing{


        private volatile int x = 0 ;
        private volatile int y = 0;



        public void increment(){
            x++;
            y++;

        }

        public void checkForDataRace(){
            if(y>x) /* (x<y) */
                log.info("Data Race is ocurring: ");
        }
    }
}
