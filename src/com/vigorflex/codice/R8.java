package com.vigorflex.codice;

public class R8 {
	
	private String prodottofinito, materiaprima;
	private float quantita;
	
	public R8(String prodottofinito, String materiaprima, float quantita) {
		this.prodottofinito=prodottofinito;
		this.materiaprima=materiaprima;
		this.quantita=quantita;
	}

	public String getProdottofinito() {
		return prodottofinito;
	}

	public void setProdottofinito(String prodottofinito) {
		this.prodottofinito = prodottofinito;
	}

	public String getMateriaprima() {
		return materiaprima;
	}

	public void setMateriaprima(String materiaprima) {
		this.materiaprima = materiaprima;
	}

	public float getQuantita() {
		return quantita;
	}

	public void setQuantita(float quantita) {
		this.quantita = quantita;
	}

	
	

}
