import java.util.NoSuchElementException;

public class Deque<T> {
    private Object[] array;
    private int front;
    private int back;
    private int size;
    private int accessCount;

    // Constructor with default size of 16
    public Deque() {
        this(16);
    }

    // Constructor with specified capacity
    public Deque(int cap) {
        this.array = new Object[cap];
        this.front = 0;
        this.back = -1;
        this.size = 0;
        this.accessCount = 0;
    }

    // Increment access count on each access operation
    private void incrementAccessCount() {
        this.accessCount++;
    }

    // Method to resize the array if necessary
    private void resizeIfNeeded() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            if (back >= front) {
                System.arraycopy(array, front, newArray, 0, size);
            } else {
                System.arraycopy(array, front, newArray, 0, array.length - front);
                System.arraycopy(array, 0, newArray, array.length - front, back + 1);
            }
            array = newArray;
            front = 0;
            back = size - 1;
        } else if (size > 0 && size * 4 <= array.length) {
            int newCapacity = Math.max(array.length / 2, 16);
            Object[] newArray = new Object[newCapacity];
            if (back >= front) {
                System.arraycopy(array, front, newArray, 0, size);
            } else {
                System.arraycopy(array, front, newArray, 0, array.length - front);
                System.arraycopy(array, 0, newArray, array.length - front, back + 1);
            }
            array = newArray;
            front = 0;
            back = size - 1;
        }
    }

    public void addLast(T item) {
        incrementAccessCount();
        resizeIfNeeded();
        back = (back + 1) % array.length;
        array[back] = item;
        size++;
    }

    public void addFirst(T item) {
        incrementAccessCount();
        resizeIfNeeded();
        front = (front - 1 + array.length) % array.length;
        array[front] = item;
        size++;
    }

    public boolean isEmpty() {
        incrementAccessCount();
        return size == 0;
    }

    public T removeFirst() {
        incrementAccessCount();
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        @SuppressWarnings("unchecked")
        T item = (T) array[front];
        array[front] = null; // Allow GC to reclaim memory
        front = (front + 1) % array.length;
        size--;
        resizeIfNeeded();
        return item;
    }

    public T removeLast() {
        incrementAccessCount();
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        @SuppressWarnings("unchecked")
        T item = (T) array[back];
        array[back] = null; // Allow GC to reclaim memory
        back = (back - 1 + array.length) % array.length;
        size--;
        resizeIfNeeded();
        return item;
    }

    public T peekFirst() {
        incrementAccessCount();
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        @SuppressWarnings("unchecked")
        T item = (T) array[front];
        return item;
    }

    public T peekLast() {
        incrementAccessCount();
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        @SuppressWarnings("unchecked")
        T item = (T) array[back];
        return item;
    }

    public int size() {
        incrementAccessCount();
        return size;
    }

    public int getAccessCount() {
        return this.accessCount;
    }

    public void resetAccessCount() {
        this.accessCount = 0;
    }
}
