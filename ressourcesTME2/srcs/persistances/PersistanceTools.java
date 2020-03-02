package srcs.persistances;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import srcs.banque.Compte;

public class PersistanceTools {


	public static void saveArrayInt(String f , int[] tab) throws IOException{
		FileOutputStream fout = new FileOutputStream("f");

		for (int i=0; i<tab.length;i++) {
			fout.write(tab[i]);
		}
		fout.close();
	}

	public static int[] loadArrayInt(String fichier)throws IOException {
		FileInputStream fin= new  FileInputStream("fichier");
		FileInputStream fin1= new  FileInputStream("fichier");

		int x ,cmp=0;
		while(fin.read()!=-1) {
			cmp ++ ;
		}
		int tab[] = new int [cmp];
		int i =0 ;
		while ((x=fin.read())!=-1) {
			tab[i]= x;
			i++;
		}

		fin.close();
		fin1.close();
		return tab  ;
	}
	public static void saveCompte(String f, Compte e) throws IOException{
		FileOutputStream fout = new FileOutputStream(f);
		e.save(fout);
	}
	
	public static Compte loadCompte(String f)throws IOException{
		return new  Compte(new FileInputStream(f));
	}
	
	public static void save(String fichier , Sauvergardable s)throws IOException {
		FileOutputStream fos= new FileOutputStream(fichier);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeUTF(s.getClass().getCanonicalName());
		s.save(dos);
	}
	public static  Sauvergardable load(String fichier) throws IOException,ClassNotFoundException, InstantiationException,IllegalAccessException,IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		FileInputStream fin = new FileInputStream(fichier);
		DataInputStream din =new DataInputStream(fin);
		String name ; 
		name = din.readUTF();
		Class<? extends Sauvergardable> c = Class.forName(name).asSubclass(Sauvergardable.class);
		
		return (Sauvergardable) c.getConstructor(InputStream.class).newInstance(fin);
	}
	


}
