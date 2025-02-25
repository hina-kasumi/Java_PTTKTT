package org.hina.buoi6.file;

import java.io.File;
import java.util.Scanner;

import org.hina.buoi5.RedBlackBST;

public class FileFrequencyIndex {
    private RedBlackBST<String, RedBlackBST<File, Integer>> indexWord;

    public FileFrequencyIndex() {
        indexWord = new RedBlackBST<>();
    }

    public void addFile(String string) {
        try {
            File file = new File(string);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                for (String xString : scanner.nextLine().split(" ")) {
                    addWord(xString, file);
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addWord(String word, File file) {
        var innerST = indexWord.get(word);
        if (innerST == null) {
            RedBlackBST<File, Integer> st = new RedBlackBST<>();
            st.put(file, 1);
            indexWord.put(word, st);
        } else {
            boolean contains = innerST.contains(file);
            if (contains) {
                innerST.put(file, innerST.get(file) + 1);
            } else {
                innerST.put(file, 1);
            }
        }
    }

    private void get(String word) {
        var bst = indexWord.get(word);
        System.out.println(word);
        for (Object o : bst.keys()) {
            File file = (File) o;
            System.out.println(file.getName() + " " + bst.get(file));
        }
    }

    public static void main(String[] args) {
        FileFrequencyIndex fIndex = new FileFrequencyIndex();
        String src = "src/main/java/org/hina/buoi6/file/";
        for (int i = 1; i <= 4; i++) {
            fIndex.addFile(src + "ex" + i + ".txt");
        }
        fIndex.get("was");
    }
}
