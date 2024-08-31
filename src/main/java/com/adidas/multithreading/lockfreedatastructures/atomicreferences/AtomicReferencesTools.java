package com.adidas.multithreading.lockfreedatastructures.atomicreferences;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicReferencesTools {

    public static void main(String[] args){
        String oldValue = "oldValue";
        String newValue = "newValue";
        AtomicReference<String> atomicReference = new AtomicReference<>(oldValue);

        /**
         *  atomicReference.set("Some unexpected change happened ");
         *
         *  now the idea of the above line, in this example here, we need to understand something that, we commented the above line to
         *  emulate that in real life scenarios involving multiple threads, we need to do something like compareAndSet(expectedValue, newValue)
         *  which means
         *  if in a multi-threaded environment a thread makes some unexpected change to the originalValue then the value
         *  that is expected in the atomicReference above
         *  is not changed as this value changed
         *
         *  the unwanted change in the above is represented by "atomicReference.set("Some unexpected change happened");"
         *
         *
         *  The line `atomicReference.set("Some unexpected change happened");` is commented out to simulate an unexpected change
         *  in a multi-threaded environment. In such scenarios, to safely update the value, you would use `compareAndSet(expectedValue, newValue)`.
         *
         *  This method ensures that the value is only updated if the current value matches `expectedValue`. If another thread
         *  changes the value before your update, `compareAndSet` will fail, preventing the undesired change. The commented-out line
         *  represents such an unwanted modification, illustrating why `compareAndSet` is used to maintain data consistency.
         */


        if(atomicReference.compareAndSet(oldValue, newValue)){
            log.info("New Value is set: {}", atomicReference.get());

        }
        else {
            log.info("Nothing changed");
        }
    }
}
