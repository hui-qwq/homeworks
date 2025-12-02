package code;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class work19 {
    public static void main(String[] args) {
        Class<?> c = User.class;

        System.out.println("类名：" + c.getName());

        System.out.println("\n属性信息：");
        Field[] fs = c.getDeclaredFields();
        for (Field f : fs) {
            System.out.println("  " + f.getType().getSimpleName() + " " + f.getName());
        }

        System.out.println("\n方法信息：");
        Method[] ms = c.getDeclaredMethods();
        for (Method m : ms) {
            System.out.print("  " + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
            Class<?>[] ps = m.getParameterTypes();
            for (int i = 0; i < ps.length; i++) {
                System.out.print(ps[i].getSimpleName());
                if (i < ps.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }
    }

    static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void showInfo() {
            System.out.println(name + ", " + age);
        }
    }
}