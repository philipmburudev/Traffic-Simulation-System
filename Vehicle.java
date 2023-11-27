class Vehicle{
    String purpose;
    int carID;
    String name;

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

    public Vehicle(String purpose, int carID, String name) {
        this.purpose = purpose;
        this.carID = carID;
        this.name = name;
    }

}