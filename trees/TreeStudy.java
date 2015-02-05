import java.io.*;
import java.util.Random;

/**
 * Created by Andrew Gable
 *
 * Tree study has a Main method and implementations of Binary Tree and Binary Search Tree. 
 */

public class TreeStudy {

	
	public static void main(String[] args) {
		System.out.println("Binary tree study");

		Node<Integer> leftNode = new Node<Integer>(28);
		BinaryTree<Integer> left = new BinaryTree<Integer>(leftNode);

		Node<Integer> rightNode = new Node<Integer>(52);
		BinaryTree<Integer> right = new BinaryTree<Integer>(rightNode);

		BinaryTree<Integer> tree = new BinaryTree<Integer>(35, left, right);

		System.out.println("Tree in preOrderTraverse:\n" + tree.toString("pre"));

		System.out.println("Tree in inOrderTraverse:\n" + tree.toString("in"));

		System.out.println("Tree in postOrderTraverse:\n" + tree.toString("post"));

		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

		bst.add(new Integer(10));
		bst.add(new Integer(28));
		bst.add(new Integer(52));

		System.out.println("Test find of newly added item (10), it found: " + bst.find(new Integer(10)));

		System.out.println("Test delete of newly added item (52), it deleted: " + bst.delete(new Integer(52)));
		
		System.out.println("Randomly inserting 10 values. . . \n");

		for (int i = 0; i < 10; i++) {
			Random rand = new Random(); 
			Integer value = rand.nextInt(1000); 
			bst.add(value);
		}

		System.out.println("Binary Search Tree: \n" + bst.toString("in"));
	}

	/**
	 * Implementation of Binary Search Tree
	 */
	public static class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

		protected boolean addReturn;
		protected E deleteReturn;

		/**
		 * Find helper method for tree
		 * @param target to find
		 * @return return the found element
		 */
		public E find(E target) {
			return find(root, target);
		}

		/**
		 * Private method to find a node in a tree
		 * @param local root of the tree
		 * @param target to find in the tree
		 * @return returns the element found in the tree
		 */
		private E find(Node<E> localRoot, E target) {
			if (localRoot == null) {
				return null;
			}

			int compareResult = target.compareTo(localRoot.data);
			if (compareResult == 0) {
				// Found it!
				return localRoot.data;
			} else if (compareResult < 0) {
				// Go to left subtree
				return find(localRoot.left, target);
			} else {
				// Go to right subtree
				return find(localRoot.right, target);
			} 
		}

		/**
		 * Add an item to the tree helper method
		 * @param item to add to the tree
		 * @return the item added to the tree
		 */
		public boolean add(E item) {
			root = add(root, item);
			return addReturn;
		}

		/**
		 * Private function to add to a tree
		 * @param local root for the tree
		 * @param item to add to the tree
		 * @return return the node added to the tree
		 */
		private Node<E> add(Node<E> localRoot, E item) {
			if (localRoot == null) {
				// Item is not in the tree
				addReturn = true;
				return new Node<E>(item);
			} else if (item.compareTo(localRoot.data) == 0) {
				// Duplicate item
				addReturn = false;
				return localRoot;
			} else if (item.compareTo(localRoot.data) < 0) {
				// item needs to go left
				localRoot.left = add(localRoot.left, item);
				return localRoot;
			} else {
				// item needs to go right
				localRoot.right = add(localRoot.right, item);
				return localRoot;
			}
		}

		/**
		 * public helper method to remove from the tree
		 * @param target to delete from the tree
		 * @return the element added to the tree
		 */
		public E delete(E target) {
			root = delete(root, target);
			return deleteReturn;
		}

		/**
		 * Private method to remove item from the tree
		 * @param local root of the tree 
		 * @param item to delete from the tree
		 * @return node removed from the tree
		 */
		private Node<E> delete(Node<E> localRoot, E item) {
			if (localRoot == null) {
				// item is not in the tree
				deleteReturn = null;
				return localRoot;
			}

			int compareResult = item.compareTo(localRoot.data);
			if (compareResult < 0) {
				// need to go left
				localRoot.left = delete(localRoot.left, item);
				return localRoot;
			} else if (compareResult > 0) {
				// need to go right
				localRoot.right = delete(localRoot.right, item);
				return localRoot;
			} else {
				// item is at root, we've got some work to do
				deleteReturn = localRoot.data;

				if (localRoot.left == null) {
					// no left, return the right
					return localRoot.right;
				} else if (localRoot.right == null) {
					// no right, return the left
					return localRoot.left;
				} else {
					// Node has two children, we have even more work to do
					if (localRoot.left.right == null){
						// The left child has no right
						localRoot.data = localRoot.left.data;
						localRoot.left = localRoot.left.left;
						return localRoot;
					} else {
						// search for the in order predecessor 
						localRoot.data = findLargestChild(localRoot.left);
						return localRoot;
					}
				}
			}
		}

