package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class work15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();

        System.out.println("输入数字（以非数字结束）：");
        while (sc.hasNextInt()) {
            a.add(sc.nextInt());
        }

        HashSet<Integer> s = new HashSet<>(a);
        ArrayList<Integer> r = new ArrayList<>(s);

        System.out.println("去重后：" + r);
        sc.close();
    }
}