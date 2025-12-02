package code;

import java.util.Comparator;

public class work14 {
    public static <T extends Comparable<T>> T f(T[] a) {
        if (a == null || a.length == 0) return null;
        T m = a[0];
        for (T e : a) {
            if (e.compareTo(m) > 0) m = e;
        }
        return m;
    }

    public static <T> T f(T[] a, Comparator<T> c) {
        if (a == null || a.length == 0 || c == null) return null;
        T m = a[0];
        for (T e : a) {
            if (c.compare(e, m) > 0) m = e;
        }
        return m;
    }

    public static void main(String[] args) {
        Integer[] i = {3,1,4,5,9};
        System.out.println(f(i));

        String[] s = {"a","c","b"};
        System.out.println(f(s));

        P[] p = {new P("A",20),new P("B",25),new P("C",18)};
        System.out.println(f(p));
        System.out.println(f(p,(x,y)->x.a - y.a));
    }

    static class P implements Comparable<P> {
        String n;
        int a;

        P(String n, int a) {
            this.n = n;
            this.a = a;
        }

        @Override
        public int compareTo(P o) {
            return this.a - o.a;
        }

        @Override
        public String toString() {
            return n + "(" + a + ")";
        }
    }
}