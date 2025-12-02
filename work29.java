package code;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class work29 {
    public static void main(String[] args) {
        ShapeManager shapeManager = new ShapeManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== 图形计算系统 ======");
            System.out.println("1. 添加图形（圆形/矩形/三角形）");
            System.out.println("2. 查看所有图形信息");
            System.out.println("3. 按类型筛选图形");
            System.out.println("4. 删除指定图形");
            System.out.println("5. 列出全部（含面积排序）");
            System.out.println("6. 退出系统");
            System.out.print("请输入操作选项：");

            int option = sc.nextInt();
            sc.nextLine(); // 处理换行符
            switch (option) {
                case 1:
                    addShapeMenu(shapeManager, sc);
                    break;

                case 2:
                    shapeManager.displayAllShapes();
                    break;

                case 3:
                    System.out.print("请输入筛选类型（圆形/矩形/三角形）：");
                    String type = sc.nextLine();
                    shapeManager.filterByType(type);
                    break;

                case 4:
                    System.out.print("请输入要删除的图形序号：");
                    int index = sc.nextInt() - 1;
                    shapeManager.removeShape(index);
                    break;

                case 5:
                    shapeManager.demonstratePolymorphism();
                    break;

                case 6:
                    System.out.println("感谢使用，系统退出！");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("无效选项，请重新输入！");
            }
        }
    }

    // 子菜单：选择添加的图形类型
    private static void addShapeMenu(ShapeManager manager, Scanner sc) {
        System.out.println("\n--- 添加图形 ---");
        System.out.println("1. 圆形");
        System.out.println("2. 矩形");
        System.out.println("3. 三角形");
        System.out.print("选择图形类型：");
        int type = sc.nextInt();

        switch (type) {
            case 1:
                System.out.print("输入半径：");
                manager.addShape(new Circle(sc.nextDouble()));
                break;

            case 2:
                System.out.print("输入长、宽：");
                manager.addShape(new Rectangle(sc.nextDouble(), sc.nextDouble()));
                break;

            case 3:
                System.out.print("输入三边长：");
                manager.addShape(new Triangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble()));
                break;

            default:
                System.out.println("无效类型！");
        }
    }
}

// 图形接口（新增ID标识）
interface Shape {
    String getId();               // 新增：图形唯一标识
    double calculateArea();       // 计算面积
    double calculatePerimeter();  // 计算周长
    String getShapeName();        // 获取图形名称
    void displayInfo();           // 显示图形信息
}

// 圆形类（新增ID）
class Circle implements Shape {
    private final String id;
    private double radius;

    public Circle(double radius) {
        this.id = UUID.randomUUID().toString().substring(0, 6); // 简化ID
        this.radius = radius;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getShapeName() {
        return "圆形";
    }

    @Override
    public void displayInfo() {
        System.out.println("ID：" + id);
        System.out.println("类型：" + getShapeName());
        System.out.println("半径：" + radius);
        System.out.printf("面积：%.2f\n", calculateArea());
        System.out.printf("周长：%.2f\n", calculatePerimeter());
    }
}

// 矩形类（新增ID）
class Rectangle implements Shape {
    private final String id;
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.length = length;
        this.width = width;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String getShapeName() {
        return "矩形";
    }

    @Override
    public void displayInfo() {
        System.out.println("ID：" + id);
        System.out.println("类型：" + getShapeName());
        System.out.println("长：" + length + "，宽：" + width);
        System.out.println("面积：" + calculateArea());
        System.out.println("周长：" + calculatePerimeter());
    }
}

// 三角形类（优化合法性提示）
class Triangle implements Shape {
    private final String id;
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String getId() {
        return id;
    }

    public boolean isValid() {
        return (a + b > c) && (a + c > b) && (b + c > a) && a > 0 && b > 0 && c > 0;
    }

    @Override
    public double calculateArea() {
        if (!isValid()) return -1;
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double calculatePerimeter() {
        return isValid() ? (a + b + c) : -1;
    }

    @Override
    public String getShapeName() {
        return "三角形";
    }

    @Override
    public void displayInfo() {
        System.out.println("ID：" + id);
        System.out.println("类型：" + getShapeName());
        System.out.println("三边长：" + a + "、" + b + "、" + c);

        if (isValid()) {
            System.out.printf("面积：%.2f\n", calculateArea());
            System.out.println("周长：" + calculatePerimeter());
        } else {
            System.out.println("⚠️ 无效三角形（三边不满足三角不等式）");
        }
    }
}

// 图形管理类（移除导出功能）
class ShapeManager {
    private List<Shape> shapes;

    public ShapeManager() {
        shapes = new java.util.ArrayList<>();
    }

    // 添加图形
    public void addShape(Shape shape) {
        shapes.add(shape);
        System.out.println("✅ 添加成功（ID：" + shape.getId() + "）");
    }

    // 删除图形
    public void removeShape(int index) {
        if (index >= 0 && index < shapes.size()) {
            Shape removed = shapes.remove(index);
            System.out.println("❌ 删除成功：" + removed.getShapeName() + "（ID：" + removed.getId() + "）");
        } else {
            System.out.println("❌ 序号无效！");
        }
    }

    // 显示所有图形
    public void displayAllShapes() {
        System.out.println("\n====== 图形列表（共" + shapes.size() + "个）======");
        if (shapes.isEmpty()) {
            System.out.println("暂无图形数据");
            return;
        }

        for (int i = 0; i < shapes.size(); i++) {
            System.out.println("\n【" + (i + 1) + "】");
            shapes.get(i).displayInfo();
        }
    }

    // 按类型筛选图形
    public void filterByType(String type) {
        System.out.println("\n====== 筛选结果（类型：" + type + "）======");
        boolean found = false;

        for (Shape shape : shapes) {
            if (shape.getShapeName().equals(type)) {
                shape.displayInfo();
                System.out.println("---");
                found = true;
            }
        }

        if (!found) {
            System.out.println("未找到该类型图形");
        }
    }

    // 多态演示（新增面积排序）
    public void demonstratePolymorphism() {
        System.out.println("\n====== 展示 ======");

        if (shapes.isEmpty()) {
            System.out.println("使用默认图形演示：");
            Shape[] defaults = {new Circle(4), new Rectangle(3,5), new Triangle(3,4,5)};

            // 按面积排序
            java.util.Arrays.sort(defaults, (s1, s2) -> Double.compare(s1.calculateArea(), s2.calculateArea()));

            for (Shape s : defaults) {
                System.out.println("\n声明类型：Shape → 实际类型：" + s.getClass().getSimpleName());
                System.out.println("名称：" + s.getShapeName() + "，面积：" + String.format("%.2f", s.calculateArea()));
            }
            return;
        }

        // 对已有图形按面积排序
        List<Shape> sorted = new java.util.ArrayList<>(shapes);
        sorted.sort((s1, s2) -> Double.compare(s1.calculateArea(), s2.calculateArea()));

        System.out.println("图形按面积升序排列：");
        for (Shape s : sorted) {
            System.out.println("\n" + s.getShapeName() + "（ID：" + s.getId() + "）");
            System.out.println("面积：" + String.format("%.2f", s.calculateArea()));
        }
    }
}