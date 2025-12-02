package code.work31;
import java.io.*;
import java.util.*;

public class work31 {
    private static final String FILE_PATH = "books.txt";
    private Map<String, Book> books = new TreeMap<>(); // 按ISBN升序排列

    public static void main(String[] args) {
        work31 system = new work31();
        system.loadBooksFromFile(); // 启动时加载文件中的图书

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n====== 图书管理系统 ======");
            System.out.println("1. 添加图书");
            System.out.println("2. 查看所有图书");
            System.out.println("3. 借书");
            System.out.println("4. 还书");
            System.out.println("5. 保存图书信息");
            System.out.println("6. 退出系统");
            System.out.print("请输入操作选项：");

            int option = sc.nextInt();
            sc.nextLine(); // 处理换行符

            switch (option) {
                case 1:
                    system.addBook(sc);
                    break;
                case 2:
                    system.viewAllBooks();
                    break;
                case 3:
                    system.borrowBook(sc);
                    break;
                case 4:
                    system.returnBook(sc);
                    break;
                case 5:
                    system.saveBooksToFile();
                    break;
                case 6:
                    system.saveBooksToFile(); // 退出前自动保存
                    System.out.println("系统退出，已保存图书信息！");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("无效选项，请重新输入！");
            }
        }
    }

    // 添加图书
    private void addBook(Scanner sc) {
        System.out.println("\n--- 添加图书 ---");
        System.out.print("ISBN编号：");
        String isbn = sc.nextLine();
        if (books.containsKey(isbn)) {
            System.out.println("❌ 该ISBN编号已存在！");
            return;
        }

        System.out.print("书名：");
        String title = sc.nextLine();
        System.out.print("作者：");
        String author = sc.nextLine();
        System.out.print("库存数量：");
        int stock = sc.nextInt();

        books.put(isbn, new Book(isbn, title, author, stock));
        System.out.println("✅ 图书添加成功！");
    }

    // 查看所有图书
    private void viewAllBooks() {
        System.out.println("\n====== 图书列表（按ISBN升序）======");
        if (books.isEmpty()) {
            System.out.println("暂无图书信息");
            return;
        }

        for (Book book : books.values()) {
            System.out.println("ISBN：" + book.getIsbn());
            System.out.println("书名：" + book.getTitle());
            System.out.println("作者：" + book.getAuthor());
            System.out.println("库存：" + book.getStock());
            System.out.println("--------------------------");
        }
    }

    // 借书功能
    private void borrowBook(Scanner sc) {
        System.out.println("\n--- 借书 ---");
        System.out.print("请输入ISBN编号：");
        String isbn = sc.nextLine();

        Book book = books.get(isbn);
        if (book == null) {
            System.out.println("❌ 未找到该图书！");
            return;
        }

        if (book.getStock() > 0) {
            book.setStock(book.getStock() - 1);
            System.out.println("✅ 借书成功！当前库存：" + book.getStock());
        } else {
            System.out.println("❌ 库存不足，无法借书！");
        }
    }

    // 还书功能
    private void returnBook(Scanner sc) {
        System.out.println("\n--- 还书 ---");
        System.out.print("请输入ISBN编号：");
        String isbn = sc.nextLine();

        Book book = books.get(isbn);
        if (book == null) {
            System.out.println("❌ 未找到该图书！");
            return;
        }

        book.setStock(book.getStock() + 1);
        System.out.println("✅ 还书成功！当前库存：" + book.getStock());
    }

    // 保存图书信息到文件
    private void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Book book : books.values()) {
                writer.write(book.getIsbn() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getStock());
                writer.newLine();
            }
            System.out.println("✅ 图书信息已保存到文件！");
        } catch (IOException e) {
            System.out.println("❌ 保存文件失败：" + e.getMessage());
        }
    }

    // 从文件加载图书信息
    private void loadBooksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return; // 文件不存在则跳过

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String isbn = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    int stock = Integer.parseInt(parts[3]);
                    books.put(isbn, new Book(isbn, title, author, stock));
                }
            }
            System.out.println("✅ 已从文件加载" + books.size() + "本图书信息");
        } catch (IOException e) {
            System.out.println("❌ 加载文件失败：" + e.getMessage());
        }
    }

    // 图书实体类
    static class Book {
        private String isbn;
        private String title;
        private String author;
        private int stock;

        public Book(String isbn, String title, String author, int stock) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.stock = stock;
        }

        // Getter和Setter方法
        public String getIsbn() { return isbn; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public int getStock() { return stock; }
        public void setStock(int stock) { this.stock = stock; }
    }
}