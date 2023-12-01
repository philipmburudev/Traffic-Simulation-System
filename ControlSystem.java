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
        int[] directions1 = {2, 3, 4};
        int[] directions2 = {1, 3, 4};
        int[] directions3 = {1, 2, 4};
        int[] directions4 = {1, 2, 3};

        int[][] directions = {directions1, directions2, directions3, directions4};
        // Generate a random direction (e.g., 1, 2, 3, 4)
        int laneMumber = lane.getLaneNumber();

        directions2 = directions[laneMumber - 1];
        

        long arrivalTime = System.currentTimeMillis();

        // generating 1-5 vehicles
        int numOfVehicles = new Random().nextInt(1,6);
        for (int i=0; i<numOfVehicles; i++) {
            int direction = directions2[new Random().nextInt(directions2.length)];
            
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

        // lane1.showAllVehicles();
        // ControlSystem.moveVehiclesOut(lanes, lane1);
        // lane2.showAllVehicles();

        // Contol based on Traffic Light
        /**
         * lane1 is north
         * lane2 is east
         * lane3 is south
         * lane4 is west
         */
        while (!lane1.getOutputLane().isEmpty() || 
               !lane2.getOutputLane().isEmpty() ||
               !lane3.getOutputLane().isEmpty() ||
               !lane4.getOutputLane().isEmpty()) {
            // Check the traffic light
            if (lane1.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                // Move vehicles from lane1 to lane2
                ControlSystem.moveVehiclesOut(lanes, lane1);
                // Generate vehicles for lane1
                // ControlSystem.generateVehicle(lane1);
            } else if (lane2.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                // Move vehicles from lane2 to lane3
                ControlSystem.moveVehiclesOut(lanes, lane2);
                // Generate vehicles for lane2
                // ControlSystem.generateVehicle(lane2);
            } else if (lane3.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                // Move vehicles from lane3 to lane4
                ControlSystem.moveVehiclesOut(lanes, lane3);
                // Generate vehicles for lane3
                // ControlSystem.generateVehicle(lane3);
            } else if (lane4.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                // Move vehicles from lane4 to lane1
                ControlSystem.moveVehiclesOut(lanes, lane4);
                // Generate vehicles for lane4
                // ControlSystem.generateVehicle(lane4);
            }

            // Change the traffic light
            if (lane1.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                lane1.trafficLight.setCurrentColor("YELLOW");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");

            } else if (lane1.trafficLight.getCurrentColor() == TrafficLight.trafficlight.YELLOW) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("GREEN");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");

            } else if (lane2.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("YELLOW");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");

            } else if (lane2.trafficLight.getCurrentColor() == TrafficLight.trafficlight.YELLOW) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("GREEN");
                lane4.trafficLight.setCurrentColor("RED");

            } else if (lane3.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("YELLOW");
                lane4.trafficLight.setCurrentColor("RED");

            } else if (lane3.trafficLight.getCurrentColor() == TrafficLight.trafficlight.YELLOW) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("GREEN");

            } else if (lane4.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("YELLOW");

            } else if (lane4.trafficLight.getCurrentColor() == TrafficLight.trafficlight.YELLOW) {
                lane1.trafficLight.setCurrentColor("GREEN");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");

            } else {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");
            }
        }
    }
}
