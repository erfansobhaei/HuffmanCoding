package com.erfnse;

public class PriorityQueue {
    public CodingTreeNode[] nodes;
    private int size = 0;


    public PriorityQueue(int capacity) {
        nodes = new CodingTreeNode[capacity];
    }

    public void enqueue(CodingTreeNode entry) {
        if (isContains(entry)) {
            int queueNumber = getQueueNumber(entry);
            int place = findPlace(new CodingTreeNode(entry.getData(), queueNumber));
            shiftElementsToLeft(place);
            size--;
            enqueue(new CodingTreeNode(entry.getData(), queueNumber + entry.getNumber()));
        } else {
            int place = findPlace(entry);
            shiftElementsToRight(place);
            nodes[place] = entry;
            size++;
        }

    }

    public CodingTreeNode dequeue() {
        CodingTreeNode result = nodes[0];
        shiftElementsToLeft(0);
        size--;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getQueueNumber(CodingTreeNode entry) {
        for (CodingTreeNode p : nodes) {
            if (p.getData().equals(entry.getData())) {
                return p.getNumber();
            }
        }
        return 1;
    }


    private void updateNumber(CodingTreeNode entry) {
        for (CodingTreeNode p : nodes) {
            if (p == null) break;
            if (entry.getData().equals(p.getData())) {
                p.setNumber(p.getNumber() + 1);
            }
        }
    }

    private boolean isContains(CodingTreeNode entry) {
        for (CodingTreeNode p : nodes) {
            if (p == null) break;
            if (entry.getData().equals(p.getData())) {
                return true;
            }
        }
        return false;
    }

    private int findPlace(CodingTreeNode entry) {
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

        for (int i = place; i < size; i++) {
            nodes[i] = nodes[i + 1];
        }
    }

    public int getSize() {
        return size;
    }

}