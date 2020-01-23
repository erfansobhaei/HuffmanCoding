package com.erfnse;

import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        PriorityQueue p = new PriorityQueue(5);
        p.enqueue(new CodingTreeNode("d",3));
        p.enqueue(new CodingTreeNode("f",14));
        p.enqueue(new CodingTreeNode("c",7));
        p.enqueue(new CodingTreeNode("a",4));
        CodingTreeNode root = generateHuffmanTree(p);
        HuffmanCodingTree tree = new HuffmanCodingTree(root);
        tree.labelNodeCodes(root);
        CodingTreeNode[] s = tree.getLeaves(root);
        for (CodingTreeNode n: s){
            System.out.println(n.getData()+" "+n.getNumber()+ " " + n.getCode());
        }
    }

    public static CodingTreeNode generateHuffmanTree(PriorityQueue queue) {
        CodingTreeNode root = null;
        while (queue.getSize() > 1) {
            CodingTreeNode leftNode = queue.dequeue();
            CodingTreeNode rightNode = queue.dequeue();
            root = CodingTreeNode.combinedNodes(leftNode, rightNode);

            leftNode.setParent(root);
            rightNode.setParent(root);

            root.setLeft(leftNode);
            root.setRight(rightNode);

            queue.enqueue(root);

        }
        return root;
    }

}