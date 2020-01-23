package com.erfnse;

public class HuffmanCodingTree {
    CodingTreeNode root;
    CodingTreeNode[] leaves;
    int numberOfLeaves;

    public HuffmanCodingTree(CodingTreeNode root) {
        this.root = root;
        leaves = new CodingTreeNode[getNumberOfLeaves(root)];
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getNumberOfLeaves(CodingTreeNode node){
        if (node == null) return 0;
        if (node.isLeaf()) return 1;
        return getNumberOfLeaves(node.getLeft())+getNumberOfLeaves(node.getRight());
    }

    public CodingTreeNode[] getLeaves(CodingTreeNode node){
        if (node != null){

            if (node.isLeaf()){
                leaves[numberOfLeaves++] = node;
            } else {
                getLeaves(node.getLeft());
                getLeaves(node.getRight());
            }

        }


        return leaves;
    }

    public void labelNodeCodes(CodingTreeNode node) {
        node.generateCode();
        if (!node.isLeaf()) {
            labelNodeCodes(node.getLeft());
            labelNodeCodes(node.getRight());
        }
    }
}
