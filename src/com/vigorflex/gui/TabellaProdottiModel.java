package com.vigorflex.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import com.vigorflex.codice.Prodotto;
import com.vigorflex.codice.R2;
import com.vigorflex.dati.ProdottoDAO;

public class TabellaProdottiModel extends AbstractTableModel{

	private static final int PRODOTTO = 0;
	private static final int QUANTITA = 1;
	private static final int IMPORTO = 2;
	

	private String[] colonne = {"Prodotto", "Quantità", "Importo"};
	private List<R2> listaR2;

	public TabellaProdottiModel(List<R2> listaProdotti) {
		listaR2 = listaProdotti;
	}

	@Override
	public int getColumnCount() {
		return colonne.length;
	}

	@Override
	public int getRowCount() {
		return listaR2.size();
	}

	@Override
	public String getColumnName(int col) {
		return colonne[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		R2 r2Tmp = listaR2.get(row);


		switch (col) {
		case PRODOTTO:
			return r2Tmp.getProdotto();
		case QUANTITA:
			return r2Tmp.getQta();
		case IMPORTO:
			Prodotto p;
			try {
				p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(r2Tmp.getProdotto());
				return r2Tmp.getQta()*p.getPrezzo();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return r2Tmp;
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	
}
