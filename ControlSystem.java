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
        int count = 1;
        // Move emergency vehicles to the target lane
        while (!currentOutputLane.isEmpty()) {
            tempVehicle = currentOutputLane.peek();
            if (tempVehicle.getVehicleType().equals("emergency")) {
                currentOutputLane.remove(); // Remove from output lane
                targetInputLane = lanes[tempVehicle.getLaneToGo() - 1]; // Get target lane
                targetInputLane.getInputLane().add(tempVehicle); // Add to target lane input
            } else if (count == currentOutputLane.size()) {
                break; // Exit loop if a non-emergency vehicle is encountered
            }
            count++;
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

        Lane[] lanes = {lane1, lane2, lane3, lane4};

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

        lane1.showAllVehicles();

        ControlSystem.moveVehiclesOut(lanes, lane1);

        lane2.showAllVehicles();
    }
}
