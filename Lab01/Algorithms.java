public class Algorithms {

   public static void linearAlgorithm(long N) {

      long x;

      for(int i = 0; i < N; i++) {
         x = 1;
      }
   }

   public static void quadraticAlgorithm(long N) {

      long x;

      for(int i = 0; i < N; i++) {

         for(int j = 0; j < N; j++) {
            x = 1;
         }
      }
   }

   public static void cubicAlgorithm(long N) {

      long x;

      for(int i = 0; i < N; i++) {

         for(int j = 0; j < N; j++) {

            for(int k = 0; k < N; k++) {

               x = 1;
            }
         }
      }
   }

   public static void logarithmicAlgorithm(long N) {

      long x;

      for(long i = N; i > 1; i /= 2) {
         x=1;
      }
   }

   public static void NlogNAlgorithm(long N) {

      long x;

      for(long i = 0; i < N; i++) {
         for(long j = N; j > 1; j /= 2) {
            x=1;
         }
      }
   }
}