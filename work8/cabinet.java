package homework1.work8;

import java.util.ArrayList;
import java.util.List;

public class cabinet {

    public cabinet() {};

    List<product> products = new ArrayList<product>();
    public void add(String id, String name, int price) {
        product p = new product(id, name, price);
        products.add(p);
        System.out.println("添加成功");
    }

    public void list() {
        System.out.printf("展示共%d个商品\n", products.size());

        for (product p : products) {
            System.out.println(p.getId() + " " +  p.getName() + " " + p.getPrice());
        }
    }
}
