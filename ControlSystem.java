import java.util.Queue;
import java.util.Random;

public class ControlSystem {

    /**
     * This method is used to move vehicles from outputlane of one Lane to inputlane of another Lane
     * @param lanes
     * @param currentLane
     */
    public static void moveVehiclesOut(Lane[] lanes, Lane currentLane) {
        Queue<Vehicle> currentOutputLane = currentLane.getOutputLane();

        int size = currentOutputLane.size();

        for (int i = 0; i < size; i++) {
            Vehicle tempVehicle = currentOutputLane.poll();

            if (tempVehicle.getVehicleType().equals("emergency")) {
                System.out.println("Emergency vehicle found " + tempVehicle.getCarID());
                Lane targetInputLane = lanes[tempVehicle.getLaneToGo() - 1];
                targetInputLane.getInputLane().add(tempVehicle);
            } else {
                currentOutputLane.add(tempVehicle);
            }
        }

        // Move the remaining vehicles to the target lane
        while (!currentOutputLane.isEmpty()) {
            Vehicle tempVehicle = currentOutputLane.poll();
            Lane targetInputLane = lanes[tempVehicle.getLaneToGo() - 1];
            targetInputLane.getInputLane().add(tempVehicle);
        }
    }

    public static void generateVehicle(Lane lane) {
        int[] directions = {1, 2, 3, 4};
        long arrivalTime = System.currentTimeMillis();

        // generating 1-5 vehicles
        int numOfVehicles = new Random().nextInt(1,6);
        for (int i=0; i<numOfVehicles; i++) {
            // Generate a random direction (e.g., 1, 2, 3, 4
            int direction = directions[new Random().nextInt(directions.length)];
            // Generate emergency vehicles with a certain probability (e.g., 10%)
            boolean isEmergency = new Random().nextInt(10) == 0;

            // Vehicle c1 = new Vehicle("normal", 1, "car1", 2);
            Vehicle vehicle = new Vehicle(isEmergency ? "emergency" : "normal", Vehicle.id, "car"+Vehicle.id, direction);
            //queues.get(direction).offer(vehicle);
            Lane.putVehicleInOutputLane(vehicle, lane);
        }
    }

    public static void main(String[] args) {
        // System.out.println("Type traffic color");
        Lane lane1 = new Lane(1, "lane1");
        Lane lane2 = new Lane(2, "lane2");
        Lane lane3 = new Lane(3, "lane3");
        Lane lane4 = new Lane(4, "lane4");

        Lane[] lanes = {lane1, lane2, lane3, lane4};

        for (Lane lane : lanes) {
            ControlSystem.generateVehicle(lane);
        }

        lane1.showAllVehicles();

        ControlSystem.moveVehiclesOut(lanes, lane1);

        lane2.showAllVehicles();
    }
}
