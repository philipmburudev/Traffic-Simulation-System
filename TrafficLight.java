public class TrafficLight{

   
public enum trafficlight{
  RED,
  YELLOW,
  GREEN
}

trafficlight currentColor;


//default constructor to initialize the traffic light
public TrafficLight() {

}

// setter for current color
public void setCurrentColor(String color) {
  if (color.compareTo("RED")==0) this.currentColor = trafficlight.RED;
  else if (color.compareTo("YELLOW")==0) this.currentColor = trafficlight.YELLOW;
  else if(color.compareTo("GREEN")==0) this.currentColor = trafficlight.GREEN;
}

// getter for current color
public trafficlight getCurrentColor() {
  return currentColor;
}



public class Main {

  public static void main(String[] args) {
    
  }
}
}

