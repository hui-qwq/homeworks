package code;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class work11 {
    public static void main(String[] args) {
        String filePath = "data.txt";

        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            System.out.println("请输入要写入文件的内容（输入exit结束）：");
            String input;
            while (!(input = scanner.nextLine()).equals("exit")) {
                writer.write(input);
                writer.newLine();
            }
            System.out.println("内容已成功写入文件！");

        } catch (IOException e) {
            System.err.println("写入文件时发生错误：" + e.getMessage());
        }

        System.out.println("\n从文件中读取的内容：");
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("读取文件时发生错误：" + e.getMessage());
        }
    }
}