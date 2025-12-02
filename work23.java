package code;

import java.util.Scanner;

public class work23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("输入数组长度：");
        int n = sc.nextInt();
        int[] a = new int[n];

        System.out.println("输入数组元素：");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        b(a);

        System.out.println("排序后：");
        for (int num : a) {
            System.out.print(num + " ");
        }

        sc.close();
    }

    static void b(int[] a) {
        int l = a.length;
        for (int i = 0; i < l - 1; i++) {
            boolean f = false;
            for (int j = 0; j < l - 1 - i; j++) {
                if (a[j] > a[j+1]) {
                    int t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                    f = true;
                }
            }
            if (!f) break;
        }
    }
}