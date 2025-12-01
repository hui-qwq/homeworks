package homework1;

import java.util.Scanner;

public class work6
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char c =  sc.next().charAt(0);
        int m = sc.nextInt();

        switch(c) {
            case '+':
                System.out.println(n+m);
                break;
            case '*':
                System.out.println(n*m);
                break;
            case '-' :
                System.out.println(n-m);
                break;
            case '/':
                System.out.println(n/m);
                break;
            default:
        }
    }
}
