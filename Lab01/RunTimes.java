public class RunTimes {
   
   public static void main(String[] args) {
      
      long startTime;
      long endTime;

      Algorithms test = new Algorithms();

      System.out.println("Logarithmic algorithm's running times:");
      
      startTime = System.nanoTime() / 1000000;
      for(long i = 1000; i < 100000000; i *= 2){

         test.logarithmicAlgorithm(i);

         endTime = System.nanoTime() / 1000000;

         System.out.println("T(" + i + ") = " + (endTime - startTime));
      }

      System.out.println();

      System.out.println("Linear algorithm's running times:");
      
      startTime = System.nanoTime() / 1000000;
      for(long i = 1000; i < 100000000; i *= 2){

         test.linearAlgorithm(i);

         endTime = System.nanoTime() / 1000000;

         System.out.println("T(" + i + ") = " + (endTime - startTime));
      }

      System.out.println();

      System.out.println("NlogN algorithm's running times:");
      
      startTime = System.nanoTime() / 1000000;
      for(long i = 1000; i < 100000000; i *= 2){

         test.NlogNAlgorithm(i);

         endTime = System.nanoTime() / 1000000;

         System.out.println("T(" + i + ") = " + (endTime - startTime));
      }

      System.out.println();

      System.out.println("Quadratic algorithm's running times:");
      
      startTime = System.nanoTime() / 1000000;
      for(long i = 1000; i <= 512000; i *= 2){

         test.quadraticAlgorithm(i);

         endTime = System.nanoTime() / 1000000;

         System.out.println("T(" + i + ") = " + (endTime - startTime));
      }

      System.out.println();

      System.out.println("Cubic algorithm's running times:");
      
      startTime = System.nanoTime() / 1000000;
      for(long i = 1000; i <= 8000; i *= 2){

         test.cubicAlgorithm(i);

         endTime = System.nanoTime() / 1000000;

         System.out.println("T(" + i + ") = " + (endTime - startTime));
      }

      

      
   }
}