package code;
import java.lang.reflect.Method;
class BankAccount {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("成功存款：" + amount + "，当前余额：" + balance);
        } else {
            System.out.println("存款金额必须大于0");
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class work10 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = BankAccount.class;

        Object accountInstance = clazz.getDeclaredConstructor().newInstance();

        Method depositMethod = clazz.getMethod("deposit", double.class);

        depositMethod.invoke(accountInstance, 1000.0);
        depositMethod.invoke(accountInstance, -500.0);

        // 验证结果（可选）
        Method getBalanceMethod = clazz.getMethod("getBalance");
        double balance = (double) getBalanceMethod.invoke(accountInstance);
        System.out.println("最终余额：" + balance);
    }
}