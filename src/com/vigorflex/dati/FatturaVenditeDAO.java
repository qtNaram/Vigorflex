package com.vigorflex.dati;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

import com.vigorflex.codice.FatturaVendite;
import com.vigorflex.codice.Ordine;

/**
 * @author Pietro Marino
 *
 */
public class FatturaVenditeDAO {
	
	private Connection con;
	
	public FatturaVenditeDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("vigorflex.properties"));
		con = DriverManager.getConnection(prop.getProperty("dburl") + TimeZone.getDefault().getID(), prop.getProperty("user"), prop.getProperty("password"));

	}
	
	
	/**
	 * Accede alla base di dati e restituisce l'elenco delle fatture vendite salvate
	 * 
	 * @return Lista di Fatture Vendite
	 * @throws Exception
	 */
	public List<FatturaVendite> getListaFattureVendite() throws Exception{
		List<FatturaVendite> lista = new ArrayList<>();
		
		Statement s = null;
		ResultSet rs = null;
		
		try {
			s = con.createStatement();
			rs = s.executeQuery("select * from fatturevendite");
			
			while(rs.next()) {
				FatturaVendite fatturaTmp = convertiRigaFatturaVendite(rs);
				lista.add(fatturaTmp);
			}
			
			return lista;
		}finally {
			chiudi(s, rs);

		}
	}
	
	
	
	/**
	 * Accede alla base di dati ed effettua una ricerca tra le fatture vendite secondo un determinato criterio, in assenza di un intervallo di date specificato
	 * 
	 * @param String criterio 
	 * @return Lista di Fatture Vendite
	 * @throws Exception
	 */
	public List<FatturaVendite> ricercaPerCriterio(String criterio) throws Exception {
		List<FatturaVendite> lista = new ArrayList<>();

		PreparedStatement s = null;
		ResultSet rs = null;

		try {
			String crit = criterio;
			int spazio = crit.indexOf(" ");
			if(spazio != -1) {
				StringTokenizer st = new StringTokenizer(crit, " ");
				String nome ="";
				String cognome="";
				while(st.hasMoreElements()) {
					nome = st.nextToken();
					cognome = st.nextToken();
				}
				
				s = con.prepareStatement("select * from fatturevendite where nome = ? and cognome = ?");
				s.setString(1, nome);
				s.setString(2, cognome);
				
				rs = s.executeQuery();
				
				while (rs.next()) {
					FatturaVendite fatturaTmp = convertiRigaFatturaVendite(rs);
					lista.add(fatturaTmp);
				}
				
				return lista;

				
			}else {
				String codice2 = criterio + "%";
				s = con.prepareStatement("select * from fatturevendite where numero like ? or nome like ? or cognome like ?");
				
				s.setString(1, codice2);
				s.setString(2, codice2);
				s.setString(3, codice2);
	
				rs = s.executeQuery();
				
				while (rs.next()) {
					FatturaVendite fatturaTmp = convertiRigaFatturaVendite(rs);
					lista.add(fatturaTmp);
				}
				
				return lista;
			}
		}
		finally {
			chiudi(s, rs);

		}
	}
	
	/**
	 * Accede alla base di dati ed effettua una ricerca tra le fatture vendite secondo un determinato criterio e un intervallo di date specificato
	 * @param String criterio
	 * @param String data inizio intervallo
	 * @param String data fine intervallo
	 * @return Lista di Fatture Vendite
	 * @throws Exception
	 */
	public List<FatturaVendite> ricercaPerCriterio(String criterio, String dataDa, String dataA) throws Exception {
		List<FatturaVendite> lista = new ArrayList<>();

		PreparedStatement s = null;
		ResultSet rs = null;

		try {
			if(criterio.equals("")) {
				java.util.Date dDa = new SimpleDateFormat("yyyy/MM/dd").parse(dataDa);
				java.sql.Date dDaSql = new java.sql.Date(dDa.getTime());
				
				java.util.Date dA = new SimpleDateFormat("yyyy/MM/dd").parse(dataA);
				java.sql.Date dASql = new java.sql.Date(dA.getTime());

				s = con.prepareStatement("select * from fatturevendite where dataEmissione >= ? and dataEmissione <= ?");

				s.setDate(1, dDaSql);
				s.setDate(2, dASql);

				rs = s.executeQuery();
				
				while (rs.next()) {
					FatturaVendite fatturaTmp = convertiRigaFatturaVendite(rs);
					lista.add(fatturaTmp);
				}
				
				return lista;
			}else {
				String crit = criterio;
				int spazio = crit.indexOf(" ");
				java.util.Date dDa = new SimpleDateFormat("yyyy/MM/dd").parse(dataDa);
				java.sql.Date dDaSql = new java.sql.Date(dDa.getTime());
				
				java.util.Date dA = new SimpleDateFormat("yyyy/MM/dd").parse(dataA);
				java.sql.Date dASql = new java.sql.Date(dA.getTime());
				
				if(spazio != -1) {
					StringTokenizer st = new StringTokenizer(crit, " ");
					String nome ="";
					String cognome="";
					while(st.hasMoreElements()) {
						nome = st.nextToken();
						cognome = st.nextToken();
					}
					
					s = con.prepareStatement("select * from fatturevendite where dataEmissione >= ? and dataEmissione <= ? and (nome = ? and cognome = ?)");
					s.setDate(1, dDaSql);
					s.setDate(2, dASql);
					
					s.setString(3, nome);
					s.setString(4, cognome);
					
					rs = s.executeQuery();
					
					while (rs.next()) {
						FatturaVendite fatturaTmp = convertiRigaFatturaVendite(rs);
						lista.add(fatturaTmp);
					}
					
					return lista;
				}else {
						criterio += "%";
					
						s = con.prepareStatement("select * from fatturevendite where dataEmissione >= ? and dataEmissione <= ? and (numero like ? or nome like ? or cognome like ?)");
									
						s.setString(3, criterio);
						s.setString(4, criterio);
						s.setString(5, criterio);
						s.setDate(1, dDaSql);
						s.setDate(2, dASql);
			
						rs = s.executeQuery();
						
						while (rs.next()) {
							FatturaVendite fatturaTmp = convertiRigaFatturaVendite(rs);
							lista.add(fatturaTmp);
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
	 * Accede alla base di dati e seleziona la Fattura Vendita il cui numero è quello specificato
	 * @param String numero Fattura Vendita
	 * @return Fattura Vendita
	 * @throws Exception
	 */
	public FatturaVendite trovaFatturaVendite(String numero) throws Exception{
		PreparedStatement s = null;
		ResultSet rs = null;

		try{
			s = con.prepareStatement("select * from fatturevendite where numero = ?");
			
			s.setString(1, numero);
			
			rs = s.executeQuery();
			
			rs.next();
			FatturaVendite fatturaTmp = convertiRigaFatturaVendite(rs);
			return fatturaTmp;	
		}finally {
			chiudi(s,rs);

		}
		
		
		
	}
	
	/**
	 * Converte una entry prelevata dalla base di dati in un oggetto FatturaVendite
	 * @param ResultSet rs
	 * @return Fattura Vendita
	 * @throws SQLException
	 */
	private FatturaVendite convertiRigaFatturaVendite(ResultSet rs) throws SQLException {
		
		int numero = rs.getInt("numero");
		String nome = rs.getString("nome");
		String cognome = rs.getString("cognome");
		String cf = rs.getString("codiceFiscale");
		Date dataEmissione = rs.getDate("dataEmissione");
		String indirizzo = rs.getString("indirizzo");
		float importo = rs.getFloat("importo");
		int sconto = rs.getInt("sconto");
		
		
		FatturaVendite fatturaTmp = new FatturaVendite( numero, nome, cognome, cf, indirizzo, dataEmissione, importo, sconto);
		
		return fatturaTmp;
	}
	
	/**
	 * Aggiunge una Fattura Vendita alla base di dati
	 * @param FatturaVendita fattura
	 * @throws Exception
	 */
	public void aggiungiFatturaVendite(FatturaVendite f) throws Exception{
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("insert into fatturevendite (numero, nome, cognome, codiceFiscale, indirizzo, dataEmissione, importo, sconto) values (?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setInt(1, f.getNumero());
			ps.setString(2, f.getNome());
			ps.setString(3, f.getCognome());
			ps.setString(4, f.getCf());
			ps.setString(5, f.getIndirizzo());
			ps.setDate(6, f.getDataEmissione());
			ps.setFloat(7, f.getImporto());
			ps.setInt(8, f.getSconto());
			ps.executeUpdate();

		}finally {
			chiudi(ps);
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
