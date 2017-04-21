package daysofcode;

public class BST<E extends Comparable<E>> {

	// Tim value roi tra ve vi tri trong mang
	// Neu ko tim duoc thi tra ve -1
	int binarySearch(int arr[], int low, int high, int value) {
		if (high >= low) {
			int mid = low + (high - low) / 2;
			if (value == arr[mid])
				return mid;
			if (value > arr[mid]) // search right
				return binarySearch(arr, (mid + 1), high, value);
			else // search right
				return binarySearch(arr, low, (mid - 1), value);
		}
		return -1;
	}

	// Kiểm tra Binary Tree có là Binary Search Tree
	boolean isBST(Node root) {
		return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	boolean isBSTUtil(Node node, int min, int max) {
		if (node == null) {
			return true;
		}

		if (node.data < min || node.data > max) {
			return false;
		}

		return isBSTUtil(node.left, min, node.data - 1) && isBSTUtil(node.right, node.data + 1, max);
	}
}
