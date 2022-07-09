package com.hcmus.slangword;

import java.io.*;
import java.util.*;

class Dictionary {
    public String word;
    public String meaning;
}

public class Main {
    static HashMap<String, Dictionary> slangKey = new HashMap<String, Dictionary>();
    static List<Dictionary> historySearch = new ArrayList<Dictionary>();
    public static void loadData(String filename) throws IOException {
        FileInputStream readFile = new FileInputStream(filename);
        BufferedReader fileData = new BufferedReader(new InputStreamReader(readFile));
        for (int i = 0; fileData.readLine() != null; i++) {
            String dataRead = fileData.readLine();
            String[] parts = dataRead.split("`");
            Dictionary dict = new Dictionary();
            try {
                dict.word = parts[0];
                dict.meaning = parts[1];
                slangKey.put(dict.word, dict);
            } catch (Exception e) {
            }
        }
        readFile.close();
    }

    public static Dictionary randomWord() {
        Set<String> keys = slangKey.keySet();
        int index = (int) (Math.random() * keys.size());
        String key = (String) keys.toArray()[index];
        Dictionary dict = slangKey.get(key);
        return dict;
    }

    private static void handleSearchSlangWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập slang word: ");
        String slang = scanner.next();
        Dictionary dict = slangKey.get(slang);
        if (dict == null) {
            System.out.println("Không có từ này!");
        } else {
            System.out.println(dict.word + " nghĩa là: " + dict.meaning);
            historySearch.add(dict);
        }
    }
    private static void handleSearchDefinition() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập meaning word: ");
        String meaning = scanner.nextLine();
        Set<String> keys = slangKey.keySet();
        for (String key : keys) {
            Dictionary dict = slangKey.get(key);
            if (dict.meaning.equals(meaning)) {
                System.out.println(key + ": " + dict.meaning);
                historySearch.add(dict);
            }
        }
    }

    private static void handleHistory() {
        System.out.println("Lịch sử slang word đã tìm kiếm:");
        for (Dictionary dict : historySearch) {
            System.out.println(dict.word + ": " + dict.meaning);
        }
    }

    private static void handleAddWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập slang word: ");
        String slang = scanner.next();
        scanner = new Scanner(System.in);
        System.out.print("Nhập meaning word: ");
        String meaning = scanner.nextLine();
        Dictionary dict = new Dictionary();
        dict.word = slang;
        dict.meaning = meaning;
        slangKey.put(slang, dict);
        System.out.println("Thêm thành công");
    }

    private static void handleEditWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập slang word: ");
        String slang = scanner.next();
        Dictionary dict = slangKey.get(slang);
        if (dict == null) {
            System.out.println("Không có từ này");
        } else {
            System.out.print("Nhập meaning word: ");
            scanner = new Scanner(System.in);
            String meaning = scanner.nextLine();
            dict.meaning = meaning;
            System.out.println("Chỉnh sửa thành công");
        }
    }

    private static void handleDeleteWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập slang word cần xoá: ");
        String slang = scanner.next();
        Dictionary dict = slangKey.get(slang);
        if (dict == null) {
            System.out.println("Không có từ này");
        } else {
            System.out.print("Bạn thật sự muốn xoá? (y/n): ");
            String confirm = scanner.next();
            if (confirm.equals("y")) {
                slangKey.remove(slang);
                System.out.println("Xoá thành công");
            }
        }
    }

    private static void handleReset() {
        try {
            slangKey.clear();
            historySearch.clear();
            loadData("slang.txt");
            System.out.println("Reset thành công");
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleRandom() {
        System.out.print("Kết quả random => ");
        Set<String> keys = slangKey.keySet();
        int index = (int) (Math.random() * keys.size());
        String key = (String) keys.toArray()[index];
        Dictionary dict = slangKey.get(key);
        System.out.println(dict.word + ": " + dict.meaning);
        historySearch.add(dict);
    }
    private static void handleMiniGame() {
        Scanner scanner = new Scanner(System.in);
        Set<String> keys = slangKey.keySet();
        int index = (int) (Math.random() * keys.size());
        String key = (String) keys.toArray()[index];
        Dictionary dict = slangKey.get(key);
        System.out.println("Slang: " + dict.word);
        System.out.println("Meaning: ");
        int rightAnswer = index % 4;
        for (int i = 0; i < 4; i++) {
            if (i == rightAnswer) {
                System.out.println(i + ". " + dict.meaning);
            } else {
                System.out.println(i + ". " + randomWord().meaning);
            }
        }

        System.out.print("Trả lời: ");
        int answer = scanner.nextInt();
        if (answer == rightAnswer) {
            System.out.println("Chính xác!!!!");
        } else {
            System.out.println("Toang! sai rồi");
        }
    }

    private static void handleDefinitionMiniGame() {
        Scanner scanner = new Scanner(System.in);
        Set<String> keys = slangKey.keySet();
        int index = (int) (Math.random() * keys.size());
        String key = (String) keys.toArray()[index];
        Dictionary dict = slangKey.get(key);
        System.out.println("Meaning: " + dict.meaning + " Slang là gì?: ");
        int rightAnswer = index % 4;
        for (int i = 0; i < 4; i++) {
            if (i == rightAnswer) {
                System.out.println(i + ". " + dict.word);
            } else {
                System.out.println(i + ". " + randomWord().word);
            }
        }
        System.out.print("Trả lời: ");
        int answer = scanner.nextInt();
        if (answer == rightAnswer) {
            System.out.println("Chính xác!!!!");
        } else {
            System.out.println("Toang! sai rồi");
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
                case 4 -> handleAddWord();
                case 5 -> handleEditWord();
                case 6 -> handleDeleteWord();
                case 7 -> handleReset();
                case 8 -> handleRandom();
                case 9 -> handleMiniGame();
                case 10 -> handleDefinitionMiniGame();
            }
            System.out.println("Nhấn Enter để tiếp tục!");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }
    }
}