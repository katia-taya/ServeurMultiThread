package srcs.service.test;

import java.net.Socket;

public class CalculatriceService implements Service , Calculatrice{

	@Override
	public int add(int a, int b) {
		
		return a+b;
	}

	@Override
	public int sous(int a, int b) {
		
		return a-b;
	}

	@Override
	public int mult(int a, int b) {
		
		return a*b;
	}

	@Override
	public RestDiv div() {
	

	}

	@Override
	public void execute(Socket connexion) {
		
		
	}

}
