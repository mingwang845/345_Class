public class Part3 {
    public static int maxProduct(Array a, int m) {
        int n = a.length();      
        int product = 1;
        int maxProduct = 1;
        for (int i = 0; i < m; i++) {
            product *= a.getVal(i);
        }
        maxProduct = product;
        for (int i = m; i < n; i++) {
            int currentElement = a.getVal(i);
            int previousElement = a.getVal(i - m);

            if (previousElement != 0) {
                product = (product / previousElement) * currentElement;
            } else {
                product = 1;
                for (int j = i - m + 1; j <= i; j++) {
                    product *= a.getVal(j);
                }
            }         
            maxProduct = Math.max(maxProduct, product);
        }
        return maxProduct;
    }
}
