package homework1.work9;
import java.util.Scanner;
class NegativeNumberException extends Exception {
    public NegativeNumberException() {
        super("输入错误：不能输入负数！");
    }

    public NegativeNumberException(String message) {
        super(message);
    }
}

public class NumberValidator {
    public static void checkPositiveNumber(int number) throws NegativeNumberException {
        if (number < 0) {
            throw new NegativeNumberException("输入的数字 " + number + " 是负数，不符合要求！");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("请输入一个正数：");
            int input = sc.nextInt();

            checkPositiveNumber(input);

            System.out.println("输入合法：" + input);
        } catch (NegativeNumberException e) {
            System.out.println("异常信息：" + e.getMessage());
        } catch (Exception e) {
            System.out.println("输入错误：请输入有效的整数！");
        } finally {
            sc.close();
        }
    }
}

