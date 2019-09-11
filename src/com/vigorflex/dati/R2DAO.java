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
import com.vigorflex.codice.R2;

public class R2DAO {
	
	private Connection con;
	
	public R2DAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("vigorflex.properties"));
		con = DriverManager.getConnection(prop.getProperty("dburl") + TimeZone.getDefault().getID(), prop.getProperty("user"), prop.getProperty("password"));

	}
	
	public void aggiungiR2(R2 r) throws Exception{
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("insert into r2 (ordine, prodotto, quantità) values (?, ?, ?)");
			
			ps.setString(1, r.getOrdine());
			ps.setString(2, r.getProdotto());
			ps.setInt(3, r.getQta());
			
			
			ps.executeUpdate();

		}finally {
			chiudi(ps);

		}
	}
	
	
	public List<R2> getListaR2(String codice) throws Exception{
		List<R2> lista = new ArrayList<>();
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			
			s = con.prepareStatement("select * from r2 where ordine = ?");
			
			s.setString(1, codice);
			
			rs = s.executeQuery();
			
			while (rs.next()) {
				R2 r2 = convertiRigaR2(rs);
				lista.add(r2);
			}
			
			return lista;
		}
		finally {
			chiudi(s, rs);

		}
		
		
	}
	
	public List<String> getListaOrdiniR2(String prodotto) throws Exception{
		List<String> lista = new ArrayList<>();
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			
			s = con.prepareStatement("select ordine from r2 where prodotto = ?");
			
			s.setString(1, prodotto);
			
			rs = s.executeQuery();
			
			while (rs.next()) {
				String codice = rs.getString("ordine");
				lista.add(codice);
			}
			
			return lista;
		}
		finally {
			chiudi(s, rs);

		}
		
		
	}
	
	private R2 convertiRigaR2(ResultSet rs) throws SQLException {
		
		String ordine = rs.getString("ordine");
		String prodotto = rs.getString("prodotto");
		int qta = rs.getInt("quantità");
		
		
		R2 r2Tmp = new R2(ordine, prodotto, qta);
		
		return r2Tmp;
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
