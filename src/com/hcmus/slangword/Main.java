package com.hcmus.slangword;

import java.io.*;
import java.util.*;

class Pair {
    public String word;
    public String meaning;
}

public class Main {
    static HashMap<String, Pair> slangKey = new HashMap<String, Pair>();
    static List<Pair> history = new ArrayList<Pair>();
    static List<Pair> listSorted = new ArrayList<Pair>();

    private static void handleSearchSlangWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập slang word: ");
        String slang = scanner.next();
        Pair pair = slangKey.get(slang);
        if (pair == null) {
            System.out.println("Không có từ này!");
        } else {
            System.out.println(pair.word + " nghĩa là: " + pair.meaning);
            history.add(pair);
        }
    }
    private static void handleSearchDefinition() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập meaning word: ");
        String meaning = scanner.nextLine();
        Set<String> keys = slangKey.keySet();
        for (String key : keys) {
            Pair pair = slangKey.get(key);
            if (pair.meaning.equals(meaning)) {
                System.out.println(key + ": " + pair.meaning);
                history.add(pair);
            }
        }
    }

    private static void handleHistory() {
        System.out.println("Lịch sử slang word đã tìm kiếm:");
        for (Pair pair : history) {
            System.out.println(pair.word + ": " + pair.meaning);
        }
    }
    public static void main(String[] args) throws IOException {
        String filename="slang.txt";

        loadData(filename);
        int choose = -1;
        while (choose != 11) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Chọn chức năng:");
            System.out.println("1. Chức năng tìm kiếm theo slang word");
            System.out.println("2. Chức năng tìm kiếm theo definition");
            System.out.println("3. Chức năng hiển thị history, danh sách các slang word đã tìm kiếm.");
            System.out.println("4. Chức năng add 1 slang words mới.");
            System.out.println("5. Chức năng edit 1 slang word.");
            System.out.println("6. Chức năng delete 1 slang word. Confirm trước khi xoá");
            System.out.println("7. Chức năng reset danh sách slang words gốc;");
            System.out.println("8. Chức năng random 1 slang word");
            System.out.println("9. Chức năng đố vui, Slang -> Meaning");
            System.out.println("10. Chức năng đố vui, Meaning -> Slang");
            System.out.println("11. Thoát");
            System.out.println("--------------------------------------------------------------------");
            System.out.print("=> Chọn: ");
            Scanner scanner = new Scanner(System.in);
            choose = scanner.nextInt();

            switch (choose) {
                case 1 -> handleSearchSlangWord();
                case 2 -> handleSearchDefinition();
                case 3 -> handleHistory();
            }
            System.out.println("Nhấn Enter để tiếp tục!");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }

    }

    public static void loadData(String filename) throws IOException {
        FileInputStream readFile = new FileInputStream(filename);
        BufferedReader fileData = new BufferedReader(new InputStreamReader(readFile));
        for (int i = 0; fileData.readLine() != null; i++) {
            String dataRead = fileData.readLine();
            String[] parts = dataRead.split("`");
            Pair pair = new Pair();
            try {
                pair.word = parts[0];
                pair.meaning = parts[1];
                slangKey.put(pair.word, pair);
                listSorted.add(pair);
            } catch (Exception e) {
            }
        }
        readFile.close();
    }

    public static Pair randomWord() {
        Set<String> keys = slangKey.keySet();
        int index = (int) (Math.random() * keys.size());
        String key = (String) keys.toArray()[index];
        Pair pair = slangKey.get(key);
        return pair;
    }
}