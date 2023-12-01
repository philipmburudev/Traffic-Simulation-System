public class ControlSystem {
    public static void main(String[] args) {
        // System.out.println("Type traffic color");
    Lane lane1 = new Lane(1, "lane1");
    Lane lane2 = new Lane(2, "lane2");
    Lane lane3 = new Lane(3, "lane3");
    Lane lane4 = new Lane(4, "lane4");

    Vehicle c1 = new Vehicle("normal", 1, "car1", 2);
    Vehicle c2 = new Vehicle("normal", 2, "car2", 2);
    Vehicle c3 = new Vehicle("emergency", 3, "car3", 2);
    Vehicle c4 = new Vehicle("normal", 4, "car4", 2);
    Vehicle c5 = new Vehicle("normal", 5, "car5", 2);

    lane1.putVehicleInOutputLane(c1);
    lane1.putVehicleInOutputLane(c2);
    lane1.putVehicleInOutputLane(c3);
    lane1.putVehicleInOutputLane(c4);
    lane1.putVehicleInOutputLane(c5);

    System.out.println("lane1");
    for (Vehicle v : lane1.getVehiclesFromOutputLane()) {
      System.out.println(v.getName());
    }
    System.out.println("lane2");

    lane2.putVehicle(lane1.getVehiclesFromOutputLane().remove());
    lane2.putVehicle(lane1.getVehiclesFromOutputLane().remove());
    lane2.putVehicle(lane1.getVehiclesFromOutputLane().remove());
    lane2.putVehicle(lane1.getVehiclesFromOutputLane().remove());
    lane2.putVehicle(lane1.getVehiclesFromOutputLane().remove());

    for (Vehicle v : lane2.getVehiclesFromInputLane()) {
      System.out.println(v.getName());
    }
    }
}
