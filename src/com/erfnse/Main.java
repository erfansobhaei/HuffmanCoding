package com.erfnse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Main {

    static String text;

    public static void main(String[] args) throws IOException {

        System.out.println("1. Compress input text:\n2. Compress .txt file:\n3. Decompress .cmp file:\n4. Exit;");
        int userSelect = new Scanner(System.in).nextInt();
        showUserInterface(userSelect);

    }

    private static void showUserInterface(int userSelect) {
        switch (userSelect) {
            case 1:
                // Compress input text
                System.out.println("Enter your text:");
                text = new Scanner(System.in).next();
                PriorityQueue p = generatePriorityQueue(text);
                HuffmanCodingTree tree = generateHuffmanTree(p);
                try {
                    HuffmanCompressor compressor = new HuffmanCompressor(tree, text, new File("compress.cmp"));
                    if (compressor.compressFile()) {
                        System.out.println("Your text is compressed successfully");
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Something went wrong!");
                }
                break;
            case 2:
                // Compress text of file.txt
                String address = "file.txt";
                try {
                    HuffmanCompressor compressor = new HuffmanCompressor(new File(address), new File("compress.cmp"));
                    if (compressor.compressFile()) {
                        System.out.println("Your text is compressed successfully");
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Something went wrong!");
                }
                break;
            case 3:
                // Decompress compress.cmp
                String destinationAddress = "compress.cmp";
                HuffmanDecompressor decompressor = new HuffmanDecompressor(new File(destinationAddress), new File("decompress.txt"));
                System.out.println(decompressor.decompressFile());
                try {
                    decompressor.decompressFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                return;
            default:
                System.out.println("Your input is not valid!\nPlease try again:");
                showUserInterface(new Scanner(System.in).nextInt());
        }
    }

    public static PriorityQueue generatePriorityQueue(String str) {
        int capacity = countNumberOfCharacters(str);
        PriorityQueue queue = new PriorityQueue(capacity);
        for (int i = 0; i < str.length(); i++) {
            queue.enqueue(new Node(String.valueOf(str.charAt(i))));
        }

        return queue;
    }

    private static int countNumberOfCharacters(String s) {
        // Text consists of ordinary characters
        try {
            int counter = 0;
            while (!s.equals("")) {
                char ch = s.charAt(0);
                s = s.replaceAll(String.valueOf(ch), "");
                counter++;
            }
            return counter;
        }
        // Text include characters like * or ^ or ... that cause of Exception in replaceAll method
        catch (PatternSyntaxException e) {
            int counter = 0;
            while (!s.equals("")) {
                char ch = s.charAt(0);
                String temp = "";
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) != ch) {
                        temp += String.valueOf(s.charAt(i));
                    }
                }
                s = temp;
                counter++;
            }
            System.out.println(counter);
            return counter;
        }
    }

    public static HuffmanCodingTree generateHuffmanTree(PriorityQueue queue) {
        Node root = queue.peek();
        while (queue.getSize() > 1) {
            Node leftNode = queue.dequeue();
            Node rightNode = queue.dequeue();
            root = Node.combinedNodes(leftNode, rightNode);

            leftNode.setParent(root);
            rightNode.setParent(root);

            root.setLeft(leftNode);
            root.setRight(rightNode);

            queue.enqueue(root);

        }
        HuffmanCodingTree tree = new HuffmanCodingTree(root);
        tree.labelNodeCodes(root);
        return tree;
    }

}