package srcs.service.test;

import java.io.IOException;
import java.net.Socket;

public class RunnableClass implements Runnable {
         
	    Service service;
	    Socket sock;
	
	   public RunnableClass(Service service , Socket sock) {
		   this.service = service;
		   this.sock= sock;
		   
	}
	   
	@Override
	public void run() {
		
		service.execute(sock);
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
