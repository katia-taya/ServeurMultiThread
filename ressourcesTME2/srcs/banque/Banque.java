package srcs.banque;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import srcs.persistances.Sauvergardable;

public class Banque implements Sauvergardable{

	private final Set<Client> clients;
	
	public Banque() {
		clients=new HashSet<>();
	}
	
	public Banque(InputStream in) {
		clients=new HashSet<>();
		DataInputStream din = new DataInputStream(in);
		try {
			int size = din.readInt();
			String nom;
			Class<? extends Sauvergardable> c ;
			Client cln;
			for(int i =0;i<size;i++) {
				nom = din.readUTF();
				try {
					c= Class.forName(nom).asSubclass(Sauvergardable.class);
					cln =(Client) c.getConstructor(InputStream.class).newInstance(in);				
					clients.add(cln);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int nbClients() {
		return clients.size();
	}
	
	public int nbComptes() {
		Set<Compte> comptes = new HashSet<>();
		for(Client c : clients) {
			comptes.add(c.getCompte());
		}
		return comptes.size();
	}
	
	public Client getClient(String nom) {
		for(Client c : clients) {
			if(c.getNom().equals(nom)) return c;
		}
		return null;
	}
	
	public boolean addNewClient(Client c) {
		return clients.add(c);
	}

	@Override
	//implémenter la methode sde suavgardable
	public void save(OutputStream out) throws IOException {
		DataOutputStream d = new DataOutputStream(out);
		d.writeInt(clients.size());
		for(Client c : clients) {
			d.writeUTF(c.getClass().getCanonicalName());
			c.save(out);
		}
	}
	

}
