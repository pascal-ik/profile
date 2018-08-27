
public class Test{

   public static double calculateTip(double cost){
      //double tip = 0.0;
      //tip = cost * 0.15;
      return cost * 0.15;
   }

   public static void main(String[]args){
   
      String google = "Go";
      while(google.length() < 100){
         google = google + "o";
      }
      
      
      System.out.println(google);
      System.out.println(calculateTip(100));
   }
}