package com.erfnse;

public class CodingTreeNode {

    private CodingTreeNode parent, left, right;
    String data;
    int number;
    String code = "";


    public CodingTreeNode(String data, int number) {
        this.data = data;
        this.number = number;
    }

    public CodingTreeNode(String data) {
        this(data, 1);
    }


    public void generateCode() {
        if (isRoot()) return;
        parent.generateCode();
        if (this.parent.getLeft() == this) {
            code = this.parent.code + "0";
        } else {
            code = this.parent.code + "1";
        }
    }

    public static CodingTreeNode combinedNodes(CodingTreeNode firstNode, CodingTreeNode secondNode) {
        return new CodingTreeNode(firstNode.getData() + secondNode.getData(), firstNode.getNumber() + secondNode.getNumber());
    }

    public boolean isLeaf() {
        return left == null && right == null && parent != null;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public String getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public void setParent(CodingTreeNode parent) {
        this.parent = parent;
    }

    public CodingTreeNode getParent() {
        return parent;
    }

    public CodingTreeNode getLeft() {
        return left;
    }

    public void setLeft(CodingTreeNode left) {
        this.left = left;
    }

    public CodingTreeNode getRight() {
        return right;
    }

    public void setRight(CodingTreeNode right) {
        this.right = right;
    }


}
