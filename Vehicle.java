class Vehicle{
    String purpose;
    int carID;
    String name;
    int lane_to_go = -1;
    static int id = 1;
    long arrivalTime;

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle(String purpose, int carID, String name, int lane_to_go) {
        this.purpose = purpose;
        this.carID = carID;
        this.name = name;
        this.lane_to_go = lane_to_go;
        this.arrivalTime = System.currentTimeMillis();
        Vehicle.id++;
    }

    public String getVehicleType() {
        return purpose;
    }

    public int getLaneToGo() {
        return lane_to_go;
    }

}