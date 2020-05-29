
public class LCArrayMethods {
	public static int getLength(int[] arr) {
		return arr.length;
	}
	public static void addFirst(int[] arr, int val) {
		arr[0] = val;
	}
	public static void addLast(int[] arr, int val) {
		arr[arr.length - 1] = val;
	}
	public static void add(int[] arr, int val, int index) {
		arr[index] = val;
	}
	public static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	public static void delete(int[] arr, int index) {
		arr[index] = -1;
	}
	public static void deleteLast(int[] arr) {
		arr[arr.length - 1] = -1;
	}
	public static void deleteFirst(int[] arr) {
		arr[0] = -1;
	}
}
