
import java.util.Queue;
import java.util.LinkedList;
import java.util.Queue;



    public class Lane {
        private Queue<Vehicle> inputlane = new LinkedList<>();
        private Queue<Vehicle> outputlane = new LinkedList<>();
        //lane number and name
        private int laneNumber;
        private String laneName;
        // traffic light obj
        private TrafficLight trafficLight = new TrafficLight();

    public Lane(int laneNumber, String laneName) {
        this.laneNumber = laneNumber;
        this.laneName = laneName;
        this.trafficLight.setCurrentColor("RED");
    }

    // put a Vehicle in a lane
    public void putVehicle(Vehicle vehicle) {
        inputlane.add(vehicle);
    }

    public void putVehicleInOutputLane(Vehicle vehicle) {
        outputlane.add(vehicle);
    }

    public Queue<Vehicle> getVehiclesFromOutputLane() {
        return outputlane;
    }

    public Queue<Vehicle> getVehiclesFromInputLane() {
        return inputlane;
    }

    

    
}
