package srcs.service.test;

public interface Annuaire {

	
	  public String lookup(String nom);
	  public void bind(String valeur , String nom);
	  public void unbind(String nom);
}
