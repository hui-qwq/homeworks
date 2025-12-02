package code;
import java.lang.reflect.Method;
class BankAccount {
    private double balance;

    // 存款方法
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("成功存款：" + amount + "，当前余额：" + balance);
        } else {
            System.out.println("存款金额必须大于0");
        }
    }

    // 可选：获取余额的方法（用于验证结果）
    public double getBalance() {
        return balance;
    }
}

public class work10 {
    public static void main(String[] args) throws Exception {
        // 1. 获取 BankAccount 类的 Class 对象
        Class<?> clazz = BankAccount.class;

        // 2. 创建类的实例（若方法非静态，需实例化对象）
        Object accountInstance = clazz.getDeclaredConstructor().newInstance();

        // 3. 获取 deposit 方法对象（参数：方法名 + 参数类型列表）
        Method depositMethod = clazz.getMethod("deposit", double.class);

        // 4. 调用方法（参数：实例对象 + 方法参数值）
        depositMethod.invoke(accountInstance, 1000.0); // 存款1000元
        depositMethod.invoke(accountInstance, -500.0); // 测试无效存款

        // 验证结果（可选）
        Method getBalanceMethod = clazz.getMethod("getBalance");
        double balance = (double) getBalanceMethod.invoke(accountInstance);
        System.out.println("最终余额：" + balance);
    }
}