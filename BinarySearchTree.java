package de.unistuttgart.dsass2024.ex03.p5;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTreeIterable<T> {

    private volatile IBinaryTreeNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public void insert(T t) {
        this.root = this.insert(this.root, t);
    }

    private IBinaryTreeNode<T> insert(IBinaryTreeNode<T> node, T t) {
        if (node == null) {
            IBinaryTreeNode<T> newNode = new BinaryTreeNode<>();
            newNode.setValue(t);
            return newNode;
        }
        if (t.compareTo(node.getValue()) < 0) {
            node.setLeftChild(this.insert(node.getLeftChild(), t));
        } else if (t.compareTo(node.getValue()) > 0) {
            node.setRightChild(this.insert(node.getRightChild(), t));
        }
        return node;
    }

    @Override
    public IBinaryTreeNode<T> getRootNode() {
        return this.root;
    }

    @Override
    public boolean isFull(){
        return this.isFull(this.root);
    }

    /**
     * Check if the tree is full
     *
     * @param node the node to check
     * @return true if the tree is full, false otherwise
     */
    private boolean isFull(IBinaryTreeNode<T> node){
        if (node == null){
            return true;
        }
        if (node.getLeftChild() == null && node.getRightChild() != null){
            return false;
        }
        if (node.getLeftChild() != null && node.getRightChild() == null){
            return false;
        }
        return this.isFull(node.getLeftChild()) && this.isFull(node.getRightChild());
    }

    /**
     * Returns an iterator for the binary search tree.
     *
     * @param traversalType the type of traversal
     * @return an iterator for the binary search tree
     */
    public Iterator<T> iterator(TreeTraversalType traversalType) {
        switch (traversalType) {
            case PREORDER:
                return new PreorderIterator<T>(this);
            case INORDER:
                return new InorderIterator<T>(this);
            case POSTORDER:
                return new PostorderIterator<T>(this);
            case LEVELORDER:
                return new LevelorderIterator<T>(this);
            default:
                throw new IllegalArgumentException("Unknown traversal type");
        }
    }

    /**
     * Preorder iterator for the binary search tree.
     *
     * @return an iterable for the binary search tree
     */
    private static class PreorderIterator<T extends Comparable<T>> implements Iterator<T> {
        Stack<IBinaryTreeNode<T>> stack = new Stack<>();

        public PreorderIterator(BinarySearchTree<T> root) {
            if (root != null) {
                this.stack.push(root.getRootNode());
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public T next() {
            IBinaryTreeNode<T> node = stack.pop();
            T obj = node.getValue();
            if (node.getRightChild() != null) {
                stack.push(node.getRightChild());
            }
            if (node.getLeftChild() != null) {
                stack.push(node.getLeftChild());
            }
            return obj;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Inorder iterator for the binary search tree.
     *
     * @return an iterable for the binary search tree
     */
    private static class InorderIterator<T extends Comparable<T>> implements Iterator<T> {
        Stack<IBinaryTreeNode<T>> stack = new Stack<>();

        public InorderIterator(BinarySearchTree<T> root) {
            IBinaryTreeNode<T> node = root.getRootNode();
            while (node != null) {
                stack.push(node);
                node = node.getLeftChild();
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public T next() {
            IBinaryTreeNode<T> node = stack.pop();
            T obj = node.getValue();
            if (node.getRightChild() != null) {
                node = node.getRightChild();
                while (node != null) {
                    stack.push(node);
                    node = node.getLeftChild();
                }
            }
            return obj;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

/**
     * Postorder iterator for the binary search tree.
     *
     * @return an iterable for the binary search tree
     */
    private static class PostorderIterator<T extends Comparable<T>> implements Iterator<T> {
        Stack<IBinaryTreeNode<T>> stack = new Stack<>();
        Stack<T> output = new Stack<>();

        public PostorderIterator(BinarySearchTree<T> root) {
            if (root != null) {
                this.stack.push(root.getRootNode());
            }
            while (!stack.isEmpty()) {
                IBinaryTreeNode<T> node = stack.pop();
                output.push(node.getValue());
                if (node.getLeftChild() != null) {
                    stack.push(node.getLeftChild());
                }
                if (node.getRightChild() != null) {
                    stack.push(node.getRightChild());
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !this.output.isEmpty();
        }

        @Override
        public T next() {
            return output.pop();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Levelorder iterator for the binary search tree.
     *
     * @return an iterable for the binary search tree
     */
    private static class LevelorderIterator<T extends Comparable<T>> implements Iterator<T> {
        Queue<IBinaryTreeNode<T>> queue = new LinkedList<>();

        public LevelorderIterator(BinarySearchTree<T> root) {
            if (root != null) {
                this.queue.add(root.getRootNode());
            }
        }

        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override
        public T next() {
            IBinaryTreeNode<T> node = queue.poll();
            T obj = node.getValue();
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
            return obj;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}