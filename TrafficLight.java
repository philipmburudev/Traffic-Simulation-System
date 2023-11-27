import java.util.Scanner;


public class TrafficLight{

public enum Lanes{
    L1,
    L2,
    L3,
    L4
}
   
public enum trafficlight{
  RED,
  YELLOW,
  GREEN
}


public class Main {

  public static void main(String[] args) {
    trafficlight color = trafficlight.YELLOW;

    System.out.println("Type coloor");
    Scanner scan = new Scanner(System.in);
    String currentcolor = scan.nextLine();
    
    if(currentcolor.compareTo("RED")==0) color = trafficlight.RED;
    else if (currentcolor.compareTo("YELLOW")==0) color = trafficlight.YELLOW;
    else if(currentcolor.compareTo("GREEN")==0) color = trafficlight.GREEN;
        
    //LANE 2 AND LANE 4
    switch(color) {
      case RED:
      //method to add car and remove
        System.out.println("Lane 1");
        break;




      case YELLOW:
         System.out.println("Lane 2");
        break;
      case GREEN:
        System.out.println("Lane 3");
        break;
    }


    
    Lanes lanes = Lanes.L1;
     switch(lanes) {
      case L1:
        System.out.println("Lane 1");
        break;
      case L2:
         System.out.println("Lane 2");
        break;
      case L3:
        System.out.println("Lane 3");
        break;
      case L4:
        System.out.println("Lane 3");
        break;
    }

    
    






   
  }
}
}
