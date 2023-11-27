import java.util.Scanner;


public class TrafficLight{

   
public enum trafficlight{
  RED,
  YELLOW,
  GREEN
}


//default constructor
public TrafficLight() {
}



public class Main {

  public static void main(String[] args) {
    trafficlight color = trafficlight.YELLOW;

    System.out.println("Type traffic color");
    Scanner scan = new Scanner(System.in);
    String currentcolor = scan.nextLine();
    
    if(currentcolor.compareTo("RED")==0) color = trafficlight.RED;
    else if (currentcolor.compareTo("YELLOW")==0) color = trafficlight.YELLOW;
    else if(currentcolor.compareTo("GREEN")==0) color = trafficlight.GREEN;
        
    switch(color) {
      case RED:
        System.out.println("Lane 1");
        break;
      case YELLOW:
         System.out.println("Lane 2");
        break;
      case GREEN:
        System.out.println("Lane 3");
        break;
    }


    
   

    
    






   
  }
}
}
