package com.erfnse;

import java.io.*;

public class HuffmanCompressor {
    HuffmanCodingTree tree;
    String inputText;
    Node[] leaves;
    File outputFile;

    public HuffmanCompressor(HuffmanCodingTree tree, String inputText, File outputFile) {
        this.tree = tree;
        this.outputFile = outputFile;
        leaves = this.tree.getLeaves();
        if (tree.isSingleton()) {
            leaves = new Node[]{tree.getRoot()};
        }
        this.inputText = inputText;
    }

    public boolean compressFile() {
        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));
            writeNodesInFile(inputText, outputStream);
            writeBitsInFile(inputText, outputStream);
            outputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private void writeNodesInFile(String inputText, DataOutputStream outputStream) throws IOException {
        outputStream.write((byte) leaves.length);
        char key;
        short b;
        for (Node node : leaves) {
            key = node.getData().charAt(0);
            b = (short) node.getNumber();
            outputStream.writeChar(key);
            outputStream.writeShort(b);
        }
    }

    private void writeBitsInFile(String inputText, DataOutputStream outputStream) throws IOException {
        int counter = 0;
        byte b = 0;
        short numberOfBytes = (short) (Math.ceil(((double) findCodeLength(inputText) / 8)));
        byte bitsOfLastByte = (byte) (findCodeLength(inputText) % 8);
        if (bitsOfLastByte == 0) {
            bitsOfLastByte = 8;
        }
        outputStream.writeShort(numberOfBytes);
        outputStream.write(bitsOfLastByte);

        for (int i = 0; i < inputText.length(); i++) {
            String code = findHuffmanCode(inputText.charAt(i));

            for (int j = 0; j < code.length(); j++) {

                if (counter == 8) {
                    outputStream.write(b);
                    counter = 0;
                    j--;
                    continue;
                }

                if (counter == 0) {
                    if (code.charAt(j) == '1') {
                        b = 1;
                    } else {
                        b = 0;
                    }
                    b = (byte) (b << 7);
                    counter++;
                    continue;
                }

                byte temp;
                if (code.charAt(j) == '1') {
                    temp = 1;
                } else {
                    temp = 0;
                }
                temp = (byte) (temp << (7 - counter));
                b |= temp;
                counter++;
            }
        }
        if (counter != 0) {
            outputStream.write(b);
        }
    }

    private int findCodeLength(String inputText) {
        int result = 0;
        for (int i = 0; i < inputText.length(); i++) {
            String code = findHuffmanCode(inputText.charAt(i));
            result += code.length();
        }
        return result;
    }

    private String findHuffmanCode(char ch) {
        String key = String.valueOf(ch);
        for (Node leaf : leaves) {
            if (leaf.getData().equals(key)) {
                return leaf.getCode();
            }
        }

        return "";
    }

    public HuffmanCompressor(File inputFile, File outputFile) throws IOException {
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String text = "";
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            text += line;
        }
        this.inputText = text;
        this.outputFile = outputFile;

        PriorityQueue p = Main.generatePriorityQueue(text);
        this.tree = Main.generateHuffmanTree(p);
        leaves = this.tree.getLeaves();

    }
}
