package homework1.work7;
import java.util.*;
public class work {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccount a = new BankAccount();
        int n = input.nextInt();
        int m = input.nextInt();
        a.deposit(n);
        a.checkBalance();
        a.withdraw(m);
        a.withdraw(n);
    }
}
