public class Part4 {

    public static void pushZeroes(Array a) {
        int x = 0;
        int y = a.length() - 1;

        while (x < y) {
            while (x < y && a.getVal(x) != 0) {
                x++;
            }
            while (x < y && a.getVal(y) == 0) {
                y--;
            }
            if (x < y) {
                a.swap(x, y);
            }
            x++;
            y--;
        }
    }
}
