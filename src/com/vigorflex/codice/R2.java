package com.vigorflex.codice;

public class R2 {
	
	private String ordine, prodotto;
	private int qta;
	
	public R2(String ordine, String prodotto, int qta) {
		this.ordine=ordine;
		this.prodotto=prodotto;
		this.qta=qta;
	}

	public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public String getProdotto() {
		return prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public int getQta() {
		return qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}
	
	

}
