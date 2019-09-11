package com.vigorflex.dati;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

import com.vigorflex.codice.FatturaVendite;
import com.vigorflex.codice.Ordine;
import com.vigorflex.gui.HomeAreaAmministrativaGUI;

public class OrdineDAO {
	
	private Connection con;
	
	public OrdineDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("vigorflex.properties"));
		con = DriverManager.getConnection(prop.getProperty("dburl") + TimeZone.getDefault().getID(), prop.getProperty("user"), prop.getProperty("password"));

	}
	
	
	/**
	 * Accede alla base di dati e restituisce l'elenco degli ordini salvati
	 * @return Lista di Ordini
	 * @throws Exception
	 */
	public List<Ordine> getListaOrdini() throws Exception{
		List<Ordine> lista = new ArrayList<>();
		
		Statement s = null;
		ResultSet rs = null;
		
		try {
			s = con.createStatement();
			rs = s.executeQuery("select * from ordini");
			
			while(rs.next()) {
				Ordine ordineTmp = convertiRigaOrdine(rs);
				lista.add(ordineTmp);
			}
			
			return lista;
		}finally {
			chiudi(s, rs);

		}
	}
	
	
	/**
	 * Accede alla base di dati ed effettua una ricerca tra gli ordini secondo un determinato criterio, in assenza di un intervallo di date specificato
	 * 
	 * @param String criterio
	 * @return Lista di Ordini
	 * @throws Exception
	 */
	public List<Ordine> ricercaPerCriterio(String codice) throws Exception {
		List<Ordine> lista = new ArrayList<>();

		PreparedStatement s = null;
		ResultSet rs = null;

		try {
			String criterio = codice;
			int spazio = criterio.indexOf(" ");
			if(spazio != -1) {
				StringTokenizer st = new StringTokenizer(criterio, " ");
				String nome ="";
				String cognome="";
				while(st.hasMoreElements()) {
					nome = st.nextToken();
					cognome = st.nextToken();
				}
				
				s = con.prepareStatement("select * from ordini where nome = ? and cognome = ?");
				s.setString(1, nome);
				s.setString(2, cognome);
				
				rs = s.executeQuery();
				
				while (rs.next()) {
					Ordine ordineTmp = convertiRigaOrdine(rs);
					lista.add(ordineTmp);
				}
				
				return lista;

				
			}else {
				String codice2 = codice + "%";
				s = con.prepareStatement("select * from ordini where codice like ? or nome like ? or cognome like ? or stato like ? ");
				
				s.setString(1, codice2);
				s.setString(2, codice2);
				s.setString(3, codice2);
				s.setString(4, codice2);
	
				rs = s.executeQuery();
				
				while (rs.next()) {
					Ordine ordineTmp = convertiRigaOrdine(rs);
					lista.add(ordineTmp);
				}
				
				return lista;
			}
		}
		finally {
			chiudi(s, rs);

		}
	}
	
	
	/**
	 * Accede alla base di dati ed effettua una ricerca tra gli ordini secondo un determinato criterio e un intervallo di date specificato
	 * @param String criterio
	 * @param String data inizio intervallo
	 * @param String data fine intervallo
	 * @return Lista di Ordini
	 * @throws Exception
	 */
	public List<Ordine> ricercaPerCriterio(String codice, String dataDa, String dataA) throws Exception {
		List<Ordine> lista = new ArrayList<>();

		PreparedStatement s = null;
		ResultSet rs = null;

		try {
			if(codice.equals("")) {
				java.util.Date dDa = new SimpleDateFormat("yyyy/MM/dd").parse(dataDa);
				java.sql.Date dDaSql = new java.sql.Date(dDa.getTime());
				
				java.util.Date dA = new SimpleDateFormat("yyyy/MM/dd").parse(dataA);
				java.sql.Date dASql = new java.sql.Date(dA.getTime());

				s = con.prepareStatement("select * from ordini where dataAcquisto >= ? and dataAcquisto <= ?");

				s.setDate(1, dDaSql);
				s.setDate(2, dASql);

				rs = s.executeQuery();
				
				while (rs.next()) {
					Ordine ordineTmp = convertiRigaOrdine(rs);
					lista.add(ordineTmp);
				}
				
				return lista;
			}else {
				String criterio = codice;
				int spazio = criterio.indexOf(" ");
				java.util.Date dDa = new SimpleDateFormat("yyyy/MM/dd").parse(dataDa);
				java.sql.Date dDaSql = new java.sql.Date(dDa.getTime());
				
				java.util.Date dA = new SimpleDateFormat("yyyy/MM/dd").parse(dataA);
				java.sql.Date dASql = new java.sql.Date(dA.getTime());
				
				if(spazio != -1) {
					StringTokenizer st = new StringTokenizer(criterio, " ");
					String nome ="";
					String cognome="";
					while(st.hasMoreElements()) {
						nome = st.nextToken();
						cognome = st.nextToken();
					}
					
					s = con.prepareStatement("select * from ordini where dataAcquisto >= ? and dataAcquisto <= ? and (nome = ? and cognome = ?)");
					s.setDate(1, dDaSql);
					s.setDate(2, dASql);
					
					s.setString(3, nome);
					s.setString(4, cognome);
					
					rs = s.executeQuery();
					
					while (rs.next()) {
						Ordine ordineTmp = convertiRigaOrdine(rs);
						lista.add(ordineTmp);
					}
					
					return lista;
				}else {
						codice += "%";
					
						s = con.prepareStatement("select * from ordini where dataAcquisto >= ? and dataAcquisto <= ? and (codice like ? or nome like ? or cognome like ? or stato like ?)");
									
						s.setString(3, codice);
						s.setString(4, codice);
						s.setString(5, codice);
						s.setString(6, codice);
						s.setDate(1, dDaSql);
						s.setDate(2, dASql);
			
						rs = s.executeQuery();
						
						while (rs.next()) {
							Ordine ordineTmp = convertiRigaOrdine(rs);
							lista.add(ordineTmp);
						}
						
						return lista;
				}
			}
			
		}
		finally {
			chiudi(s, rs);

		}
	}
	
	/**
	 * Accede alla base di dati e seleziona l'Ordine il cui codice è quello specificato
	 * @param String codice ordine
	 * @return Ordine
	 * @throws Exception
	 */
	public Ordine trovaOrdine(String codice) throws Exception{
		PreparedStatement s = null;
		ResultSet rs = null;
		try{
			
			s = con.prepareStatement("select * from ordini where codice = ?");
			
			s.setString(1, codice);
			
			rs = s.executeQuery();
			
			rs.next();
			Ordine ordineTmp = convertiRigaOrdine(rs);
			return ordineTmp;	
		}finally {
			chiudi(s, rs);

		}
		
		
		
	}
	
	/**
	 * Accede alla base di dati e seleziona l'Ordine associato al numero di FatturaVendite specificato
	 * @param int numero Fattura Vendite
	 * @return Ordine
	 * @throws Exception
	 */
	public Ordine trovaOrdine(int numero) throws Exception{
		PreparedStatement s = null;
		ResultSet rs = null;
		try{

			s = con.prepareStatement("select * from ordini where numFatturaVend = ?");
			
			String num = Integer.toString(numero);
			
			s.setString(1, num);
			
			rs = s.executeQuery();
			
			rs.next();
			Ordine ordineTmp = convertiRigaOrdine(rs);
			return ordineTmp;
		}finally {
			chiudi(s,rs);

		}
		
	}
	
	
	/**
	 * Converte una lista di codici relativi a ordini in una lista di Ordini
	 * @param List<String> Codici
	 * @return Lista Ordini
	 * @throws Exception
	 */
	public List<Ordine> convertiCodiciInOrdini(List<String> lista) throws Exception{
		List<Ordine> listaOrdini = new ArrayList<>();
		for(String s : lista) {
			Ordine tmp = trovaOrdine(s);
			listaOrdini.add(tmp);
		}
		return listaOrdini;
	}
	
	/**
	 * Converte una lista di codici relativi a ordini in una lista di Fatture Vendite 
	 * @param Lista Codici (Stringhe)
	 * @return Lista Fatture Vendite
	 * @throws Exception
	 */
	public List<FatturaVendite> convertiCodiciInFatture(List<String> lista) throws Exception{
		List<FatturaVendite> listaFatture = new ArrayList<>();

		for(String s : lista) {
			Ordine tmp = trovaOrdine(s);
			int numero = tmp.getNumFatturaVend();
			FatturaVendite fatturaTmp = HomeAreaAmministrativaGUI.fatturaVenditeDAO.trovaFatturaVendite(Integer.toString(numero));
			listaFatture.add(fatturaTmp);
		}
		return listaFatture;
	}
	
	/**
	 * Converte una entry prelevata dalla base di dati in un oggetto Ordine
	 * @param ResultSet rs
	 * @return Ordine
	 * @throws SQLException
	 */
	private Ordine convertiRigaOrdine(ResultSet rs) throws SQLException {
		
		String codice = rs.getString("codice");
		String nome = rs.getString("nome");
		String cognome = rs.getString("cognome");
		String cf = rs.getString("cf");
		java.sql.Date dataAcquisto = rs.getDate("dataAcquisto");
		String indirizzo = rs.getString("indirizzo");
		String telefono = rs.getString("telefono");
		String email = rs.getString("email");
		float importoTotale = rs.getFloat("importoTotale");
		int sconto = rs.getInt("sconto");
		String modalitaConsegna = rs.getString("modalitaConsegna");
		int numFatturaVend = rs.getInt("numFatturaVend");
		String stato = rs.getString("stato");
		
		Ordine ordineTmp = new Ordine(codice, nome, cognome, cf, indirizzo, telefono, email, modalitaConsegna, stato,
				importoTotale, sconto, numFatturaVend, dataAcquisto);
		
		return ordineTmp;
	}
	
	/**
	 * Aggiunge un Ordine alla base di dati
	 * @param Ordine o
	 * @throws Exception
	 */
	public void aggiungiOrdine(Ordine o) throws Exception{
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("insert into ordini (codice, nome, cognome, cf, indirizzo, telefono, email, modalitaConsegna, stato," + 
					"importoTotale, sconto, numFatturaVend, dataAcquisto) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, o.getCodice());
			ps.setString(2, o.getNome());
			ps.setString(3, o.getCognome());
			ps.setString(4, o.getCf());
			ps.setString(5, o.getIndirizzo());
			ps.setString(6, o.getTelefono());
			ps.setString(7, o.getEmail());
			ps.setString(8, o.getModalitaConsegna());
			ps.setString(9, o.getStato());
			ps.setFloat(10, o.getImportoTotale());
			ps.setInt(11, o.getSconto());
			ps.setInt(12, o.getNumFatturaVend());
			ps.setDate(13, o.getDataAcquisto());
			
			ps.executeUpdate();

		}finally {
			chiudi(ps);

		}
	}
	
	/**
	 * Rimuove un Ordine dalla base di dati
	 * @param String codice ordine
	 * @throws Exception
	 */
	public void eliminaOrdine(String codice) throws Exception{
		Statement s = null;
		try {
			s = con.createStatement();
			s.executeUpdate("delete from ordini where codice='"+codice+"'");
			
		}finally {
			chiudi(s);

		}
	}
	
	private static void chiudi(Connection con, Statement s, ResultSet rs)
			throws SQLException {

		if (rs != null) {
			rs.close();
		}

		if (s != null) {
			s.close();
		}
		if (con != null) {
			con.close();
		}
	}

	private void chiudi(Statement s, ResultSet rs) throws SQLException {
		chiudi(null, s, rs);		
	}
	
	private void chiudi(Statement ps) throws SQLException {
		chiudi(null, ps, null);		
	}
	
	private void chiudi(Connection con, Statement ps) throws SQLException {
		chiudi(con, ps, null);		
	}
	
	public static void main(String[] args) throws Exception {
	}

}
