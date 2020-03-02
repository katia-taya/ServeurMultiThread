package srcs.banque;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import srcs.persistances.Sauvergardable;

public class Client implements Sauvergardable {

	
	private final String nom;
	private final Compte compte;

	
	public Client(String nom, Compte compte) {
		this.nom=nom;
		this.compte=compte;

	}
	public Client (InputStream in) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		DataInputStream din = new DataInputStream(in);
		 nom = din.readUTF();
		 Class<? extends Sauvergardable> c = Class.forName(din.readUTF()).asSubclass(Sauvergardable.class);
		 compte = (Compte) c.getConstructor(InputStream.class).newInstance(in);
	}
	public Client (InputStream in,Set<Client> clients) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		DataInputStream din = new DataInputStream(in);
		String id;
		 nom = din.readUTF();
		 id= din.readUTF();
		 Compte compte2 = check(clients,id);
		 if(compte2==null) {
		 Class<? extends Sauvergardable> c = Class.forName(din.readUTF()).asSubclass(Sauvergardable.class);
		 compte = (Compte) c.getConstructor(InputStream.class).newInstance(in);
		 }
		 else {
			compte=compte2;
		 }
		
	}
	public String getNom() {
		return nom;
	}


	public Compte getCompte() {
		return compte;
	}

	@Override
	public boolean equals(Object o) {
		if(o==this) return true;
		if(o==null) return false;
		if(!(o instanceof Compte)) return false;
		Client other= (Client) o;
		return other.nom.equals(nom);
	}

	@Override
	public void save(OutputStream out) throws IOException {
		DataOutputStream dout = new DataOutputStream(out);
		dout.writeUTF(nom);
		dout.writeUTF(compte.getId());
		dout.writeUTF(compte.getClass().getCanonicalName());
		compte.save(out);
	}
	
	public Compte check(Set<Client> clients,String id) {
		for(Client c: clients) 
			if(c.getCompte().getId().equals(id))
				return c.getCompte();
		return null;
	}
	
}
