public class Sort {
    public  Sort(){
        

    }
    //implements a merge sort with an iterative approach
    public static void iterativeMerge(Array A){
        //checks to insure the Array isn't empty and if so then nothing is done
        if(A == null ){
            return;
        }
        if(A.length()>1){
            int mid = A.length()/2;
            Array left = new Array(mid);
            for(int i = 0; i < mid; i++){
                left.setVal(i, A.getVal(i));
            }
            Array right = new Array(A.length()-mid);
            for(int i = mid; i < A.length(); i ++){
                right.setVal(i - mid, A.getVal(i));
            }
            //Creates the left and right values for the merge and merges and combines them
            iterativeMerge(left);
            iterativeMerge(right);
            int i = 0;
            int j = 0;
            int k = 0;
            while(i < left.length() && j < right.length()){
                if(left.getVal(i)< right.getVal(j)){
                    A.setVal(k, left.getVal(i));
                    i++;
                }else{
                    A.setVal(k,right.getVal(j));
                    j++;
                }
                k++;
            }
            while(i < left.length())
            {
                A.setVal(k, left.getVal(i));
                i++;
                k++;
            }
            while(j < right.length())
            {
                A.setVal(k,right.getVal(j));
                j++;
                k++;
            }
        }
        
    }
       // Sorts Array A using an iterative combination to Merge Sort and
    // Insertion Sort
    public static void insertMerge(Array A, int size) {
        return;
    }
    //usinga 3 partition merge sorting algoithm
    public static void threeWayMerge(Array arr) {
        //checks to insure the Array isn't empty and if so then nothing is done
        //And if arr.length() is <= to 1 then return it as well
        if (arr == null || arr.length() <= 1)
            return;
        Array temp = new Array(arr.length());
        threeWayMergeSort(arr, 0, arr.length() - 1, temp);
    }
    // implemetns a merges that uses three subarrays 
    private static void threeWayMergeSort(Array arr, int left, int right, Array temp) {
        // creates the subarrays the seprate the merges and then merges. 
        if (left < right) {
            int mid1 = left + (right - left) / 3;
            int mid2 = left + 2 * (right - left) / 3;
            threeWayMergeSort(arr, left, mid1, temp);
            threeWayMergeSort(arr, mid1 + 1, mid2, temp);
            threeWayMergeSort(arr, mid2 + 1, right, temp);
            merge(arr, left, mid1, mid2, right, temp);
        }
    }

    private static void merge(Array arr, int left, int mid1, int mid2, int right, Array temp) {
        int i = left, j = mid1 + 1, k = mid2 + 1, t = left;
        // compare elements from the three segments and merge them into the temporary array
        while (i <= mid1 && j <= mid2 && k <= right) {
            if (arr.getVal(i) <= arr.getVal(j) && arr.getVal(i) <= arr.getVal(k))
                temp.setVal(t++, arr.getVal(i++));
            else if (arr.getVal(j) <= arr.getVal(i) && arr.getVal(j) <= arr.getVal(k))
                temp.setVal(t++, arr.getVal(j++));
            else
                temp.setVal(t++, arr.getVal(k++));
        }
        // merge any remaining elements from the first and second subarray

        while (i <= mid1 && j <= mid2) {
            if (arr.getVal(i) <= arr.getVal(j))
                temp.setVal(t++, arr.getVal(i++));
            else
                temp.setVal(t++, arr.getVal(j++));
        }
        //merge any remaining elements from the second and third subarray

        while (j <= mid2 && k <= right) {
            if (arr.getVal(j) <= arr.getVal(k))
                temp.setVal(t++, arr.getVal(j++));
            else
                temp.setVal(t++, arr.getVal(k++));
        }

        //merge any remaining elements from the first and third subarray

        while (i <= mid1 && k <= right) {
            if (arr.getVal(i) <= arr.getVal(k))
                temp.setVal(t++, arr.getVal(i++));
            else
                temp.setVal(t++, arr.getVal(k++));
        }
        //merge any remaining elements from the first subarray

        while (i <= mid1)
            temp.setVal(t++, arr.getVal(i++));
        //merge any remaining elements from the second subarray

        while (j <= mid2)
            temp.setVal(t++, arr.getVal(j++));
        // merge any remaining elements from the third subarray

        while (k <= right)
            temp.setVal(t++, arr.getVal(k++));
        // copy the merged elements back into the original array
        for (int p = left; p <= right; p++)
            arr.setVal(p, temp.getVal(p));
    }


    
      // implements a variation of quicksort that partitions the array into five segments
    // by using two pivot elemtns
    public static void fiveWayQuick(Array A) {
        fiveQuickSort(A, 0, A.length() - 1);
    }

    // recursively sorts the array using the five-way partitioning quicksort algorithm
    private static void fiveQuickSort(Array A, int low, int high) {
        if (low < high) {
            // partition the array into five segments and retrieve pivot indices
            int[] pi = partition(A, low, high);
            int pivotOne = pi[0];
            int pivotTwo = pi[1];
            // recursively sort the segments around the pivots
            fiveQuickSort(A, low, pivotOne - 1);
            fiveQuickSort(A, pivotOne + 1, pivotTwo - 1);
            fiveQuickSort(A, pivotTwo + 1, high);
        }
    }

