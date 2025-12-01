package homework1;
import java.util.Scanner;
public class work5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if((n % 4 == 0 && n % 100 != 0) ||  n % 400 == 0) {
            System.out.printf("%d 年是闰年\n", n);
        }else System.out.printf("%d 年是不闰年\n", n);
    }
}
