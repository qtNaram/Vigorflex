package com.vigorflex.gui;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.vigorflex.codice.Prodotto;

import com.vigorflex.codice.R8;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

public class DettagliProdottoGUI extends JFrame{
	private JPanel contentPane;
	private static String nome;
	private static int n;

	private static String codice;
	JTable table;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DettagliProdottoGUI frame = new DettagliProdottoGUI(nome, n, codice);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public DettagliProdottoGUI(String nome, int n, String codice) throws Exception {
		setTitle("Dettagli Prodotto");
		DettagliProdottoGUI.nome=nome;
		DettagliProdottoGUI.n = n;
		DettagliProdottoGUI.codice=codice;
		
		Prodotto prodotto = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nome);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblSchedaOrdine = new JLabel("Scheda " + prodotto.getNome());
		lblSchedaOrdine.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSchedaOrdine.setBounds(127, 39, 476, 14);
		getContentPane().add(lblSchedaOrdine);
		
		JLabel lblqtaMC = new JLabel("Q.t\u00E0 Magazz. (Commesse)");
		lblqtaMC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblqtaMC.setBounds(127, 64, 186, 14);
		getContentPane().add(lblqtaMC);
		
		JLabel lblsettoreMC = new JLabel("Settore Magazz. (Commesse)");
		lblsettoreMC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblsettoreMC.setBounds(127, 89, 186, 14);
		getContentPane().add(lblsettoreMC);
		
		JLabel lblqtaMD = new JLabel("Q.t\u00E0 Magazz. (Dettaglio)");
		lblqtaMD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblqtaMD.setBounds(127, 114, 186, 14);
		getContentPane().add(lblqtaMD);
		
		JLabel lblsettoreMD = new JLabel("Settore Magazz. (Dettaglio)");
		lblsettoreMD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblsettoreMD.setBounds(127, 139, 186, 14);
		getContentPane().add(lblsettoreMD);
		
		JLabel lblqtaNegozio = new JLabel("Q.t\u00E0 Negozio");
		lblqtaNegozio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblqtaNegozio.setBounds(127, 163, 186, 14);
		getContentPane().add(lblqtaNegozio);
		
		JLabel lblsettoreNegozio = new JLabel("Settore negozio");
		lblsettoreNegozio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblsettoreNegozio.setBounds(127, 189, 186, 14);
		getContentPane().add(lblsettoreNegozio);
		
		JLabel lblprezzo = new JLabel("Prezzo unitario");
		lblprezzo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblprezzo.setBounds(127, 216, 186, 14);
		getContentPane().add(lblprezzo);
		
		JLabel label_1 = new JLabel("-");
		label_1.setBounds(323, 64, 212, 14);
		label_1.setText(Integer.toString(prodotto.getQtaMagazzinoCommmesse()));
		getContentPane().add(label_1);
		
		JLabel label = new JLabel("-");
		label.setBounds(323, 89, 212, 14);
		label.setText(prodotto.getSettoreMagazzinoCommesse());
		contentPane.add(label);
		
		
		JLabel label_2 = new JLabel("-");
		label_2.setBounds(323, 114, 212, 14);
		label_2.setText(Integer.toString(prodotto.getQtaMagazzinoDettaglio()));
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("-");
		label_3.setBounds(323, 139, 212, 14);
		label_3.setText(prodotto.getSettoreMagazzinoDettaglio());
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("-");
		label_4.setBounds(323, 164, 212, 14);
		label_4.setText(Integer.toString(prodotto.getQtaNegozio()));
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("-");
		label_5.setBounds(323, 188, 212, 14);
		label_5.setText(prodotto.getSettoreNegozio());
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("-");
		label_6.setBounds(323, 214, 212, 14);
		label_6.setText(Float.toString(prodotto.getPrezzo()));
		getContentPane().add(label_6);
		
		List<R8> listaR8 = HomeAreaAmministrativaGUI.r8DAO.getListaR8(nome);
		
