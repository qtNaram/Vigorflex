package com.vigorflex.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.vigorflex.codice.FatturaVendite;
import com.vigorflex.codice.Ordine;


public class TabellaFattureVenditeModel extends AbstractTableModel {
	private static final int NUMERO = 0;
	private static final int NOMINATIVO = 1;
	private static final int DATA_EMISSIONE = 2;

	private String[] colonne = { "Numero", "Nominativo", "Data Emissione"};
	private List<FatturaVendite> fatture;

	public TabellaFattureVenditeModel(List<FatturaVendite> listaFatture) {
		fatture = listaFatture;
	}

	@Override
	public int getColumnCount() {
		return colonne.length;
	}

	@Override
	public int getRowCount() {
		return fatture.size();
	}

	@Override
	public String getColumnName(int col) {
		return colonne[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		FatturaVendite fatturaTmp = fatture.get(row);

		switch (col) {
		case NUMERO:
			return fatturaTmp.getNumero();
		case NOMINATIVO:
			return fatturaTmp.getNome() +" "+ fatturaTmp.getCognome();
		case DATA_EMISSIONE:
			return fatturaTmp.getDataEmissione();
		default:
			return fatturaTmp.getNumero();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
