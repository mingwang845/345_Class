/*
 * The following PHashtable implements seperate chainging
 * with it's minimum_Capacity being 11
 * and implementing a linkedlist class that was created 
 * 
 */
public class PHashtable {
    private static final int minimumCapacity = 11;
    private LinkedList<Patient>[] hashtable;
    private int size;
    //initializes a Phsahtiable with the minimumCapity of 11; 
    public PHashtable() {
        this(minimumCapacity);
    }

    public PHashtable(int cap) {
        hashtable = new LinkedList[cap];
        for (int i = 0; i < cap; i++) {
            hashtable[i] = new LinkedList<>();
        }
    }
    //gerts the patient by looking at's it's hashcode and then iterating
    //through each sepearte chain until you find the patient you are
    //lookign for and return it 
    public Patient get(String name) {
        int hashCode = Math.abs(name.hashCode() % hashtable.length);
        LinkedList<Patient> list = hashtable[hashCode];
        for (Patient patient : list) {
            if (patient.name().equals(name)) {
                return patient;
            }
        }
        return null;
    }
    //gets the hashvalue and places the patience intot he linkedLIst array
    public void put(Patient p) {
        int hashCode = Math.abs(p.name().hashCode() % hashtable.length);
        LinkedList<Patient> list = hashtable[hashCode];
        list.add(p);
        size++;

        // Check load factor and resize if necessary and if greater than 
        // 0.75 then resize
        double loadFactor = (double) size / hashtable.length;
        if (loadFactor > 0.75) {
            resize();
        }
    }
    //removes the following patient from the hashtable by iterating through
    //and first getting the hashcode and then iterating throught the sepeate chainging and removing hte value and 
    //returns the paitnet if removed else it's a null
    public Patient remove(String name) {
        int hashCode = Math.abs(name.hashCode() % hashtable.length);
        LinkedList<Patient> list = hashtable[hashCode];
        for (Patient patient : list) {
            if (patient.name().equals(name)) {
                list.remove(patient);
                size--;
                return patient;
            }
        }
        return null;
    }
    //returns the currect size of the array. 
    public int size() {
        return size;
    }
    //resizes the array if the load factor is meet or exceeded by the user
    // and the amount of inputs
    public void resize() {
        int newCapacity = findNextPrime(hashtable.length * 2);
        LinkedList<Patient>[] newHashTable = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newHashTable[i] = new LinkedList<>();
        }
        // Rehash existing elements
        for (LinkedList<Patient> list : hashtable) {
            for (Patient patient : list) {
                int hashCode = Math.abs(patient.name().hashCode() % newCapacity);
                newHashTable[hashCode].add(patient);
            }
        }
        hashtable = newHashTable;
    }
    //helps find the next prime
    private int findNextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }
    //double chekcs if it's a prime or not 
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
