public class Patient implements Comparable<Patient> {

	private String name;

	private int urgency;

	private long time_in;

	private int position;
    public Patient(String name, int urgency){
        this.name = name;
        this.urgency = urgency;
        this.time_in = 0;
    }
//Creates and initilizes patients dependent on the three parameters of
//name urgency and time_in
	public Patient(String name, int urgency, long time_in) {
		this.name = name;
		this.urgency = urgency;
		this.time_in = time_in;
		this.position = -1;
	}
//return patient name
	public String name() {
		return this.name;
	}
//return patient urgency
	public int urgency() {
		return this.urgency;
	}
//return patient time_in
	public long time_in() {
		return this.time_in;
	}
//sets the patient's urgency
	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}
//sets the position/updates teh poistion of the patient being set into the queue
	public void setPosition(int pos) {
		this.position = pos;
	}
//compares to make sure the order of the queue for the patients are correctly ordered
	@Override
	public int compareTo(Patient other) {
	if (this.urgency < other.urgency()) {
			return -1;
		} else if (this.urgency > other.urgency()) {
			return 1;
		} else {
			if (this.time_in > other.time_in()) {
				return -1;
			} else if (this.time_in < other.time_in()){
				return 1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public String toString() {
		return (name + ", " + urgency + ", " + time_in + ", " + position);
	}


//return the current position of the index being searchign for in teh queue
	public int posInQueue() {
		return this.position;
	}



}