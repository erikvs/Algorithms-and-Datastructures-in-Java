public class Plane {
    private int arrivalTime, departureTime, ID;


    public Plane(int arrivalTime, int ID){
        this.arrivalTime = arrivalTime;
        this.departureTime = 0;
        this.ID = ID;
    }

    public int totalTime(){
        return departureTime - arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departs) {
        this.departureTime = departs;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
