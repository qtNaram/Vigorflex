package com.vigorflex.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.vigorflex.dati.FatturaVenditeDAO;
import com.vigorflex.dati.OrdineDAO;
import com.vigorflex.dati.ProdottoDAO;
import com.vigorflex.dati.R2DAO;
import com.vigorflex.codice.FatturaVendite;
import com.vigorflex.codice.Ordine;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;

public class RisultatiFattureVenditeGUI extends JFrame {

	private JPanel contentPane;


	static  String criterio;
	static  String prodotto;

	private static  String dataDa;
	private static  String dataA;

	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RisultatiFattureVenditeGUI frame = new RisultatiFattureVenditeGUI(criterio, dataDa, dataA, prodotto);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public RisultatiFattureVenditeGUI() throws Exception {
		this(criterio, dataDa, dataA, prodotto);
	}
	
	public RisultatiFattureVenditeGUI(String criterio, String dataDa, String dataA, String prodotto) throws Exception {
		RisultatiFattureVenditeGUI.criterio=criterio;
		RisultatiFattureVenditeGUI.dataA = dataA;
		RisultatiFattureVenditeGUI.dataDa = dataDa;
		RisultatiFattureVenditeGUI.prodotto = prodotto;


		setResizable(false);

		
		setTitle("Risultati Ricerca Fatture Vendite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 91, 436, 288);
		contentPane.add(scrollPane);
		
		List<FatturaVendite> fatture = null;
		
		if(criterio != null && criterio.trim().length() > 0 && dataDa.length() < 3 && dataA.length() < 3) {
			fatture = HomeAreaAmministrativaGUI.fatturaVenditeDAO.ricercaPerCriterio(criterio);
		}else if(dataDa.length() > 3 && dataA.length() > 3) {
			fatture = HomeAreaAmministrativaGUI.fatturaVenditeDAO.ricercaPerCriterio(criterio, dataDa, dataA);
		}else {
			if(prodotto.length() > 3) {
				fatture = HomeAreaAmministrativaGUI.ordineDAO.convertiCodiciInFatture(HomeAreaAmministrativaGUI.r2DAO.getListaOrdiniR2(prodotto));
			}else {	
				fatture = HomeAreaAmministrativaGUI.fatturaVenditeDAO.getListaFattureVendite();
			}
		}
		
		TabellaFattureVenditeModel tabFatture = new TabellaFattureVenditeModel(fatture);
		table = new JTable();
		
		scrollPane.setViewportView(table);
		table.setModel(tabFatture);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		table.setForeground(Color.BLUE);
		Map attributes = table.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		table.setFont(table.getFont().deriveFont(attributes));
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		
		  table.addMouseListener(new java.awt.event.MouseAdapter(){
			  public void mouseClicked(java.awt.event.MouseEvent e){
				  int row=table.rowAtPoint(e.getPoint());
				  int col= table.columnAtPoint(e.getPoint());
	              String codiceString = table.getValueAt(row, col).toString();


					try {
						switch(col) {
						case 0: 
							int codice = Integer.parseInt(codiceString);
							DettagliFatturaVenditeGUI dettagli;
							dettagli = new DettagliFatturaVenditeGUI(codice, 2);
							dispose();
							dettagli.setVisible(true); break;
						case 1:
							RisultatiFattureVenditeGUI risNominativo;
							risNominativo = new RisultatiFattureVenditeGUI(codiceString, "", "", "");
							dispose();
							risNominativo.setVisible(true); break;
						case 2:
							RisultatiFattureVenditeGUI risData;
							String dataFormattata = ""+codiceString.charAt(0)+codiceString.charAt(1)+codiceString.charAt(2)+codiceString.charAt(3)+"/"+codiceString.charAt(5)+codiceString.charAt(6)+"/"+codiceString.charAt(8)+codiceString.charAt(9);
							risData = new RisultatiFattureVenditeGUI("", dataFormattata, dataFormattata, "");
							dispose();
							risData.setVisible(true); break;
						}
						
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
			  }
		  });
		
		JLabel lblListaOrdini = new JLabel("Lista Fatture Vendite");
		lblListaOrdini.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListaOrdini.setBounds(127, 40, 193, 14);
		contentPane.add(lblListaOrdini);
		
		JLabel lblRisultatiDiRicerca = new JLabel("Risultati di ricerca: "+ criterio + " "+dataDa+ " "+dataA);
		lblRisultatiDiRicerca.setBounds(127, 65, 436, 14);
		contentPane.add(lblRisultatiDiRicerca);
		
		JButton button = new JButton("< Indietro");
		button.setForeground(Color.BLUE);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBounds(117, 11, 115, 23);
		button.setBorderPainted(false); 
		button.setContentAreaFilled(false); 
		button.setFocusPainted(false); 
		button.setOpaque(false);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RicercaFattureGUI ricFatture;
				try {
					ricFatture = new RicercaFattureGUI();
					ricFatture.setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(107, 0, 125, 399);
		contentPane.add(separator_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 107, 399);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Home");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 54, 46, 14);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					HomeAreaAmministrativaGUI home = new HomeAreaAmministrativaGUI();
					home.setVisible(true);
					dispose();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		
		Font font5 = lblNewLabel_1.getFont();
		Map attributes5 = font5.getAttributes();
		attributes5.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblNewLabel_1.setFont(font5.deriveFont(attributes5));
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblOrdini = new JLabel("Ordini");
		lblOrdini.setForeground(Color.BLUE);
		lblOrdini.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrdini.setBounds(10, 79, 72, 14);
		panel.add(lblOrdini);
		
		Font font3 = lblOrdini.getFont();
		Map attributes3 = font3.getAttributes();
		attributes3.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblOrdini.setFont(font3.deriveFont(attributes3));
		lblOrdini.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblOrdini.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					RicercaOrdiniGUI ricOrdini = new RicercaOrdiniGUI();
					ricOrdini.setVisible(true);
					dispose();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		
		
		JLabel lblFattureVendite = new JLabel("Fatture Vendite");
		lblFattureVendite.setForeground(Color.BLUE);
		lblFattureVendite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFattureVendite.setBounds(10, 104, 87, 14);
		panel.add(lblFattureVendite);
		
		Font font4 = lblFattureVendite.getFont();
		Map attributes4 = font4.getAttributes();
		attributes4.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblFattureVendite.setFont(font4.deriveFont(attributes4));
		lblFattureVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblFattureVendite.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					RicercaFattureGUI ricFattura = new RicercaFattureGUI();
					ricFattura.setVisible(true);
					dispose();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		
		JLabel lblPuntoVendita = new JLabel("Punto Vendita");
		lblPuntoVendita.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPuntoVendita.setBounds(10, 129, 87, 14);
		panel.add(lblPuntoVendita);
		
		JLabel lblDipendenti_1 = new JLabel("Dipendenti");
		lblDipendenti_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDipendenti_1.setBounds(10, 179, 87, 14);
		panel.add(lblDipendenti_1);
		
		JLabel lblStatistiche = new JLabel("Statistiche");
		lblStatistiche.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatistiche.setBounds(10, 204, 87, 14);
		panel.add(lblStatistiche);
		
		JLabel lblVetrina = new JLabel("Vetrina");
		lblVetrina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVetrina.setBounds(10, 154, 87, 14);
		panel.add(lblVetrina);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMenu.setBounds(10, 23, 67, 20);
		panel.add(lblMenu);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		separator.setBounds(107, 751, 496, 34);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(0, 399, 583, 182);
		contentPane.add(separator_1);
		
	}
	
	public void aggiornaLista() {
		try {
			List<FatturaVendite> fatture = HomeAreaAmministrativaGUI.fatturaVenditeDAO.getListaFattureVendite();

			TabellaFattureVenditeModel tabFatture = new TabellaFattureVenditeModel(fatture);

			table.setModel(tabFatture);

		}catch(Exception e) {
			JOptionPane.showMessageDialog(this,  "Errore: "+e, "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
}
