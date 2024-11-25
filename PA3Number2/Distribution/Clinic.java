public class Clinic { 
    private int erThreshold;
    private PatientQueue patientQueue; 
    private int erPatients;
    private int processedPatients; 
    private int seenByDoctoPatients;
    private int walk; 
    //intializes and creates a clinic class that sets the threshold for the er
    public Clinic(int er_threshold) {    
        this.patientQueue = new PatientQueue();
        this.erThreshold = er_threshold;
        this.processedPatients = 0; 
        this.erPatients = 0;
        this.walk = 0; 
    }
    //returnt eh threshold value  
    public int er_threshold() {
        return erThreshold; 
    }
    //return teh process will check to ensure that the patient being checked in
    //will be in the proper position that it's needed and if it's 
    //an urgency level over the erTHreshold it should send it ot the er and not add to the
    //queue 
    public String process(String name, int urgency) {
         Patient newPatient = new Patient(name, urgency); 
         if (urgency > erThreshold) { 
            erPatients++;
            return null; 
        } 
        patientQueue.insert(newPatient);
        processedPatients++;
        return name; 
    }
    //tooks are teh nextPatient if it's there and if not then return 
    //null stating that th eend of the queue is done for and you 
    //wouldn't have to iterate throught it 
    public String seeNext() {
        Patient temporary;
        try {
            temporary = patientQueue.removeNext();
             seenByDoctoPatients++;
            return temporary.name();
        } catch (EmptyQueueException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
            return null; 
        }  
    //if while waiting the patient's urgency level is increased then shift around 
    //and update if they need to go into the er or not 
    public boolean handle_emergency(String name, int urgency) {
        if (urgency > erThreshold) { 
            erPatients++;
             patientQueue.remove(name); 
            return true;
        }
        patientQueue.update(name, urgency); 
        return false; 
    } 
    public void walk_out(String name) { 
        Patient temporary = patientQueue.remove(name); 
        if (temporary != null) { 
            walk++; 
        } 
    }
    public int processed() { 
        return this.processedPatients; 
    } 
    public int sentToER() {
        return this.erPatients; 
    } 
    public int seenByDoctor() { 
        return this.seenByDoctoPatients;
    }
    public int walkedOut() { 
        return this.walk;
    }
}