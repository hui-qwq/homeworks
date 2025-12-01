package homework1;
import java.util.Scanner;
public class work3 {
    public static final double PI = 3.1415926535;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double r = sc.nextDouble();

        System.out.printf("半径为%.2f的圆的半径为%.2f\n", r, PI * r * r);
    }
}
