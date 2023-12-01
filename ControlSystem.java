public class ControlSystem {

    // public void moveVehiclesOut(Lane[] lanes) {
    //     Vehicle tempVehicle = null;
    //     while (!outputlane.isEmpty()) {
    //         if (outputlane.peek().getVehicleType().compareTo("emergency") == 0) {
    //             outputlane.add(inputlane.remove());
    //         } else if (outputlane.peek().getVehicleType().compareTo("normal") == 0) {
    //             outputlane.add(inputlane.remove());
    //         }
    //     }
    // }

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
