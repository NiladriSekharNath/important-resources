package com.adidas.dsa.striversde.binarysearch;

/**
 * Problem: Given an array of integers A of size N and an integer B.
 *
 * The College library has N books. The ith book has A[i] number of pages.
 *
 * You have to allocate books to B number of students so that the maximum number of pages allocated to a student is minimum.
 *
 * A book will be allocated to exactly one student.
 * Each student has to be allocated at least one book.
 * Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.
 * Calculate and return that minimum possible number.
 *
 * NOTE: Return -1 if a valid assignment is not possible.
 *
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 *  1 <= A[i], B <= 105
 *
 *
 *
 * Input Format
 * The first argument given is the integer array A.
 * The second argument given is the integer B.
 *
 *
 *
 * Output Format
 * Return that minimum possible number.
 *
 *
 *
 * Example Input
 * Input 1:
 * A = [12, 34, 67, 90]
 * B = 2
 * Input 2:
 * A = [5, 17, 100, 11]
 * B = 4
 *
 *
 * Example Output
 * Output 1:
 * 113
 * Output 2:
 * 100
 *
 *
 * Example Explanation
 * Explanation 1:
 * There are two students. Books can be distributed in following fashion :
 * 1)  [12] and [34, 67, 90]
 *     Max number of pages is allocated to student 2 with 34 + 67 + 90 = 191 pages
 * 2)  [12, 34] and [67, 90]
 *     Max number of pages is allocated to student 2 with 67 + 90 = 157 pages
 * 3)  [12, 34, 67] and [90]
 *     Max number of pages is allocated to student 1 with 12 + 34 + 67 = 113 pages
 *     Of the 3 cases, Option 3 has the minimum pages = 113.
 *
 *
 * Solution Approach : Since here we are tasked to find the max pages a student can be assigned
 *
 * let's find the range,
 * case 1: [12, 34, 67, 90] students = 4
 *
 * each book in books[] has i pages that means book[i], i = 0 has 12 pages,
 *
 * if we try with 1 page max per student no one can get a book
 *
 * if we try with 12 only one student can get a book, but this is not possible as all student must get a book
 *
 * now if we give like this :
 *
 * student1 = 12
 * student2 = 34
 * student3 = 67
 * student4 = 90
 *
 * we find the max number to be from this arrangement is 90, since each student is getting the 90 minimum pages
 *
 * so our low = max(books[]) = 90
 *
 * to understand the high let's take this example :
 *
 * case2: [12, 34, 67, 90] student = 1;
 *
 * now in this arrangement one student gets all the books [12 + 34 + 67 + 90], since all books need to be allocated
 *
 * so high = sum(books[])
 *
 * now here we found a range: [low, high], [max(books[]), sum(books[])]
 *
 *
 * now here with linear search, we can try if any number between low to high, let's say x we try to take the value
 * and calculate the number of students required if maximum of x books can be allocated, here we can try binary
 * search calculate mid between low and high to find the mid and discard either left or either right
 *
 * Let's say x or mid = 100, in our original example:
 *
 * [12, 34, 67, 90], students = 2  low [ 90, 203]
 *
 * we need,
 * student1 = 12 + 34 < 100 (correct allocation)
 * student2 = 67
 * student3 = 90
 *
 * only one student can get this book
 *
 * here we find we need 3 students if the limit is set at 100
 *
 * so we need to increase the limit of 100 so that we can reduce the number of students required:
 *
 *  if (countOfStudentsRequired > students) {
 *           low = mid + 1;
 *  }
 *
 *  again let's say we try with mid = 190 limit
 *
 *  student1 = 12 + 34 + 67 < 190
 *  student2 = 90
 *  max(90, 190) one correct possible answer
 *
 *  so we store this one possible answer in the answer = mid = 190, this is correct answer since we need the lowest valid value
 *
 *  we try with smaller value in high = mid - 1 = 190 - 1 = 189
 *
 *  in the block
 *  else {
 *           ans = mid;
 *           high = mid - 1;
 *         }
 *
 * and we keep continuing
 *
 *
 *
 *
 */
public class AllocateBooks {

    public int books(int[] books, int students) {
      if (students > books.length) return -1;
      int low = Integer.MIN_VALUE, high = 0, ans = 0;
      for (int book : books) {
        low = Math.max(low, book);
        high += book;
      }
      while (low <= high) {
        int mid = low + (high - low) / 2;
        int countOfStudentsRequired = getMinimumStudentsRequired(mid, books);
        if (countOfStudentsRequired > students) {
          low = mid + 1;
        } else {
          ans = mid;
          high = mid - 1;
        }
      }
      return ans ;
    }

    private int getMinimumStudentsRequired(int maxPagesToAllocate, int[] books) {
      int countOfStudents = 1, currentStudentPageCount = 0;
      for (int i = 0; i < books.length; i++) {
        if (currentStudentPageCount + books[i] <= maxPagesToAllocate) {
          currentStudentPageCount += books[i];
        } else if (currentStudentPageCount + books[i] > maxPagesToAllocate) {
          currentStudentPageCount = books[i];
          countOfStudents++;
        }


      }
      return countOfStudents;
    }

  public static void main(String args[]) {
    new AllocateBooks().books(new int[]{12, 34, 67, 90}, 2);
    //new AllocateBooks().books(new int[]{73, 58, 30, 72, 44, 78, 23, 9}, 5);
  }
}
