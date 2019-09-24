import java.io.*;
import java.util.*;

public class AVLTreeNew<E extends Comparable<E>> extends LinkedBinaryTree<E>{
    public class Node<E> {
        private Node<E> left, right, parent;
        private int height = 1;
        private E value;

        private Node(E val) {
            this.value = val;
        }
    }
    private int height (Node<E> N) {
        if (N == null)
            return 0;
        return N.height;
    }

  //  @Override
    protected Node<E> insertRec(Node<E> root, E key){

        /* 1.  Perform the normal BST rotation */
        if (root == null) {
            return(new Node(key));
        }

        if (key.compareTo(root.value) < 0)
            root.left  = insertRec(root.left, key);
        else
            root.right = insertRec(root.right, key);

        /* 2. Update height of this ancestor node */
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether
           this node became unbalanced */
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key.compareTo(root.left.value) < 0)
            return rightRotate(root);

        // Right Right Case
        if (balance < -1 && key.compareTo(root.right.value) > 0)
            return leftRotate(root);

        // Left Right Case
        if (balance > 1 && key.compareTo(root.left.value) > 0)
        {
            root.left =  leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && key.compareTo(root.right.value) < 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        /* return the (unchanged) node pointer */
        return root;
    }



    private Node<E> rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right))+1;
        x.height = Math.max(height(x.left), height(x.right))+1;

        // Return new root
        return x;
    }

    private Node<E> leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right))+1;
        y.height = Math.max(height(y.left), height(y.right))+1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }
}
