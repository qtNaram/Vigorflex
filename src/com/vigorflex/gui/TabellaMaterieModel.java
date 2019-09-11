package com.vigorflex.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import com.vigorflex.codice.Prodotto;
import com.vigorflex.codice.R2;
import com.vigorflex.codice.R8;
import com.vigorflex.dati.ProdottoDAO;

public class TabellaMaterieModel extends AbstractTableModel{

	private static final int MATERIA = 0;
	private static final int QUANTITA = 1;

	private String[] colonne = {"Materia Prima o Semilavorato", "Quantità"};
	private List<R8> listaR8;

	public TabellaMaterieModel(List<R8> listaMaterie) {
		listaR8 = listaMaterie;
	}

	@Override
	public int getColumnCount() {
		return colonne.length;
	}

	@Override
	public int getRowCount() {
		return listaR8.size();
	}

	@Override
	public String getColumnName(int col) {
		return colonne[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		R8 r8Tmp = listaR8.get(row);

		switch (col) {
		case MATERIA:
			return r8Tmp.getMateriaprima();
		case QUANTITA:
			return r8Tmp.getQuantita();
				}
		return r8Tmp;
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	
}
