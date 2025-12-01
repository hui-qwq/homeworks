package homework1.work7;

public class BankAccount {
    private double money;

    public BankAccount() {}
    public BankAccount(double money) {
        this.money = money;
    }

    public void deposit(double money) {
        this.money += money;
        System.out.println("已存入 " + money);
    }

    public void withdraw(double money) {
        this.money -= money;
        if(this.money < 0) {
            System.out.println("余额不足");
        }else System.out.println("已取出" + money);
    }

    public void checkBalance() {
        System.out.println("当前余额 : " + this.money);
    }

}
