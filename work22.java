package code;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class work22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("输入字符串：");
        String s = sc.nextLine();
        sc.close();

        Map<Character, Integer> m = new HashMap<>();

        for (char c : s.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> e : m.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}