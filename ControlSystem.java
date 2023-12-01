import java.util.HashMap;
import java.util.Queue;
import java.util.Random;

public class ControlSystem {

    static int numOfVehicles = 0;
    static HashMap<String, Integer> statistics = new HashMap<String, Integer>();
    public static void setStatistics() {
        statistics = new HashMap<>();
        statistics.put("emergency", 0);
        statistics.put("normal", 0);
        statistics.put("totalWaitingTime", 0);
    }

    public static void printStatistics() {
        System.out.println("Statistics:");
        System.out.println("Normal Vehicles Passed: " + statistics.get("normal"));
        System.out.println("Emergency Vehicles Passed: " + statistics.get("emergency"));
        System.out.println("Total Vehicles Passed: " + (statistics.get("emergency")+statistics.get("normal")));
        System.out.println("Average Wait Time: " + (statistics.get("totalWaitingTime") / (statistics.get("emergency")+statistics.get("normal"))) + "ms");
    }

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
                statistics.put("emergency", statistics.get("emergency") + 1);
                statistics.put("totalWaitingTime", statistics.get("totalWaitingTime") + (int)(System.currentTimeMillis() - tempVehicle.arrivalTime));
            } else {
                currentOutputLane.add(tempVehicle);
            }
        }

        // Move the remaining vehicles to the target lane
        while (!currentOutputLane.isEmpty()) {
            Vehicle tempVehicle = currentOutputLane.poll();
            Lane targetInputLane = lanes[tempVehicle.getLaneToGo() - 1];
            targetInputLane.getInputLane().add(tempVehicle);
            statistics.put("normal", statistics.get("normal") + 1);
            statistics.put("totalWaitingTime", statistics.get("totalWaitingTime") + (int)(System.currentTimeMillis() - tempVehicle.arrivalTime));
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
        ControlSystem.setStatistics();

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
        // Set initial traffic light color to GREEN for lane1
        lane1.trafficLight.setCurrentColor("GREEN");
        // Continue traffic simulation until all output lanes are empty
        while (!lane1.getOutputLane().isEmpty() || 
               !lane2.getOutputLane().isEmpty() ||
               !lane3.getOutputLane().isEmpty() ||
               !lane4.getOutputLane().isEmpty()) {

            // Check the current color of each traffic light
            System.out.println("Traffic light 1: " + lane1.trafficLight.getCurrentColor());
            System.out.println("Traffic light 2: " + lane2.trafficLight.getCurrentColor());
            System.out.println("Traffic light 3: " + lane3.trafficLight.getCurrentColor());
            System.out.println("Traffic light 4: " + lane4.trafficLight.getCurrentColor());

            // Move vehicles based on the current traffic light color
            if (lane1.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                // Move vehicles from lane1 to other lanes
                ControlSystem.moveVehiclesOut(lanes, lane1);
                lane1.showAllVehicles();
                // Generate vehicles for lane1
                // ControlSystem.generateVehicle(lane1);
            } else if (lane2.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                // Move vehicles from lane2 to lane3
                ControlSystem.moveVehiclesOut(lanes, lane2);
                lane2.showAllVehicles();
                // Generate vehicles for lane2
                // ControlSystem.generateVehicle(lane2);
            } else if (lane3.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                // Move vehicles from lane3 to other lanes
                ControlSystem.moveVehiclesOut(lanes, lane3);
                lane3.showAllVehicles();
                // Generate vehicles for lane3
                // ControlSystem.generateVehicle(lane3);
            } else if (lane4.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                // Move vehicles from lane4 to other lanes
                ControlSystem.moveVehiclesOut(lanes, lane4);
                lane4.showAllVehicles();
                // Generate vehicles for other lanes
                // ControlSystem.generateVehicle(lane4);
            }

            // Change the traffic light colors based on the specified sequence
            //  if trffic light was green, set to  yellow and the rest to red
             if (lane1.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                lane1.trafficLight.setCurrentColor("YELLOW");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");

            //  if trffic light was yellow, set to next light to Green and the rest to red
            } else if (lane1.trafficLight.getCurrentColor() == TrafficLight.trafficlight.YELLOW) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("GREEN");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");

            //  if trffic light was green, set to  yellow and the rest to red
            } else if (lane2.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("YELLOW");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");

                //  if trffic light was yellow, set to next light to Green and the rest to red
            } else if (lane2.trafficLight.getCurrentColor() == TrafficLight.trafficlight.YELLOW) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("GREEN");
                lane4.trafficLight.setCurrentColor("RED");
                
            //  if trffic light was green, set to  yellow and the rest to red
            } else if (lane3.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("YELLOW");
                lane4.trafficLight.setCurrentColor("RED");

                //  if trffic light was yellow, set to next light to Green and the rest to red
            } else if (lane3.trafficLight.getCurrentColor() == TrafficLight.trafficlight.YELLOW) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("GREEN");

                //  if trffic light was green, set to  yellow and the rest to red
            } else if (lane4.trafficLight.getCurrentColor() == TrafficLight.trafficlight.GREEN) {
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("YELLOW");

                //  if trffic light was yellow, set to next light to Green and the rest to red
            } else if (lane4.trafficLight.getCurrentColor() == TrafficLight.trafficlight.YELLOW) {
                lane1.trafficLight.setCurrentColor("GREEN");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");

            } else {
                // Default case, setting all traffic lights to RED
                lane1.trafficLight.setCurrentColor("RED");
                lane2.trafficLight.setCurrentColor("RED");
                lane3.trafficLight.setCurrentColor("RED");
                lane4.trafficLight.setCurrentColor("RED");
            }
        }

        // Print statistics
        ControlSystem.printStatistics();
    }
}
