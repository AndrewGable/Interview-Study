/**
 * Created by Andrew Gable
 *
 * Study for recurisve methods. Includes print string, gcd, Linear Search and Binary Search recursive methods
 */

public class Recursion {
	
	public static void main(String[] args) {
		System.out.println("First method: Print string char by char with new line");
		printString("Andrew");

		System.out.println("\nRecursive gcd method. . .");
		int gcdOne = gcd(15, 5);
		System.out.println("Greatest common denominator of 15 and 5 is " + gcdOne);

		System.out.println("\nLinear search using recursion");
		int[] items = {1, 5, 6, 7, 8, 34, 89, 2, 28, 4};
		int foundItem = linearSearch(items, 28, 0);
		System.out.println("Want to find 28 and I found " + foundItem);

		System.out.println("\nBinary search using recursion");
		int[] binaryItems = {1, 4, 7, 20, 21, 23, 24, 25, 28, 40, 52, 400, 3000};
		int found = binarySearch(binaryItems, 52, 0, binaryItems.length - 1);
		System.out.println("Want to find 52 and I found " + found);		 

	}

	/**
	 * Create a recursive method that prints out a string line by line
	 * @param stringToPrint string you want to print
	 */
	private static void printString(String stringToPrint) {
		
		// Create our base case
		if (stringToPrint == null || stringToPrint.equals("")) {
			return;
		} else {
			System.out.println(stringToPrint.charAt(0));
			printString(stringToPrint.substring(1));
		}
	}

	/**
	 * Find the greatest common denominator recursively 
	 * @param  m first number
	 * @param  n second number
	 * @return   greatest common denominator of m and n
	 */
	private static int gcd(int m, int n) {
		if (m % n == 0) {
			return n;
		} else if (m < n) {
			return gcd(n, m); // User put them in wrong order, switch them
		} else {
			return gcd(n, m % n);
		}
	}

	/**
	 * Create a linear search algorithm that recursively calls it self
	 * This is the brute force method, and takes O(n) time, and O(1) space worst case
	 * @param  items         items to search through (int array)
	 * @param  itemToFind    item you want to find in array
	 * @param  firstPosition position to start searching at (0)
	 * @return               the item found or not (-1)
	 */
	private static int linearSearch(int[] items, int itemToFind, int firstPosition) {
		if (firstPosition == items.length) {
			return -1;
		} else if (items[firstPosition] == itemToFind) {
			return items[firstPosition];
		} else 
			return linearSearch(items, itemToFind, firstPosition + 1);
	}

	/**
	 * Improve on linear search with a binary search recursive method
	 * The array to be searched must be sorted! 
	 * This searching method takes O(log n) time and O(1) space
	 * @param  items  items to search through (sorted!)
	 * @param  target item to find
	 * @param  first  first index of items (0)
	 * @param  last   last index of items (length - 1)
	 * @return        item found
	 */
	private static int binarySearch(int[] items, int target, int first, int last) {

		if (first > last) {
			return -1;
		} else {
			int middle = (first + last) / 2;
			int compareResult = Integer.compare(target, items[middle]);

			if (compareResult == 0) {
				// We found the item, return it!
				return items[middle];
			} else if (compareResult < 0) {
				// If target is less than the middle
				return binarySearch(items, target, first, middle - 1);
			} else {
				// If target is more than the middle
				return binarySearch(items, target, middle + 1, last);
			}
		}

	}
}