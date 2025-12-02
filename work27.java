package code;

import java.util.Scanner;
import java.util.TreeSet;

public class work27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<Integer> t = new TreeSet<>();

        System.out.println("输入整数（输入非整数结束）：");
        while (sc.hasNextInt()) {
            t.add(sc.nextInt());
        }

        System.out.println("升序排列：" + t);
        sc.close();
    }
}