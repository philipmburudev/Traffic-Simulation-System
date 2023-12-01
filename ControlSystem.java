import java.util.Queue;

public class ControlSystem {

    /**
     * This method is used to move vehicles from outputlane of one Lane to inputlane of another Lane
     * @param lanes
     * @param currentLane
     */
    public static void moveVehiclesOut(Lane[] lanes, Lane currentLane) {
        Vehicle tempVehicle = null;
        Lane targetInputLane = null;
        Queue<Vehicle> currentOutputLane = currentLane.getOutputLane();
        Queue<Vehicle> currentInputLane = currentLane.getInputLane();
        // Move emergency vehicles to the target lane
        while (!currentOutputLane.isEmpty()) {
            tempVehicle = currentOutputLane.peek();
            if (tempVehicle.getVehicleType().equals("emergency")) {
                currentOutputLane.remove(); // Remove from output lane
                targetInputLane = lanes[tempVehicle.getLaneToGo() - 1]; // Get target lane
                targetInputLane.getInputLane().add(tempVehicle); // Add to target lane input
            } else {
                break; // Exit loop if a non-emergency vehicle is encountered
            }
        }
        // Move the remaining vehicles to the target lane
        while (!currentOutputLane.isEmpty()) {
            tempVehicle = currentOutputLane.remove(); // Remove from output lane
            targetInputLane = lanes[tempVehicle.getLaneToGo() - 1]; // Get target lane
            targetInputLane.getInputLane().add(tempVehicle); // Add to target lane input
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
