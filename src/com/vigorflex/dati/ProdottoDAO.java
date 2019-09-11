package com.vigorflex.dati;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import com.vigorflex.codice.Ordine;
import com.vigorflex.codice.Prodotto;

public class ProdottoDAO {
	
	private Connection con;
	
	public ProdottoDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("vigorflex.properties"));
		con = DriverManager.getConnection(prop.getProperty("dburl") + TimeZone.getDefault().getID(), prop.getProperty("user"), prop.getProperty("password"));
	}

	
	/**
	 * Accede alla base di dati e restituisce il prodotto il cui nome Ë quello specificato
	 * @param String nome Prodotto
	 * @return Prodotto
	 * @throws Exception
	 */
	public Prodotto getProdotto(String nome) throws Exception{
	
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = con.prepareStatement("select * from prodotti where nome='"+nome+"'");

			rs = s.executeQuery();
			
			rs.next();
			Prodotto p = convertiRigaProdotto(rs);

			return p;
		}finally {
			chiudi(s, rs);
		}
	}
	
	
	/**
	 * Converte una entry prelevata dalla base di dati in un oggetto Prodotto
	 * @param ResultSet rs
	 * @return Prodotto
	 * @throws SQLException
	 */
	private Prodotto convertiRigaProdotto(ResultSet rs) throws SQLException {

		String nome = rs.getString("nome");
		String settoreMagazzinoCommesse = rs.getString("settoreMagazzinoCommesse");
		String settoreMagazzinoDettaglio = rs.getString("settoreMagazzinoDettaglio");
		String settoreNegozio = rs.getString("settoreNegozio");
		float prezzo = rs.getFloat("prezzo");
		int qtaMagazzinoCommesse = rs.getInt("qt‡MagazzinoCommesse");
		int qtaMagazzinoDettaglio = rs.getInt("qt‡MagazzinoDettaglio");
		int qtaNegozio = rs.getInt("qt‡Negozio");
		

		Prodotto prodottoTmp = new Prodotto(nome, settoreMagazzinoCommesse, settoreMagazzinoDettaglio, settoreNegozio, qtaMagazzinoCommesse, 
				qtaMagazzinoDettaglio, qtaNegozio, prezzo);
		
		return prodottoTmp;
	}
	
	
	
	/**
	 * Accede alla base di dati e aggiorna la quantit‡ disponibile di un Prodotto sottraendone quella specificata
	 * @param String nome Prodotto
	 * @param int quantit‡ Prodotto
	 * @throws SQLException
	 */
	public void modificaQta(String nome, int qta) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update prodotti set qt‡MagazzinoCommesse=qt‡MagazzinoCommesse-"+qta+" where nome='"+nome+"'");

			ps.executeUpdate();
		
		}finally {
			chiudi(ps);
		}
	}
	
	
	/**
	 * Accede alla base di dati e verifica se la quantit‡ del Prodotto specificato Ë al di sopra della soglia minima
	 * @param String nome Prodotto
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean controllaQta(String nome) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select qt‡MagazzinoCommesse from prodotti where nome='"+nome+"'");

			rs = ps.executeQuery();
			
			rs.next();
			
			int n = rs.getInt(1);
			if(n < 50) return true;
			
			return false;
		
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
