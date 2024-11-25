public class test {
    public static void main(String[] args) {
        // Test with different sizes and d values
        testSorting(10, 10); // Size 10, d = 10
        testSorting(20, 15); // Size 20, d = 15
        testSorting(30, 5);  // Size 30, d = 5


        testSortingLinkedList(10, 10);
        testSortingLinkedList(20, 15);
        testSortingLinkedList(30, 5);

  


    }

    public static void testSorting(int size, int d) {
        // Generate the primitive integer array
        int[] primitiveArray = ArrayGen.getRand(size, d);

        // Create an instance of Array using the primitive array
        Array array = new Array(size);

        for(int i = 0; i < size ; i++){
            array.setVal(i, primitiveArray[i]);
        }

        // Display the original array
        System.out.println("Original Array:");
        printArray(array);

        // Test sorting algorithms
        System.out.println("Loc Heap:");
        Sort.locHeap(array,2);
        printArray(array);
        
        // Add more sorting algorithms as needed
    }

    public static void testSortingLinkedList(int size, int d){
        int[] primitiveArray = ArrayGen.getRand(size, d);
        LinkedList ll = new LinkedList();
        for(int i = 0; i < size; i++){
            ll.add(primitiveArray[i]);
        }
        System.out.println("OriginalArray:");
        printLL(ll.head());

        System.out.println("Merge Sort:");
        LinkedList sorted  = Sort.insertionSort(ll);

        printLL(sorted.head());


        
    }


    public static void printLL(Node head){
        Node current = head;
        while(current != null){
            System.out.print(current.val() + " ");
            current = current.next();
        }
        System.out.println();
    }
    

    // Method to print an array
    public static void printArray(Array array) {
        for (int i = 0; i < array.length(); i++) {
            System.out.print(array.getVal(i) + " ");
        }
        System.out.println();
    }
}
