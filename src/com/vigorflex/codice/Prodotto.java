package com.vigorflex.codice;

public class Prodotto {
	
	private String nome, settoreMagazzinoCommesse, settoreMagazzinoDettaglio, settoreNegozio;
	private int qtaMagazzinoCommmesse, qtaMagazzinoDettaglio, qtaNegozio;
	private float prezzo;
	
	public Prodotto(String nome, String settoreMagazzinoCommesse, String settoreMagazzinoDettaglio,
			String settoreNegozio, int qtaMagazzinoCommmesse, int qtaMagazzinoDettaglio, int qtaNegozio, float prezzo) {
		super();
		this.nome = nome;
		this.settoreMagazzinoCommesse = settoreMagazzinoCommesse;
		this.settoreMagazzinoDettaglio = settoreMagazzinoDettaglio;
		this.settoreNegozio = settoreNegozio;
		this.qtaMagazzinoCommmesse = qtaMagazzinoCommmesse;
		this.qtaMagazzinoDettaglio = qtaMagazzinoDettaglio;
		this.qtaNegozio = qtaNegozio;
		this.prezzo = prezzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSettoreMagazzinoCommesse() {
		return settoreMagazzinoCommesse;
	}

	public void setSettoreMagazzinoCommesse(String settoreMagazzinoCommesse) {
		this.settoreMagazzinoCommesse = settoreMagazzinoCommesse;
	}

	public String getSettoreMagazzinoDettaglio() {
		return settoreMagazzinoDettaglio;
	}

	public void setSettoreMagazzinoDettaglio(String settoreMagazzinoDettaglio) {
		this.settoreMagazzinoDettaglio = settoreMagazzinoDettaglio;
	}

	public String getSettoreNegozio() {
		return settoreNegozio;
	}

	public void setSettoreNegozio(String settoreNegozio) {
		this.settoreNegozio = settoreNegozio;
	}

	public int getQtaMagazzinoCommmesse() {
		return qtaMagazzinoCommmesse;
	}

	public void setQtaMagazzinoCommmesse(int qtaMagazzinoCommmesse) {
		this.qtaMagazzinoCommmesse = qtaMagazzinoCommmesse;
	}

	public int getQtaMagazzinoDettaglio() {
		return qtaMagazzinoDettaglio;
	}

	public void setQtaMagazzinoDettaglio(int qtaMagazzinoDettaglio) {
		this.qtaMagazzinoDettaglio = qtaMagazzinoDettaglio;
	}

	public int getQtaNegozio() {
		return qtaNegozio;
	}

	public void setQtaNegozio(int qtaNegozio) {
		this.qtaNegozio = qtaNegozio;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}


}
