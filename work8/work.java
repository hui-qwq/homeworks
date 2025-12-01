package homework1.work8;
import java.util.Scanner;
public class work {
    public static void main(String[] args) {
        cabinet c = new cabinet();
        Scanner sc = new Scanner(System.in);
        c.list();
        c.add("001", "狗粮", 10);
        c.add("002", "猫粮", 5);
        c.add("003", "大米", 15);
        c.add("004", "鸡肉", 8);
        c.list();
    }
}
