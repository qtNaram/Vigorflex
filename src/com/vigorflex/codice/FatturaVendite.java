package com.vigorflex.codice;

import java.sql.Date;

public class FatturaVendite {
	

	private String nome, cognome, cf, indirizzo;
	private float importo;
	private int numero, sconto;
	private Date dataEmissione;
	
	public FatturaVendite(int numero, String nome, String cognome, String cf, String indirizzo, Date dataEmissione, float importo, int sconto) {
		super();
		this.numero=numero;
		this.nome=nome;
		this.cognome=cognome;
		this.cf=cf;
		this.indirizzo=indirizzo;
		this.importo=importo;
		this.sconto=sconto;
		this.dataEmissione=dataEmissione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importoTotale) {
		this.importo = importoTotale;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	
	
}
