package com.erfnse;

public class PriorityQueue {
    public Node[] nodes;
    private int size = 0;


    public PriorityQueue(int capacity) {
        nodes = new Node[capacity];
    }

    public void enqueue(Node entry) {
        if (isContains(entry)) {
            int queueNumber = getQueueNumber(entry);
            int place = findPlace(new Node(entry.getData(), queueNumber));
            shiftElementsToLeft(place);
            size--;
            enqueue(new Node(entry.getData(), queueNumber + entry.getNumber()));
        } else {
            int place = findPlace(entry);
            shiftElementsToRight(place);
            nodes[place] = entry;
            size++;
        }

    }

    public Node dequeue() {
        Node result = nodes[0];
        shiftElementsToLeft(0);
        size--;
        return result;
    }
    public Node peek(){
        return nodes[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getQueueNumber(Node entry) {
        for (Node p : nodes) {
            if (p.getData().equals(entry.getData())) {
                return p.getNumber();
            }
        }
        return 1;
    }


    private void updateNumber(Node entry) {
        for (Node p : nodes) {
            if (p == null) break;
            if (entry.getData().equals(p.getData())) {
                p.setNumber(p.getNumber() + 1);
            }
        }
    }

    private boolean isContains(Node entry) {
        for (Node p : nodes) {
            if (p == null) break;
            if (entry.getData().equals(p.getData())) {
                return true;
            }
        }
        return false;
    }

    private int findPlace(Node entry) {
        int place = 0;
        if (size == 0) return 0;


        // Reaching last index of smaller or equal to entry
        while (nodes[place] != null && entry.getNumber() > nodes[place].getNumber()) {
            place++;
        }


        // Reaching index by characters order
        for (int i = place; i < size && entry.getNumber() == nodes[i].getNumber(); i++) {
            if (entry.getData().charAt(0) > nodes[i].getData().charAt(0)) {
                place++;
            }
        }

        return place;

    }

    private void shiftElementsToRight(int place) {

        for (int i = size; i > place; i--) {
            nodes[i] = nodes[i - 1];
        }
    }

    private void shiftElementsToLeft(int place) {

        for (int i = place; i < size-1; i++) {
            nodes[i] = nodes[i + 1];
        }
        nodes[size-1] = null;
    }

    public int getSize() {
        return size;
    }

}