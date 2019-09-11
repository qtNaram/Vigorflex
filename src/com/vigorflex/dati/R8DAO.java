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
import com.vigorflex.codice.R8;

public class R8DAO {
	
	private Connection con;
	
	public R8DAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("vigorflex.properties"));
		con = DriverManager.getConnection(prop.getProperty("dburl") + TimeZone.getDefault().getID(), prop.getProperty("user"), prop.getProperty("password"));
	}
	
	public void aggiungiR8(R8 r) throws Exception{
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("insert into r8 (prodottofinito, materiaprima, quantità) values (?, ?, ?)");
			
			ps.setString(1, r.getProdottofinito());
			ps.setString(2, r.getMateriaprima());
			ps.setFloat(3, r.getQuantita());
			
			
			ps.executeUpdate();

		}finally {
			chiudi(ps);

		}
	}
	
	
	public List<R8> getListaR8(String nome) throws Exception{
		List<R8> lista = new ArrayList<>();
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			
			s = con.prepareStatement("select * from r8 where prodottofinito = ?");
			
			s.setString(1, nome);
			
			rs = s.executeQuery();
			
			while (rs.next()) {
				R8 r8 = convertiRigaR8(rs);
				lista.add(r8);
			}
			
			return lista;
		}
		finally {
			chiudi(s, rs);

		}
		
		
	}
	
	private R8 convertiRigaR8(ResultSet rs) throws SQLException {
		
		String prodottofinito = rs.getString("prodottofinito");
		String materiaprima = rs.getString("materiaprima");
		float quantità = rs.getFloat("quantità");
		
		
		R8 r8Tmp = new R8(prodottofinito, materiaprima, quantità);
		
		return r8Tmp;
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