    private static int[] partition(Array A, int low, int high) {    
        int pivot1 = A.getVal(low); 
        int pivot2 = A.getVal(high);   

        // checks to see if pivot1 is smaller or equal to pivot2
        if (pivot1 > pivot2) {
            A.swap(low, high);
            pivot1 = A.getVal(low);
            pivot2 = A.getVal(high);
        }
    
        //initialize pointers for partitioning
        int i = low + 1;
        int j = high - 1; 
        int k = low + 1; 
        
        //partition the array into segments based on pivot values.
        while (k <= j) {
            if (A.getVal(k) < pivot1) {
                A.swap(i, k); 
                i++;
            } else if (A.getVal(k) >= pivot2) {
                while (A.getVal(j) > pivot2 && k < j) {
                    j--;
                }
                A.swap(k, j);
                j--;

                if (A.getVal(k) < pivot1) {
                    A.swap(k, i);
                    i++;
                }
            }
            k++;
        }
        
        // finalizes the partitioning and swap pivot elements into their correct positions
        i--;
        j++;
        A.swap(low, i);
        A.swap(high, j);
    
        return new int[]{i, j};
    }

    // implements selection sort wher every eleemtn in A is at most d elements away from its final position
    public static void locSelect(Array A, int d) {
        int size = A.length();
        //move the unsorted subarray one by one
        for (int i = 0; i < size - 1; i++) {

            //finds teh minimum element in teh unsorted array
            int minIndex = 0;
            int minVal = Integer.MAX_VALUE;
            
            for (int j = i; j <= Math.min(size - 1, i + d); j++) {

                int cur = A.getVal(j);
                if (cur < minVal) {
                    minVal = cur;
                    minIndex = j;
                }
            }
            A.swap(i, minIndex);
        }
    }
    
    
    
    public static void locHeap(Array A, int d) {
        int N = A.length();

        // Build a heap using only the first d elements
        // Starting the loop at N / 2 - 1 ensures
        // that the heapify operation begins at the last non-leaf
        // node and progresses upward to the root of the heap.
        for (int i = (N / 2) - 1; i >= 0; i--) {
            heapify(A, N, i, d);

        }

        // Repeatedly extract the maximum element and place it at the end of the array
        for (int i = N - 1; i >= 0; i--) {
            // Move the maximum element to the end of the array
            A.swap(0, i);

            // Heapify the reduced heap using only the first d elements
            heapify(A, i, 0, d);
        }
    }

    // Heapify a subtree rooted with node i, considering only the first d elements
    private static void heapify(Array A, int N, int i, int d) {
        int max = i; // Max Value index
        int left = 2 * i + 1; // Left child index
        int right = 2 * i + 2; // Right child index

        // Compare with the left child within the locality limit
        for (int j = left; j <= right && j - i <= d; j++) {
            if (j < N && A.getVal(j) > A.getVal(max)) {
                max = j;
            }
        }

        // Compare with the right child within the locality limit
        for (int j = right; j >= left && j - i <= d; j--) {
            if (j < N && A.getVal(j) > A.getVal(max)) {
                max = j;
            }
        }

        // If the largest element is not the root, swap and heapify the affected subtree
        if (max != i) {
            A.swap(i, max);
            heapify(A, N, max, d);
        }
    }
    public static LinkedList mergeSort(LinkedList list) {
        //returns the list if it's null  or is just a single node

        if (list == null || list.head() == null || list.head().next() == null) {
            return list;
        }
        //Split the list into two halves
        LinkedList[] halves = splitIntoHavles(list);
        //recursively sorts each half
        halves[0] = mergeSort(halves[0]);
        halves[1] = mergeSort(halves[1]);
        //finally merge the havles into one  sorted LL
        return merge(halves[0].head(), halves[1].head());
    }

    
    private static LinkedList[] splitIntoHavles(LinkedList list) {
        Node slow = list.head();
        Node fast = list.head().next();
        // the fast pointer is two steps while the slow is one step
        while (fast != null && fast.next() != null) {
            fast = fast.next().next();
            slow = slow.next();
        }
        //slow is the middle of the list
        LinkedList firstHalf = new LinkedList(list.head());
        LinkedList secondHalf = new LinkedList(slow.next());

        //splits the list into two parts
        slow.setNext(null); 

        return new LinkedList[] { firstHalf, secondHalf };
    }
    // mergess teh two sorted linked lists into one linked list
    private static LinkedList merge(Node head1, Node head2) {
        Node placement = new Node(0); 
        Node current = placement;
        // compare elemetns from both lists and merge them togethre
        while (head1 != null && head2 != null) {

            if (head1.val() < head2.val()) {
                current.setNext(head1);
                head1 = head1.next();

            } else {
                current.setNext(head2);
                head2 = head2.next();
            }
            current = current.next();
        }
        if (head1 != null) {
            current.setNext(head1);
        }
        if (head2 != null) {
            current.setNext(head2);
        }
        return new LinkedList(placement.next()); 
    }
    
