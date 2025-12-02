package code;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class work30 {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== 员工工资管理系统 ======");
            System.out.println("1. 添加普通员工");
            System.out.println("2. 添加管理层员工");
            System.out.println("3. 查看所有员工信息");
            System.out.println("4. 计算所有员工工资");
            System.out.println("5. 按部门筛选员工");
            System.out.println("6. 退出系统");
            System.out.print("请输入操作选项：");

            int option = sc.nextInt();
            sc.nextLine(); // 处理换行符

            switch (option) {
                case 1:
                    addRegularEmployee(manager, sc);
                    break;
                case 2:
                    addManagerEmployee(manager, sc);
                    break;
                case 3:
                    manager.displayAllEmployees();
                    break;
                case 4:
                    manager.calculateAllSalaries();
                    break;
                case 5:
                    System.out.print("请输入部门名称：");
                    String dept = sc.nextLine();
                    manager.filterByDepartment(dept);
                    break;
                case 6:
                    System.out.println("系统退出，感谢使用！");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("无效选项，请重新输入！");
            }
        }
    }

    // 添加普通员工
    private static void addRegularEmployee(EmployeeManager manager, Scanner sc) {
        System.out.println("\n--- 添加普通员工 ---");
        System.out.print("员工ID：");
        String id = sc.nextLine();
        System.out.print("姓名：");
        String name = sc.nextLine();
        System.out.print("部门：");
        String dept = sc.nextLine();
        System.out.print("基本工资：");
        double baseSalary = sc.nextDouble();
        manager.addEmployee(new RegularEmployee(id, name, dept, baseSalary));
    }

    // 添加管理层员工
    private static void addManagerEmployee(EmployeeManager manager, Scanner sc) {
        System.out.println("\n--- 添加管理层员工 ---");
        System.out.print("员工ID：");
        String id = sc.nextLine();
        System.out.print("姓名：");
        String name = sc.nextLine();
        System.out.print("部门：");
        String dept = sc.nextLine();
        System.out.print("基本工资：");
        double baseSalary = sc.nextDouble();
        System.out.print("管理津贴：");
        double bonus = sc.nextDouble();
        manager.addEmployee(new ManagerEmployee(id, name, dept, baseSalary, bonus));
    }
}

// 抽象员工类（定义通用属性和抽象方法）
abstract class Employee {
    protected String employeeId;
    protected String name;
    protected String department;

    public Employee(String employeeId, String name, String department) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
    }

    // 抽象方法：计算工资（子类必须实现）
    public abstract double calculateSalary();

    // 通用方法：显示员工基本信息
    public void displayInfo() {
        System.out.println("员工ID：" + employeeId);
        System.out.println("姓名：" + name);
        System.out.println("部门：" + department);
    }

    // 获取部门（用于筛选）
    public String getDepartment() {
        return department;
    }

    // 获取员工类型（子类重写）
    public abstract String getEmployeeType();
}

// 普通员工类（继承Employee）
class RegularEmployee extends Employee {
    private double baseSalary; // 基本工资
    private static final double BENEFIT_RATE = 0.08; // 福利补贴比例（8%）

    public RegularEmployee(String employeeId, String name, String department, double baseSalary) {
        super(employeeId, name, department);
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateSalary() {
        // 普通员工工资 = 基本工资 + 基本工资*福利补贴
        return baseSalary + (baseSalary * BENEFIT_RATE);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("类型：普通员工");
        System.out.println("基本工资：¥" + String.format("%.2f", baseSalary));
        System.out.println("实发工资：¥" + String.format("%.2f", calculateSalary()));
    }

    @Override
    public String getEmployeeType() {
        return "普通员工";
    }
}

// 管理层员工类（继承Employee）
class ManagerEmployee extends Employee {
    private double baseSalary; // 基本工资
    private double managementBonus; // 管理津贴

    public ManagerEmployee(String employeeId, String name, String department, double baseSalary, double managementBonus) {
        super(employeeId, name, department);
        this.baseSalary = baseSalary;
        this.managementBonus = managementBonus;
    }

    @Override
    public double calculateSalary() {
        // 管理层工资 = 基本工资 + 管理津贴 + 基本工资*15%（绩效）
        return baseSalary + managementBonus + (baseSalary * 0.15);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("类型：管理层员工");
        System.out.println("基本工资：¥" + baseSalary);
        System.out.println("管理津贴：¥" + managementBonus);
        System.out.println("实发工资：¥" + String.format("%.2f", calculateSalary()));
    }

    @Override
    public String getEmployeeType() {
        return "管理层员工";
    }
}

// 员工管理类（处理员工集合和业务逻辑）
class EmployeeManager {
    private List<Employee> employees;

    public EmployeeManager() {
        employees = new ArrayList<>();
    }

    // 添加员工（多态：接收Employee子类对象）
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("✅ 员工添加成功：" + employee.name + "（" + employee.getEmployeeType() + "）");
    }

    // 显示所有员工信息
    public void displayAllEmployees() {
        System.out.println("\n====== 员工列表（共" + employees.size() + "人）======");
        if (employees.isEmpty()) {
            System.out.println("暂无员工数据");
            return;
        }

        for (int i = 0; i < employees.size(); i++) {
            System.out.println("\n【员工" + (i + 1) + "】");
            employees.get(i).displayInfo();
        }
    }

    // 计算所有员工工资总额
    public void calculateAllSalaries() {
        System.out.println("\n====== 工资统计 ======");
        if (employees.isEmpty()) {
            System.out.println("暂无员工数据");
            return;
        }

        double total = 0;
        for (Employee emp : employees) {
            double salary = emp.calculateSalary();
            total += salary;
            System.out.println(emp.name + "（" + emp.getEmployeeType() + "）：¥" + String.format("%.2f", salary));
        }
        System.out.println("--------------------------");
        System.out.println("本月工资总额：¥" + String.format("%.2f", total));
    }

    // 按部门筛选员工
    public void filterByDepartment(String dept) {
        System.out.println("\n====== " + dept + "员工列表 ======");
        boolean found = false;

        for (Employee emp : employees) {
            if (emp.getDepartment().equals(dept)) {
                emp.displayInfo();
                System.out.println("---");
                found = true;
            }
        }

        if (!found) {
            System.out.println("该部门暂无员工");
        }
    }
}