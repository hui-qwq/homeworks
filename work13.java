package code;
import java.util.Scanner;

public class work13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("输入数组长度：");
        int n = sc.nextInt();
        int[] a = new int[n];

        System.out.println("输入数组元素：");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println("逆序输出：");
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(a[i] + " ");
        }

        sc.close();
    }
}