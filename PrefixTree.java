package de.unistuttgart.dsass2024.ex05.p2;

public class PrefixTree implements IPrefixTree {
    private IPrefixTreeNode root;
    private int size;

    public PrefixTree() {
        this.root = new PrefixTreeNode();
        this.size = 0;
    }

    @Override
    public void insert(String word) {
        insertRecursively(word, root);
    }

    private void insertRecursively(String word, IPrefixTreeNode node) {
        if(word.isEmpty()){
            if(node.getPrefix().isEmpty()){
                node.setPrefix(word);
                this.size++;
            }
            return;
        }

        char firstChar = word.charAt(0);
        PrefixTreeNode child = (PrefixTreeNode) node.getChild(firstChar);
        if(child == null){
            child = new PrefixTreeNode();
            node.setChild(firstChar, child);
        }
        insertRecursively(word.substring(1), child);
    }

    @Override
    public int size() {
        return this.size;
    }

    public int longestPrefix(String word){
        return longestPrefixRecursively(word, root, 0);
    }

    private int longestPrefixRecursively(String word, IPrefixTreeNode node, int length){
        if(word.isEmpty()){
            return length;
        }

        char firstChar = word.charAt(0);
        IPrefixTreeNode child = node.getChild(firstChar);
        if (child != null){
            return longestPrefixRecursively(word.substring(1), child, length + 1);
        }
        return length;
    }
}