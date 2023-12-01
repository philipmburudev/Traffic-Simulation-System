class Vehicle{
    String purpose;
    int carID;
    String name;
    int lane_to_go = -1;

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
    }

    public String getVehicleType() {
        return purpose;
    }

}