    public static LinkedList quickSort(LinkedList list) {
        // Return if empty list is empty or a single node
        if (list == null || list.head() == null || list.head().next() == null) {
            return list;
        }

        // Split list based on pivot position
        LinkedList[] halves = pivot(list.head(), list.tail());

        LinkedList half1 = quickSort(halves[0]);
        LinkedList half2 = quickSort(halves[1]);

        list.head().setNext(null);

        return append(half1, list.head(), half2);
    }

    // This function takes a linked list represented by its head node and tail
    // reference, and it partitions the elements around a pivot value (the value of
    // the head node). It creates two separate linked lists: one containing elements
    // less than or equal to the pivot value, and another containing elements
    // greater than the pivot value.
    private static LinkedList[] pivot(Node head, LinkedList tail) {
        // Create two linked lists to store elements less than or equal to the pivot
        // and elements greater than the pivot respectively.
        LinkedList left = new LinkedList();
        LinkedList right = new LinkedList();

        // Get the value of the pivot, which is the value of the head node.
        int headVal = head.val();

        // Start iterating from the tail of the linked list.
        Node curr = tail.head();
        while (curr != null) {
            // Get the value of the current node.
            int val = curr.val();

            // If the value is less than or equal to the pivot, add it to the left list,
            // otherwise add it to the right list.
            if (val <= headVal)
                left.add(val);
            else
                right.add(val);

            // Move to the next node.
            curr = curr.next();
        }

        // Return an array containing the left and right linked lists.
        return new LinkedList[] { left, right };
    }

    // This function appends three linked lists together in order: left, middle, and
    // right. It returns a new linked list containing the concatenated elements.
    private static LinkedList append(LinkedList left, Node mid, LinkedList right) {
        // Create a new linked list to store the appended elements.
        LinkedList result = new LinkedList();
        // Get the head of the result linked list.
        Node head = result.head();

        // Append elements from the left list to the result list.
        if (!left.isEmpty()) {
            Node curr = left.head();
            // Add the value of the current node to the result list.
            result.add(curr.val());
            // Update the first head of the result list.
            head = result.head();

            // Move to the next node in the left list.
            curr = curr.next();
            // Set the next node of the first head to the next node in the left list.
            head.setNext(curr);

            // Traverse the left list to update the first head to the last node.
            while (curr != null) {
                head = head.next();
                curr = curr.next();
            }
        }

        // Append the middle node to the result list.
        if (head == null) {
            // If the result list is empty, add the middle node as the head.
            result.add(mid.val());
            head = result.head();
        } else {
            // Otherwise, set the next node of the current head to the middle node,
            // and update the first head to the middle node.
            head.setNext(mid);
            head = head.next();
        }

        // Append elements from the right list to the result list.
        if (!right.isEmpty()) {
            // If the right list is not empty, set the next node of the current head
            // to the head of the right list.
            head.setNext(right.head());
        }

        // Return the result list containing the appended elements.
        return result;
    }


 
    // uses recursion to create a working bubble sort
    public static LinkedList bubbleSort(LinkedList list){
        int size = 0;
        Node start = list.head();
        //counts the length of the list and will stop when start is null
        while(start != null){
            size ++; 
            start = start.next();

        }
        // iterates through the entire list to perform bubbleSort
        for(int i = 1; i < size; i++){
            list = bubble(list);
        }

        return list;
    }
    // preforms a single step bubble sort with recursion
    static LinkedList bubble(LinkedList list){
         //returns the list if it's null  or is just a single node or if list is empty

        if(list.isEmpty() || list.head().next() == null || list == null){
            return list;
        }
        Node a = list.head();
        Node b = list.tail().head();
        LinkedList t = list.tail().tail();
        //if a < b then add b first adn then add a value
        if(a.val() <= b.val()){
            t.add(b.val());
            t = bubble(t);
            t.add(a.val());
        }else{
        //else then add a first and then b 
            t.add(a.val());
            t = bubble(t);
            t.add(b.val());
        }
        return t;

    }

    public static LinkedList insertionSort(LinkedList list) {
        //returns the list if it's null  or is just a single node
        if (list == null || list.head() == null || list.head().next() == null) {
            return list;
        }
        // sort the tail recursively
        LinkedList tail = insertionSort(list.tail());
        // inserts teh head of the list into the sorted list 
        return insert(tail, list.head());
    }

    private static LinkedList insert(LinkedList sortedList, Node node) {
         //returns the sortedList if it's null  or is just a single node
        if (sortedList == null || sortedList.head() == null || node.val() <= sortedList.head().val()) {
            node.setNext(sortedList.head());
            return new LinkedList(node);
        }

        //finds the correct position to insert the new node
        Node cur = sortedList.head();
        while (cur.next() != null && node.val() > cur.next().val()) {
            cur = cur.next();
        }
        //inserts the new node into the sorted list
        node.setNext(cur.next());
        cur.setNext(node);

        return sortedList;
    }
}
