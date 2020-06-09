package models;

public class Livre {
	private int CodeLivre;
	private String TitreLivre;
	private double PrixLivre;
	
	public int getCodeLivre() {
		return CodeLivre;
	}
	public void setCodeLivre(int codeLivre) {
		CodeLivre = codeLivre;
	}
	public String getTitreLivre() {
		return TitreLivre;
	}
	public void setTitreLivre(String titreLivre) {
		TitreLivre = titreLivre;
	}
	public double getPrixLivre() {
		return PrixLivre;
	}
	public void setPrixLivre(double prixLivre) {
		PrixLivre = prixLivre;
	}
}
