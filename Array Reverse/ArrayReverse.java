// CSC 231 Computer Science and Programming II
// Reversing array elements

public class ArrayReverse {
   public static void main(String[] args) {
      int[] elements = {10, 20, 30, 40, 50, 60, 70, 80}; // Array elements

      System.out.print("\nOriginal elements: ");
      for (int i = 0; i < elements.length; ++i) {
    	 System.out.print(elements[i] + " ");
       }

      // Reverse array's elements
      for (int i = 0; i < elements.length; ++i) {
    	  elements[i] = elements[elements.length - i -1]; // Swap elements
      }

      // Print numbers
      System.out.print("\nNew elements: ");
      for (int i = 0; i < elements.length; ++i) {
         System.out.print(elements[i] + " ");
      }
   }
}