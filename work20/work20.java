package work20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class work20 {
    public static void main(String[] args) {
        String p = "C:\\Users\\QQ\\.vscode\\IDEA-JAVA\\ccdd\\src\\work20\\test.txt";
        int c = 0;

        try (BufferedReader r = new BufferedReader(new FileReader(p))) {
            String l;
            while ((l = r.readLine()) != null) {
                String[] ws = l.split("\\s+");
                for (String w : ws) {
                    if (!w.isEmpty()) c++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("单词数量：" + c);
    }
}