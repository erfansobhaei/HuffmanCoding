package com.erfnse;

public class HuffmanCodingTree {
    private Node root;
    private Node[] leaves;
    private int numberOfLeaves;


    public HuffmanCodingTree(Node root) {
        this.root = root;
        leaves = new Node[getNumberOfLeaves(root)];
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getNumberOfLeaves(Node node) {
        if (node == null) return 0;
        if (node.isLeaf()) return 1;
        return getNumberOfLeaves(node.getLeft()) + getNumberOfLeaves(node.getRight());
    }

    public Node[] getLeaves(Node node) {
        if (node != null) {

            if (node.isLeaf()) {
                leaves[numberOfLeaves++] = node;
            } else {
                getLeaves(node.getLeft());
                getLeaves(node.getRight());
            }

        }
        return leaves;
    }

    public Node[] getLeaves() {
        return getLeaves(root);
    }

    public void labelNodeCodes(Node node) {
        if (node == null) return;
        node.generateCode();
        if (!node.isLeaf()) {
            labelNodeCodes(node.getLeft());
            labelNodeCodes(node.getRight());
        }
    }

    public boolean isSingleton() {
        return getNumberOfLeaves(root) == 0;
    }

    public Node getRoot() {
        return root;
    }
}
