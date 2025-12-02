package code;

public class work16 {
    public static int[] m(int[] a, int[] b) {
        int l = a.length + b.length;
        int[] c = new int[l];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while (i < a.length) {
            c[k++] = a[i++];
        }

        while (j < b.length) {
            c[k++] = b[j++];
        }

        return c;
    }

    public static void main(String[] args) {
        int[] a = {1,3,5};
        int[] b = {2,4,6};
        int[] c = m(a, b);

        for (int n : c) {
            System.out.print(n + " ");
        }
    }
}