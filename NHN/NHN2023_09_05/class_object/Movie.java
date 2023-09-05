public class Movie {
  
  public static int wScript = 6;
  public static int wActing = 13;
  public static int wDirection = 11;
  
  public static int movieRating (int s, int a, int d) {
	  return wScript * s + wActing * a + wDirection * d;
  }
}