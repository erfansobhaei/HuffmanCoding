package com.erfnse;

public class CodingTreeNode {
    private CodingTreeNode parent, left, right;
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    int number;
    String code;

    public CodingTreeNode(String data, int number){
        this.data = data;
        this.number = number;
    }
    public CodingTreeNode(String data){this(data, 1);}

    public CodingTreeNode getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public String generateCode(){
        String s = "";
        return s;
    }


    public void setParent(CodingTreeNode parent) {
        this.parent = parent;
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

    public static CodingTreeNode combinedNodes(CodingTreeNode firstNode, CodingTreeNode secondNode){
        return new CodingTreeNode(firstNode.getData()+secondNode.getData(), firstNode.getNumber()+secondNode.getNumber());
    }
}
