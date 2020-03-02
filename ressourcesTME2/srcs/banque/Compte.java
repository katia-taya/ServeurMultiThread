package srcs.banque;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import srcs.persistances.Sauvergardable;

public class Compte implements Sauvergardable{



	private final String id;
	private double solde;


	public Compte(String id) {
		this.id=id;	
		this.solde=0.0;
	}
	//constructeur qui permet d'initialier le flux in 
	public Compte(InputStream in)throws IOException{
		DataInputStream din = new DataInputStream(in);		
		this.solde = din.readDouble();
		this.id = din.readUTF();
	}


	public String getId() {
		return id;
	}

	public double getSolde() {
		return solde;
	}

	public void crediter(double montant) {
		solde += montant;
	}

	public void debiter(double montant) {
		solde -= montant;
	}

	@Override
	public boolean equals(Object o) {
		if(o==this) return true;
		if(o==null) return false;
		if(!(o instanceof Compte)) return false;
		Compte other= (Compte) o;
		return other.id.equals(id);
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	//methode save qui permet d'écrire  l'instance dans le flux out 
	public void  save(OutputStream out)throws IOException {
		DataOutputStream d = new DataOutputStream(out);
		//d.writeUTF(this.getClass().getCanonicalName());
		d.writeDouble(solde);
		d.writeUTF(id);
	}
   

}
