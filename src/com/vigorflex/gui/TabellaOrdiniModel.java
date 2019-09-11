package com.vigorflex.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.vigorflex.codice.Ordine;


public class TabellaOrdiniModel extends AbstractTableModel {
	private static final int CODICE = 0;
	private static final int NOMINATIVO = 1;
	private static final int DATA_ACQUISTO = 2;
	private static final int STATO = 3;

	private String[] colonne = { "Codice", "Nominativo", "Data", "Stato" };
	private List<Ordine> ordini;

	public TabellaOrdiniModel(List<Ordine> listaOrdini) {
		ordini = listaOrdini;
	}

	@Override
	public int getColumnCount() {
		return colonne.length;
	}

	@Override
	public int getRowCount() {
		return ordini.size();
	}

	@Override
	public String getColumnName(int col) {
		return colonne[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Ordine ordineTmp = ordini.get(row);

		switch (col) {
		case CODICE:
			return ordineTmp.getCodice();
		case NOMINATIVO:
			return ordineTmp.getNome() +" "+ ordineTmp.getCognome();
		case DATA_ACQUISTO:
			return ordineTmp.getDataAcquisto();
		case STATO:
			return ordineTmp.getStato();
		default:
			return ordineTmp.getCodice();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
