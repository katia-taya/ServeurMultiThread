package srcs.service.test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMultithread {
	private final int port;
	private  final Class<? extends Service> Clazz;
	private Service setat;
	
	public ServeurMultithread (int port ,Class<? extends Service> Clazz ) throws InstantiationException, IllegalAccessException {
		this.port=port;
		this.Clazz=Clazz;
        for (Annotation a : Clazz.getAnnotations()) {
      	      if (a instanceof EtatGlobal) {
      	    	this.setat = Clazz.newInstance();
      	      }else {
      	    	  
      	      }		}
	}
	public void listen() throws IOException, InstantiationException, IllegalAccessException {
		@SuppressWarnings("resource")
		ServerSocket ecoute = new ServerSocket(port);
		
		while(true) {
			Socket con = ecoute.accept();
			RunnableClass rc = new RunnableClass( getService(), con);   //ou bien faire Service service =  getService(); THREAD T =NEW THREAD (() ->{t.execute  } );
			Thread th = new Thread(rc);
			th.start();	 
		}
	}
	private Service getService(){
			 try {
				           for (Annotation a : Clazz.getAnnotations()) {
                	      if (a instanceof EtatGlobal) {
                			  return  setat;
                		  }
                		  if (a instanceof SansEtat) {
                				return Clazz.getConstructor().newInstance();
                		  }
                	  }
 	 			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			   e.printStackTrace();
			}
			 throw  new IllegalStateException();
		 }
}

