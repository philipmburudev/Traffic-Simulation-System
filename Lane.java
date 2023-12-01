
// The `import` statements are used to import the `Queue` and `LinkedList` classes from the `java.util`
// package.
import java.util.Queue;
import java.util.LinkedList;

    public class Lane {
        private Queue<Vehicle> inputlane = new LinkedList<>(); // cars going into the lane
        private Queue<Vehicle> outputlane = new LinkedList<>(); // cars going out of the lane
        //lane number and name
        private int laneNumber;
        private String laneName;
        // traffic light obj
        TrafficLight trafficLight = new TrafficLight();

       // The `public Lane(int laneNumber, String laneName)` is a constructor method for the `Lane`
       // class. It takes two parameters, `laneNumber` and `laneName`, and initializes the `laneNumber`
       // and `laneName` instance variables with the values passed as arguments. Additionally, it sets
       // the current color of the `trafficLight` object to "RED".
        public Lane(int laneNumber, String laneName) {
            this.laneNumber = laneNumber;
            this.laneName = laneName;
            this.trafficLight.setCurrentColor("RED");
        }

        // setter for lane number
        public void setLaneNumber(int laneNumber) {
            this.laneNumber = laneNumber;
        }

        // getter for lane number
        public int getLaneNumber() {
            return laneNumber;
        }

        // setter for lane name
        public void setLaneName(String laneName) {
            this.laneName = laneName;
        }

        // getter for lane name
        public String getLaneName() {
            return laneName;
        }

        // put a Vehicle in a lane
        public void putVehicleIn(Vehicle vehicle) {
            inputlane.add(vehicle);
        }

        public Vehicle moveVehicleOut() {
            return outputlane.remove();
        }

        // put vehicle in output lane
        public static void putVehicleInOutputLane(Vehicle vehicle, Lane lane) {
            lane.outputlane.add(vehicle);
        }

        /**
         * The function returns the output lane of vehicles.
         * 
         * @return The method is returning a Queue of Vehicle objects.
         */
        public Queue<Vehicle> getOutputLane() {
            return outputlane;
        }

        /**
         * The function returns the input lane of vehicles as a queue.
         * 
         * @return The method is returning a Queue of Vehicle objects.
         */
        public Queue<Vehicle> getInputLane() {
            return inputlane;
        }

        public void showAllVehicles() {
            System.out.println("Lane number: " + laneNumber + "," + " Lane name: " + laneName);
            System.out.println("Input lane: ");
            for (Vehicle v : inputlane) {
                System.out.println(v.getName());
            }
            System.out.println("Output lane: ");
            for (Vehicle v : outputlane) {
                System.out.println(v.getName());
            }
            System.out.println("\n");
        }
    }