		/**
		 * Private method to find the largest child, used by delete()
		 * @param parent node to find largest child
		 * @return largest found child
		 */
		private E findLargestChild(Node<E> parent) {
			if (parent.right.right == null) {
				E returnVale = parent.right.data;
				parent.right = parent.right.left;
				return returnVale;
			} else {
				return findLargestChild(parent.right);
			}
		}

	}

	/**
	 * Implementation of Binary Tree
	 */
	public static class BinaryTree<E> implements Serializable {

		protected Node<E> root;

		/**
		 * Empty constructor
		 */
		public BinaryTree() {
			root = null;
		}

		/**
		 * Root only constructor
		 * @param root of tree
		 * @return
		 */
		protected BinaryTree(Node<E> root) {
			this.root = root;
		}

		/**
		 * Constructor that creates a new root with left and right trees
		 * @param item to create binary tree
		 * @param leftTree is the left tree for binary tree
		 * @param rightTree is the right tree for binary tree
		 * @return
		 */
		public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
			root = new Node<E>(data);

			if (leftTree != null) {
				root.left = leftTree.root;
			} else {
				root.left = null;
			}

			if (rightTree != null) {
				root.right = rightTree.root;
			} else {
				root.right = null;
			}
		}

		/**
		 * Get the left subtree 
		 * @return the left subtree as a BinaryTree object
		 */
		public BinaryTree<E> getLeftSubtree() {
			if (root != null && root.left != null) {
				return new BinaryTree<E>(root.left);
			} else {
				return new BinaryTree<E>(null);
			}
		}

		/**
		 * Get the right subtree
		 * @return the right subtree as a BinaryTree object
		 */
		public BinaryTree<E> getRightSubtree() {
			if (root != null && root.right != null) {
				return new BinaryTree<E>(root.right);
			} else {
				return new BinaryTree<E>(null);
			}
		}

		/**
		 * Returns true if root is a leaf
		 * @return yes if root is a leaf
		 */
		public boolean isLeaf() {
			return root == null || (root.left == null && root.right == null);
		}

		/** 
		 * To String of Binary Tree
		 * @param traversalMethod of tree
		 * @return string of tree
		 */
		public String toString(String traversalMethod) {
			StringBuilder stringBulder = new StringBuilder();

			if (traversalMethod.equals("pre")){
				preOrderTraverse(root, 1, stringBulder);
			} else if (traversalMethod.equals("in")) {
				inOrderTraverse(root, 1, stringBulder);
			} else {
				postOrderTraverse(root, 1, stringBulder);
			}
			
			return stringBulder.toString();
		}

		private void preOrderTraverse(Node<E> node, int depth, StringBuilder stringBulder) {
			for (int i = 0; i < depth; i++) {
				stringBulder.append(" ");
			}

			if (node == null){
				stringBulder.append("null\n");
			} else {
				stringBulder.append(node.toString());
				stringBulder.append("\n");
				preOrderTraverse(node.left, depth + 1, stringBulder);
				preOrderTraverse(node.right, depth + 1, stringBulder);
			}
		}

		protected void inOrderTraverse(Node<E> node, int depth, StringBuilder stringBulder) {
			for (int i = 0; i < depth; i++) {
				stringBulder.append(" ");
			}

			if (node == null) {
				stringBulder.append("null\n");
			} else {
				inOrderTraverse(node.left, depth + 1, stringBulder);
				stringBulder.append(node.toString());
				stringBulder.append("\n");
				inOrderTraverse(node.right, depth + 1, stringBulder);
			}
		}

		protected void postOrderTraverse(Node<E> node, int depth, StringBuilder stringBulder) {
			for (int i = 0; i < depth; i++) {
				stringBulder.append(" ");
			}

			if (node == null) {
				stringBulder.append("null\n");
			} else {
				postOrderTraverse(node.left, depth + 1, stringBulder);
				postOrderTraverse(node.right, depth + 1, stringBulder);
				stringBulder.append(node.toString());
				stringBulder.append("\n");
			}
		}
	}

	/**
	 * Node class
	 */
	protected static class Node<E> implements Serializable {
		protected E data;
		protected Node<E> left;
		protected Node<E> right;

		public Node(E data) {
			this.data = data;
			left = null;
			right = null;
		}

		public String toString() {
			return data.toString();
		}
	}

}