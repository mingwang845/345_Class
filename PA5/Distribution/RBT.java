import java.util.Deque;
import java.util.LinkedList;

public class RBT {
    private Node root;
    private int size;

    /* CONSTRUCTOR */
    public RBT() {
        this.root = null;
        this.size = 0;
    }

    /* PUBLIC METHODS */

    /***
     * insert a new key into tree
     * or update the count if the key already exists
     */
    public void put(int key) {
        if (root == null) {
            root = new Node(key);
            size++;
        } else
            root = put(key, root);
        root.setColor(Node.BLACK);
    }

    /***
     * get the count associated with the given key;
     * return 0 if key doesn't exist in the tree
     */
    public int get(int key) {
        Node x = root;
        while (x != null) {
            if (key == x.key())
                return x.count();
            if (key > x.key())
                x = x.right();
            else
                x = x.left();
        }
        return 0;
    }

    /***
     * get the color of a node
     ***/
    public String getColor(int key) {
        Node x = get(key, root);
        if (x == null)
            return null;
        if (x.isRed())
            return "RED";
        return "BLACK";
    }

    /***
     * return true if the tree
     * is empty and false
     * otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /***
     * return the number of Nodes
     * in the tree
     */
    public int size() {
        return size;
    }

    /***
     * returns the height of the tree
     */
    public int height() {
        return height(root);
    }

    /***
     * returns the height of node
     * with given key in the tree;
     * return -1 if the key does
     * not exist in the tree
     */
    public int height(int key) {
        Node x = get(key, root);
        return height(x);
    }

    /***
     * returns true if the key is in the tree
     * and false otherwise
     ***/
    public boolean contains(int key) {
        Node x = get(key, root);
        if (x != null)
            return true;
        return false;
    }

    /***
     * return the depth of the node
     * with the given key in the tree;
     * return -1 if the key does not exist
     * in the tree
     ***/
    public int depth(int key) {
        int depth = 0;
        Node x = root;
        while (x != null) {
            if (key == x.key())
                break; // Exit the loop if the key is found
            else if (key > x.key()) {
                x = x.right();
            } else {
                x = x.left();
            }
            depth++; // Increment depth as we move down the tree
        }
        if (x != null && key == x.key()) {
            return depth; // Return the depth if the key is found
        } else {
            return -1; // Return -1 if the key doesn't exist
        }
    }
    

    /***
     * return the size of the subtree
     * rooted at the given key
     ***/
    public int size(int key) {
        Node x = get(key, root);
        int count = 0;
        if (x == null) {
            return -1; // Key does not exist, return -1
        } else {
            return size(x, count); // Call the recursive method to count the size of the subtree rooted at node x
        }
        
    }
    /***
     * Recursive method to calculate the size of the subtree rooted at the given node
     ***/
    //right now with the N that is being called it's one gonna return 1 because every tree might only have one node
    private int size(Node x, int size) {
        if (x == null) {
            return 0; // Base case: if node is null, subtree size is 0
        }
        return x.N(); // Return the size of the subtree rooted at node x
    }
    
    
   

    /***
     * return the minimum key
     ***/
    public int min() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        
        Node minNode = findMin(root); // Call helper function to find the leftmost node
        return minNode.key(); // Return the key of the leftmost node
    }
    
    /***
     * Helper method to find the leftmost node in the tree
     ***/
    //when checkign the node if left is null or not before traversing and going there 
    private static Node findMin(Node node) {
        Node left = node.left();
        Node x = node;
        while (left != null ) {
            x= left;
            left = left.left(); // Traverse left until reaching the leftmost node    
        }
        return x; // Return the leftmost node
    }

    /***
     * return the maximum key
     ***/
    public int max() throws EmptyTreeException {
        // TO BE IMPLEMENTED
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        Node maxNode = findMax(root);
        return maxNode.key();
    }
    private Node findMax(Node node){
        Node right = node.right();
        Node x = node;
        while(right != null){
            x= right;
            right = right.right();
        }
        return x;
    }

    /***
     * return the largest key
     * that is less than or equal
     * to the parameter
     ***/
/***
 * Return the largest key that is less than or equal to the parameter.
 * Throw a KeyDoesNotExistException if such a key does not exist.
 * Time complexity: O(log N)
 ***/
public int floor(int key) throws KeyDoesNotExistException {
    if (isEmpty()) {
        throw new KeyDoesNotExistException();
    }

    Node floorNode = findFloor(root, key); // Call helper function to find the floor node
    if (floorNode == null) {
        throw new KeyDoesNotExistException();
    }
    return floorNode.key(); // Return the key of the floor node
}

/***
 * Helper method to find the floor node in the tree
 ***/
private Node findFloor(Node node, int key) {
    Node floorNode = null;
    while (node != null) {
        if (key == node.key()) {
            return node; // If key matches the current node, return the node itself
        } else if (key < node.key()) {
            node = node.left(); // Traverse left
        } else {
            floorNode = node; // Update floor node to the current node
            node = node.right(); // Traverse right
        }
    }
    return floorNode; // Return the floor node
}

    /***
     * return the smallest key
     * that is greater than or equal
     * to the parameter
     ***/
    public int ceil(int key) throws KeyDoesNotExistException {
        // TO BE IMPLEMENTED
        if(isEmpty()){
            throw new KeyDoesNotExistException();
        }
        Node ceilNode = findCeil(root, key);

        if (ceilNode == null) {
            throw new KeyDoesNotExistException();
        }

        return ceilNode.key();
    }

    private Node findCeil(Node node, int key){
        Node ceilNode = null;
        while(node != null){
            if (key == node.key()) {
                return node; // If key matches the current node, return the node itself
            } else if (key < node.key()) {
                ceilNode = node;
                node = node.left(); // Traverse left
                
            } else {
                node = node.right(); // Traverse right
            }
        }
        return ceilNode;

    }
    /***
     * return the number of keys
     * that are less than the parameter
     ***/

