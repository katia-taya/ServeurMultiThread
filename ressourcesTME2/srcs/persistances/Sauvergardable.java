package srcs.persistances;

import java.io.IOException;
import java.io.OutputStream;

public interface Sauvergardable {
	
 public void save(OutputStream out)throws IOException;

}
