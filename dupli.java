import java.util.Arrays;

public class dupli {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 1, 5, 6, 6, 54, 3};
        int b = a.length;
        Arrays.sort(a);

        for (int i = 0; i < b; i++) {  
            if (i == 0 || a[i] != a[i - 1]) {
                System.out.println(a[i]);
            }
        }
    }
}