public int rank(int key) {
    return rank(key, root);
}

private int rank(int key, Node x) {
    if (x == null) {
        return 0; 
    }

    Node left = x.left();
    
    if (key < x.key()) {
        if (left == null) {
            return 0; 
        }

        return rank(key, left);
    } else if (key > x.key()) {
        if (left == null) {
            return 1 + rank(key, x.right()); 
        }
        return 1 + size(left.key()) + rank(key, x.right());
    } else {
        if (left == null) {
            return 0;
        }
        return size(left.key());
    }
}


    /***
     * return the key at the given rank
     ***/
/***
 * Return the key at the given rank or throw an exception if the rank passed in does not make sense for the tree.
 * This should be O(log N).
 ***/
public int select(int rank) throws NoSuchRankException {
    if(rank < 0 || rank >= size(root.key())){
        throw new NoSuchRankException();
    }
    return select(root, rank + 1); // Add 1 to rank because rank is 0-based
}

/***
 * Helper method to recursively find the key at the given rank.
 ***/
private int select(Node x, int rank) {
    if (x == null) {
        return -1; 
    }

    int leftSubtreeSize = subTree(x.left()); 

    if (rank == leftSubtreeSize + 1) {
        return x.key(); 
    } else if (rank <= leftSubtreeSize) {
        return select(x.left(), rank); 
    } else {
        return select(x.right(), rank - leftSubtreeSize - 1); 
    }
}

    

    

    /***
     * return the number of keys in [lo...hi]
     ***/
    public int size(int lo, int hi) {
        // TO BE IMPLEMENTED
        if(lo == hi){
            return 0;
        }
        int high = rank(hi);
        int low = rank(lo);
        
        if(contains(hi)){
            high++;
        }
        
        return high - low;
    }

    /***
     * return a String representation of the tree
     * level by level
     ***/
    public String toString() {
            if (isEmpty()) {
                return "";
            }

            StringBuilder result = new StringBuilder();
            Deque<Node> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    Node current = queue.poll();
                    result.append(current.toString()).append(" ");
                    if (current.left() != null) {
                        queue.offer(current.left());
                    }
                    if (current.right() != null) {
                        queue.offer(current.right());
                    }
                }
                result.append("\n"); 
            }

            return result.toString();
        }
    /* PRIVATE METHODS */

    /***
     * return the height of x
     * or -1 if x is null
     */
    private int height(Node x) {
        if (x == null)
            return -1;
        return x.height();
    }

    /***
     * recursive helper method for insert
     */
    private Node put(int key, Node x) {

        if (key == x.key()) {
            x.incCount();
        } else if (key > x.key()) {
            if (x.right() == null) {
                x.setRight(new Node(key));
                size++;

            } else {
                x.setRight(put(key, x.right()));
    
            }
        } else {
            if (x.left() == null) {
                x.setLeft(new Node(key));
                size++;

            } else {
                x.setLeft(put(key, x.left()));

            }
        }
        x = balance(x);
        x .setN(1+subTree(x.left()) + subTree(x.right()));
        x.setHeight(Math.max(height(x.left()), height(x.right())) + 1);
        return x;
    }

    private int subTree(Node x){
        return x == null ? 0: x.N();
    }

    /***
     * recursive method for getting Node
     * with given key
     */
    private Node get(int key, Node x) {
        if (x == null)
            return null;
        if (key == x.key())
            return x;
        else if (key > x.key())
            return get(key, x.right());
        return get(key, x.left());
    }

    /***
     * rotate left
     ***/
    private Node rotateLeft(Node h) {
        Node x = h.right();
        h.setRight(x.left());
        x.setLeft(h);
        x.setN(h.N());
        h.setN(1+subTree(h.left()) + subTree(h.right()));
        x.setColor(h.color());
        h.setColor(Node.RED);
        x.setHeight(h.height());
        h.setHeight(1 + Math.max(height(h.left()), height(h.right())));
        return x;
    }

    /***
     * rotate right
     ***/
    private Node rotateRight(Node h) {
        
        Node x = h.left();
        h.setLeft(x.right());
        x.setRight(h);
        x.setN(h.N());
        h.setN(1+subTree(h.left()) + subTree(h.right()));
        x.setColor(h.color());
        h.setColor(Node.RED);
        x.setHeight(h.height());
        h.setHeight(1 + Math.max(height(h.left()), height(h.right())));

        return x;
    }

    /***
     * color flip
     ***/
    private void colorFlip(Node h) {
        
        h.setColor(Node.RED);
        h.left().setColor(Node.BLACK);
        h.right().setColor(Node.BLACK);
        if (h == root)
            h.setColor(Node.BLACK);

    }

    /***
     * balance
     ***/
    private Node balance(Node h) {
        if (h.right() != null && h.right().isRed() && h.right().right() != null && h.right().right().isRed()) {
            h = rotateLeft(h);
            colorFlip(h);
        } else if (h.left() != null && h.right() != null && h.left().isRed() && h.right().isRed())
            colorFlip(h);
        else if (h.left() != null && h.left().isRed())
            h = rotateRight(h);
        return h;
    }
}
