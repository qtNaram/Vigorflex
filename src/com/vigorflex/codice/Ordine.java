package com.vigorflex.codice;

import java.sql.Date;

public class Ordine {
	
	private String codice, nome, cognome, cf, indirizzo, telefono, email, modalitaConsegna, stato;
	private float importoTotale;
	private int sconto, numFatturaVend;
	private Date dataAcquisto;
	
	public Ordine(String codice, String nome, String cognome, String cf, String indirizzo, String telefono,
			String email, String modalitaConsegna, String stato, float importoTotale, int sconto, int numFatturaVend, Date dataAcquisto) {
		super();
		this.codice=codice;
		this.nome=nome;
		this.cognome=cognome;
		this.cf=cf;
		this.indirizzo=indirizzo;
		this.telefono=telefono;
		this.email=email;
		this.modalitaConsegna=modalitaConsegna;
		this.stato=stato;
		this.importoTotale=importoTotale;
		this.sconto=sconto;
		this.numFatturaVend=numFatturaVend;
		this.dataAcquisto=dataAcquisto;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getModalitaConsegna() {
		return modalitaConsegna;
	}

	public void setModalitaConsegna(String modalitaConsegna) {
		this.modalitaConsegna = modalitaConsegna;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public float getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(float importoTotale) {
		this.importoTotale = importoTotale;
	}

	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	public int getNumFatturaVend() {
		return numFatturaVend;
	}

	public void setNumFatturaVend(int numFatturaVend) {
		this.numFatturaVend = numFatturaVend;
	}

	public Date getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}
	

	
}
