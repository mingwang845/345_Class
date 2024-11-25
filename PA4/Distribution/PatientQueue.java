public class PatientQueue {
	static Patient[] queue;
	static PHashtable table;
	private int minimumCapacity;
	//initializes the default PatientQueue with a minimumCapacity of 11
	public PatientQueue() {
		minimumCapacity = 11;
		queue = new Patient[11];
		table = new PHashtable();
	}
	//initializes the default PatientQueue with a minimumCapacity of cap
	public PatientQueue(int cap) {
		this.minimumCapacity = cap;
		queue = new Patient[cap];
		table = new PHashtable(cap);
	}
	
	//insert a patient into the queue properly by calling swimHeap to make sure
	//the queue is in the right heap order
	public void insert(Patient p) {
		resize();
		queue[table.size()] = p;
		p.setPosition(table.size());
		swimHeap();
    	table.put(p);
    }
	// restores a heap by property moving hte inserted patient up teh heap
	// to ensure it's in the final location, causing a runtime of O(logn)
	private void swimHeap() {
		if (table.size() <= 1) {
			return;
		}
		int index = table.size()-1;
		if (table.size() % 2 == 0) {
			if (queue[index].compareTo(queue[index/2]) > 0) {
				Patient temp = queue[index];
				temp.setPosition(index/2);
				queue[index/2].setPosition(index);
				queue[index] = queue[index/2];
				queue[index/2] = temp;
			}
			index--;
		}
		for (int i = index; i > 0; i -= 2) {
			Patient left = queue[i];
			Patient right = queue[i-1];
			Patient parent = queue[(i-1)/2];
			Patient greatest;
			int maxIndex ;
			if(left.compareTo(right)> 0){
				greatest = left;
				maxIndex = i;
			}else{
				greatest = right;
				maxIndex = i -1;
			}
			if (greatest.compareTo(parent) > 0) {
				Patient temporary = greatest;
				temporary.setPosition((i-1)/2);
				parent.setPosition(maxIndex);
				queue[maxIndex] = parent;
				queue[(i-1)/2] = temporary;
			}
		}
	}
	//restores a heap property by moveing the inserted patient down using sink
	// causing a runtime of O(logn)
	private void sinkHeap() {
		int i = 0;
		while (i*2 < table.size()) {
			Patient parentNode = queue[i];
			Patient leftChild = queue[2*i];
			Patient rightChild = queue[2*i + 1];
			if (leftChild != null) {
				Patient greatest;
				int greatestIndex;
				if (rightChild == null) {
					greatest = leftChild;
					greatestIndex = 2*i;
				} else {
					if (leftChild.compareTo(rightChild) > 0) {
						greatest = leftChild;
						greatestIndex = 2 * i;
					} else {
						greatest = rightChild;
						greatestIndex = 2 * i + 1;
					}
				} 
				if (greatest.compareTo(parentNode) > 0) {
					Patient temp = greatest;
					temp.setPosition(i);
					parentNode.setPosition(greatestIndex);
					queue[greatestIndex] = parentNode;
					queue[i] = temp;
				}
			}
			i+=2;
		}
	}
	//removes the next patient in the queue which should be at the index of 0
	// as it's has the hightest priority, using the remove funciton to remove next 
	public Patient removeNext() throws EmptyQueueException {
		if (queue[0] == null)
        { 
            return null;
        }
		Patient patientRemoved = remove(queue[0].name());
		return patientRemoved;
	}
	//returns and gets  the next patient in the queue
	public Patient getNext() throws EmptyQueueException {
		if (table.size() == 0){
			throw new EmptyQueueException();
		}
		Patient patientNext = queue[0];
		return patientNext;
	}
	//returns the size/number of elements in teh queue
	public int size() {
		return table.size();
	}
	//checks to see if the table is empty
	public boolean isEmpty() {
		return table.size() == 0;
	}
	//removes a patients from the queue by the name and seearchs for the position
	//and sets the removed position all then calls swimHeap dn sinkHeap to ensure that it
	// will be in the right order for the priority queue
	public Patient remove(String name) {
		Patient patientRemoved = table.remove(name);
		if (patientRemoved == null){
			return null;
		}
		queue[patientRemoved.posInQueue()] = queue[table.size()];
		queue[patientRemoved.posInQueue()].setPosition(patientRemoved.posInQueue());
		queue[table.size()] = null;
		swimHeap();
		sinkHeap();
		patientRemoved.setPosition(-1);
		resize();
		return patientRemoved;
	}
	//Upadtes the urgency of a patient and ensure that the heap
	//properyt is maintained 
	public Patient update(String name, int urgency) {
		Patient patientUpdate = table.get(name);
		patientUpdate.setUrgency(urgency);

        //call swim and sink to make sure it properly works
		swimHeap();
		sinkHeap();
		return patientUpdate;
    }
	//checks and resizes the queue if necessary
	private void resize() {
		if (table.size() >= queue.length) {
			int newSize = queue.length * 2;
			Patient[] newQueue = new Patient[newSize];
			for (int i = 0; i < queue.length; i++) {
				newQueue[i] = queue[i];
			}
			queue = newQueue;
		} else if (table.size() < queue.length/8) {
			int newSize = queue.length / 2;
			Patient[] newQueue = new Patient[newSize];
			for (int i = 0; i < table.size(); i++) {
				newQueue[i] = queue[i];
			}
			queue = newQueue;
		}
	}
	
}