package srcs.persistance.test;

import static org.junit.Assert.*;
import static srcs.persistances.PersistanceTools.*;

import org.junit.Test;

import srcs.banque.Compte;
import srcs.persistances.Sauvergardable;

import java.io.IOException;
public class SauvegardableTest {
		
	@Test
	public void testSauvegardable() {
		try {
			String fichier = "/tmp/save";
			Compte cpt = new Compte("cpt1");
			
			cpt.crediter(5.0);
			cpt.debiter(2.0);
			assertEquals(3,cpt.getSolde(),0);
			save(fichier, cpt);
			
			Sauvergardable bis = load(fichier);
			assertTrue(bis instanceof Compte);
			System.out.println(1);
			assertEquals(cpt, bis);
			assertEquals(3,((Compte)bis).getSolde(),0);
		}catch(IOException | ReflectiveOperationException e) {
			assertTrue(false);
		}
	}

}
