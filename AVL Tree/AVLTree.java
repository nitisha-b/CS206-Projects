import java.util.*;
import java.io.*;

public class AVLTree<E extends Comparable <E>> extends LinkedBinaryTree<E> {

	public AVLTree() {
		super();
	}

	public Node<E> rotateRight(Node<E> node){
		if(node.getLeft() ==  null) return node;

		Node<E> actpivot = new Node(node.getLeft().getElement(), null, null, null);
		Node<E> pivot = node.getLeft();
		Node<E> pivotLeft = pivot.getLeft();

		node.setLeft(pivot.getRight());
		actpivot.setRight(node);
		actpivot.setLeft(pivotLeft);

		if(node.getParent()==null){
			root = actpivot;
		}
		else{
			if(node.getParent().getLeft() == node){
				node.getParent().setLeft(actpivot);
			}
			else{
				node.getParent().setRight(actpivot);
			}
		}
		return actpivot;
	}

	public Node<E> rotateLeft(Node<E> node){
		if(node.getRight() == null) return node;

		Node<E> actpivot = new Node(node.getRight().getElement(), null, null, null);
		Node<E> pivot = node.getRight();
		Node<E> pivotRight = pivot.getRight();

		node.setRight(pivot.getLeft());
		actpivot.setLeft(node);
		actpivot.setRight(pivotRight);

		if(node.getParent()==null){
			root = actpivot;
		}
		else{
			if(node.getParent().getRight() == node){
				node.getParent().setRight(actpivot);
			}
			else{
				node.getParent().setLeft(actpivot);
			}
		}
		return actpivot;
	}

	public int height(Node<E> node){
	    if(node == null) return 0;
	    else {
	      return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
	    }
	}

	public int balanceFactor(Node<E> node){
    //System.out.println(node == null);

	    if(node == null) return 0;
	    else{
	      int balF = height(node.getLeft()) - height(node.getRight());
	      //System.out.println(balF);
	      return balF;
	    }
	}

	@Override
	public Node<E> insertRec(Node<E> node, E key){
		if(node == null){
				node = new Node(key, null, null, null);
				return node;
		}


		if(node.getElement().compareTo(key) > 0){


			node.setLeft(insertRec(node.getLeft(), key));
			node.getLeft().setParent(node);
		}
		else if(node.getElement().compareTo(key) < 0) {

			node.setRight(insertRec(node.getRight(), key));
			node.getRight().setParent(node);
		}
		else{
				node.setElement(key);
		}
		height(node); // = 1 + Math.max(height(root.left), height(root.right));

		int blcFactor = balanceFactor(node);

		if(blcFactor > 1) {

			//left-right rotate --> left first, followed by right
			if(balanceFactor(node.getLeft()) < 0){

				node.setLeft(rotateLeft(node.getLeft()));
				return rotateRight(node);
			}

			//right-right Rotate -> unbalanced on the right side
			else {
				return rotateRight(node);
			}

		}

		else if(blcFactor < -1) {

			//right-left rotate --> first right then left
			if(balanceFactor(node.getRight()) > 0){
				//System.out.println("right-left");
				//System.out.println(rotateRight(node.getRight()));
				node.setRight(rotateRight(node.getRight()));
				return rotateLeft(node);
			}

			//left-left unbalanced --> rotate left
			else {

				return rotateLeft(node);
			}
		}

		return node;
	}

	@Override
	public void insert(E element){
		 root = insertRec(root, element);
			size++;
	}



}
