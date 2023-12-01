import java.util.Queue;

public class ControlSystem {

    public static void moveVehiclesOut(Lane[] lanes, Lane currentLane) {
        Vehicle tempVehicle = null;
        Queue<Vehicle> currentOutputLane = currentLane.getOutputLane();
        Queue<Vehicle> currentInputLane = currentLane.getInputLane();
        while (!currentOutputLane.isEmpty()) {
            if (currentOutputLane.peek().getVehicleType().compareTo("emergency") == 0) {
                tempVehicle = currentOutputLane.remove();
                currentInputLane.add(tempVehicle);

            } else if (currentOutputLane.peek().getVehicleType().compareTo("emergency") == 0) {
                tempVehicle = currentOutputLane.remove();
                currentInputLane.add(tempVehicle);
            }
        }
    }

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

        Lane.putVehicleInOutputLane(c1, lane1);
        Lane.putVehicleInOutputLane(c2, lane1);
        Lane.putVehicleInOutputLane(c3, lane1);
        Lane.putVehicleInOutputLane(c4, lane1);
        Lane.putVehicleInOutputLane(c5, lane1);

        System.out.println("lane1");
        for (Vehicle v : lane1.getOutputLane()) {
            System.out.println(v.getName());
        }
        System.out.println("lane2");

        lane2.putVehicleIn(lane1.getOutputLane().remove());
        lane2.putVehicleIn(lane1.getOutputLane().remove());
        lane2.putVehicleIn(lane1.getOutputLane().remove());
        lane2.putVehicleIn(lane1.getOutputLane().remove());
        lane2.putVehicleIn(lane1.getOutputLane().remove());

        for (Vehicle v : lane2.getInputLane()) {
            System.out.println(v.getName());
        }
    }
}