		TabellaMaterieModel tabMaterie = new TabellaMaterieModel(listaR8);
		table = new JTable();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 248, 450, 171);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		table.setModel(tabMaterie);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(350);
		table.getColumnModel().getColumn(1).setPreferredWidth(97);
		
		
		JLabel lblCollegamentiRapidiRelativi = new JLabel("Collegamenti rapidi relativi al prodotto");
		lblCollegamentiRapidiRelativi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCollegamentiRapidiRelativi.setBounds(127, 430, 326, 25);
		getContentPane().add(lblCollegamentiRapidiRelativi);
		
		JLabel lblFatturaVenditaN = new JLabel("Fatture Vendite");
		lblFatturaVenditaN.setForeground(Color.BLUE);
		lblFatturaVenditaN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblFatturaVenditaN.setBounds(127, 466, 120, 14);
		getContentPane().add(lblFatturaVenditaN);
		
		Font font3 = lblFatturaVenditaN.getFont();
		Map attributes3 = font3.getAttributes();
		attributes3.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblFatturaVenditaN.setFont(font3.deriveFont(attributes3));
		lblFatturaVenditaN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblFatturaVenditaN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {		
					RisultatiFattureVenditeGUI ricFatture = new RisultatiFattureVenditeGUI("", "", "", prodotto.getNome());
					ricFatture.setVisible(true);
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
		
		
		JLabel lblOrdini = new JLabel("Ordini");
		lblOrdini.setForeground(Color.BLUE);
		lblOrdini.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblOrdini.setBounds(127, 490, 120, 14);
		getContentPane().add(lblOrdini);
		Font font = lblOrdini.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblOrdini.setFont(font.deriveFont(attributes));
		lblOrdini.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblOrdini.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {		
					RisultatiOrdiniGUI ricOrdini = new RisultatiOrdiniGUI("", "", "", prodotto.getNome());
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
		
		JButton btnNewButton = new JButton("Elimina prodotto");
		btnNewButton.setBounds(285, 527, 141, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnModificaOrdine = new JButton("Modifica prodotto");
		btnModificaOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificaOrdine.setBounds(436, 527, 146, 23);
		getContentPane().add(btnModificaOrdine);
		
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
				try {
					if(n == 1) {
						DettagliOrdineGUI dettagli = new DettagliOrdineGUI(codice);
						dispose();
						dettagli.setVisible(true);
					}else {
						DettagliFatturaVenditeGUI dettagli = new DettagliFatturaVenditeGUI(Integer.parseInt(codice), 2);
						dispose();
						dettagli.setVisible(true);
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(107, 0, 125, 558);
		contentPane.add(separator_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 107, 558);
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
		
		JLabel lblOrdini5 = new JLabel("Ordini");
		lblOrdini5.setForeground(Color.BLUE);
		lblOrdini5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOrdini5.setBounds(10, 79, 72, 14);
		panel.add(lblOrdini5);
		
		Font font6 = lblOrdini5.getFont();
		Map attributes6 = font6.getAttributes();
		attributes6.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblOrdini5.setFont(font6.deriveFont(attributes6));
		lblOrdini5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblOrdini5.addMouseListener(new MouseAdapter() {
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
		separator_1.setBounds(0, 556, 629, 25);
		contentPane.add(separator_1);
		
		JLabel lblEsposizioniInVetrina = new JLabel("Esposizioni in vetrina");
		lblEsposizioniInVetrina.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblEsposizioniInVetrina.setBounds(291, 466, 120, 14);
		contentPane.add(lblEsposizioniInVetrina);
		
		Font font2 = lblEsposizioniInVetrina.getFont();
		Map attributes2 = font2.getAttributes();
		lblEsposizioniInVetrina.setFont(font2.deriveFont(attributes2));
		lblEsposizioniInVetrina.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblStatisticheVendite = new JLabel("Statistiche Vendite");
		lblStatisticheVendite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblStatisticheVendite.setBounds(291, 490, 120, 14);
		contentPane.add(lblStatisticheVendite);

		
		
		Font font7 = lblStatisticheVendite.getFont();
		Map attributes7 = font7.getAttributes();
		lblStatisticheVendite.setFont(font7.deriveFont(attributes7));
		lblStatisticheVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}
}
