package com.erfnse;

public class Node {

    private Node parent, left, right;
    String data;
    int number;
    String code = "";


    public Node(String data, int number) {
        this.data = data;
        this.number = number;
    }

    public Node(String data) {
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

    public static Node combinedNodes(Node firstNode, Node secondNode) {
        return new Node(firstNode.getData() + secondNode.getData(), firstNode.getNumber() + secondNode.getNumber());
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


    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


}
