import java.io.*;

public class BinaryTreeStudy {

	
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
	}


	public static class BinaryTree<E> implements Serializable {

		protected Node<E> root;

		// Empty constructor 
		public BinaryTree() {
			root = null;
		}

		// Root only constructor 
		protected BinaryTree(Node<E> root) {
			this.root = root;
		}

		// Constructor that creates a new root with left and right trees
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

		public BinaryTree<E> getLeftSubtree() {
			if (root != null && root.left != null) {
				return new BinaryTree<E>(root.left);
			} else {
				return new BinaryTree<E>(null);
			}
		}

		public BinaryTree<E> getRightSubtree() {
			if (root != null && root.right != null) {
				return new BinaryTree<E>(root.right);
			} else {
				return new BinaryTree<E>(null);
			}
		}

		public boolean isLeaf() {
			return root == null || (root.left == null && root.right == null);
		}

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