package com.erfnse;

public class Node {

    private String data;
    private int number;

    public Node(String data, int number){
        this.data = data;
        this.number = number;
    }

    public Node(String data) {
        this(data,1);
    }

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
}
