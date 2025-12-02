package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class work28 {
    static class Person {
        String n;
        int a;

        Person(String n, int a) {
            this.n = n;
            this.a = a;
        }

        @Override
        public String toString() {
            return n + "(" + a + ")";
        }
    }

    public static void main(String[] args) {
        List<Person> l = new ArrayList<>();
        l.add(new Person("A", 25));
        l.add(new Person("B", 20));
        l.add(new Person("C", 30));

        Collections.sort(l, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.a - p2.a;
            }
        });

        System.out.println(l);
    }
}