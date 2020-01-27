package com.erfnse;

import java.io.*;

public class HuffmanDecompressor {
    File inputFile, outputFile;
    FileWriter fileWriter;
    HuffmanCodingTree tree;
    static String code = "";
    Node[] nodes;

    public HuffmanDecompressor(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public boolean decompressFile() {
        try {
            fileWriter = new FileWriter(outputFile);
            DataInputStream outputStream = new DataInputStream(new FileInputStream(inputFile));


            // Read nodes information
            byte numberOfNodes = (byte) outputStream.read();
            PriorityQueue queue = new PriorityQueue(numberOfNodes);
            for (int i = 0; i < numberOfNodes; i++) {
                System.out.println();
                String key = String.valueOf(outputStream.readChar());
                short number = outputStream.readShort();
                queue.enqueue(new Node(key, number));

            }
            tree = Main.generateHuffmanTree(queue);
            nodes = tree.getLeaves();


            short numberOfBytes = outputStream.readShort();
            byte numberBitsOfLastByte = (byte) outputStream.read();

            // Decode byte by byte

            for (int i = 0; i < numberOfBytes - 1; i++) {
                byte b = (byte) outputStream.read();
                writeCharsInFile(b, 8);
            }

            // Decode last byte
            byte b = (byte) outputStream.read();
            writeCharsInFile(b, numberBitsOfLastByte);

            fileWriter.close();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void writeCharsInFile(byte b, int numberOfBits) throws IOException {
        for (int j = 7; j >= 8 - numberOfBits; j--) {
            byte mask = (byte) Math.pow(2, j);
            mask = (byte) (b & mask);
            if (mask == 0) {
                code += "0";
            } else {
                code += "1";
            }
            for (Node node : nodes) {
                if (node.getCode().equals(code)) {
                    System.out.println(node.getData() + "  " + node.getCode());
                    fileWriter.write(node.getData());
                    code = "";
                    break;
                }
            }
        }
    }
}
