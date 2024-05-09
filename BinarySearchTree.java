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

    @Override
    public Iterator<T> iterator(TreeTraversalType traversalType) {
        switch (traversalType) {
            case PREORDER:
                return new PreorderIterator(this.root);
            case INORDER:
                return new InorderIterator(this.root);
            case POSTORDER:
                return new PostorderIterator(this.root);
            case LEVELORDER:
                return new LevelorderIterator(this.root);
            default:
                throw new IllegalArgumentException("Unknown traversal type");
        }
    }

    // Code ist noch nicht fertig, Iteratoren müssen noch implementiert werden
    // Der Code im Kommentar ist nur ein Ansatz
/**
    private class PreorderIterator implements Iterator<T> {
        private Stack<IBinaryTreeNode<T>> stack;

        public PreorderIterator(IBinaryTreeNode<T> root) {
            this.stack = new Stack<>();
            if (root != null) {
                this.stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return null;
        }
    }

    private class InorderIterator implements Iterator<T> {
        private Stack<IBinaryTreeNode<T>> stack;

        public InorderIterator(IBinaryTreeNode<T> root) {
            this.stack = new Stack<>();
            if (root != null) {
                this.stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return null;
        }
    }

    private class PostorderIterator implements Iterator<T> {
        private Stack<IBinaryTreeNode<T>> stack;

        public PostorderIterator(IBinaryTreeNode<T> root) {
            this.stack = new Stack<>();
            if (root != null) {
                this.stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return null;
        }
    }

    private class LevelorderIterator implements Iterator<T> {
        private Queue<IBinaryTreeNode<T>> queue;

        public LevelorderIterator(IBinaryTreeNode<T> root) {
            this.queue = new LinkedList<>();
            if (root != null) {
                this.queue.add(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return null;
        }
    }
        */
}