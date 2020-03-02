package srcs.service.test;

public interface Calculatrice {
       
	public int add(int a , int b);
	public int sous(int a , int b);
	public int mult(int a , int b);
	public RestDiv div();
	
	
	
	
	
	 public class RestDiv{
		
		
		public int getQuotient(int a , int b) {
			return (a / b)  ;
		}

		public int getReste(int a , int b) {
			return (a % b);
		} 
	 }
}
