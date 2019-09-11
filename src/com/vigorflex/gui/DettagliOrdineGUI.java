package com.vigorflex.gui;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.vigorflex.codice.Ordine;
import com.vigorflex.codice.Prodotto;
import com.vigorflex.codice.R2;

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

public class DettagliOrdineGUI extends JFrame{
	private JPanel contentPane;
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
					DettagliOrdineGUI frame = new DettagliOrdineGUI(codice);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public DettagliOrdineGUI(String codice) throws Exception {
		setResizable(false);
		setTitle("Dettagli Ordine");
		DettagliOrdineGUI.codice=codice;
		
		Ordine ordine = HomeAreaAmministrativaGUI.ordineDAO.trovaOrdine(codice);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 803);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblSchedaOrdine = new JLabel("Scheda Ordine "+codice);
		lblSchedaOrdine.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSchedaOrdine.setBounds(128, 36, 186, 14);
		getContentPane().add(lblSchedaOrdine);
		
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblCodice.setBounds(128, 61, 120, 14);
		getContentPane().add(lblCodice);
		
		JLabel lblNominativo = new JLabel("Nominativo");
		lblNominativo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNominativo.setBounds(128, 86, 120, 14);
		getContentPane().add(lblNominativo);
		
		JLabel lblCodiceFiscale = new JLabel("Codice Fiscale");
		lblCodiceFiscale.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblCodiceFiscale.setBounds(128, 111, 120, 14);
		getContentPane().add(lblCodiceFiscale);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblIndirizzo.setBounds(128, 136, 120, 14);
		getContentPane().add(lblIndirizzo);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblTelefono.setBounds(128, 161, 120, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblIndirizzoEmail = new JLabel("Indirizzo e-mail");
		lblIndirizzoEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblIndirizzoEmail.setBounds(128, 185, 120, 14);
		getContentPane().add(lblIndirizzoEmail);
		
		JLabel lblModalitDiConsegna = new JLabel("Modalit\u00E0 di consegna");
		lblModalitDiConsegna.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblModalitDiConsegna.setBounds(128, 211, 120, 14);
		getContentPane().add(lblModalitDiConsegna);
		
		JLabel lblDataEmissione = new JLabel("Data emissione");
		lblDataEmissione.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblDataEmissione.setBounds(128, 238, 120, 14);
		getContentPane().add(lblDataEmissione);
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblStato.setBounds(128, 263, 120, 14);
		getContentPane().add(lblStato);
		
		JLabel label = new JLabel("-");
		label.setBounds(295, 61, 212, 14);
		label.setText(codice);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("-");
		label_1.setBounds(295, 86, 212, 14);
		label_1.setText(ordine.getNome()+" "+ordine.getCognome());
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("-");
		label_2.setBounds(295, 111, 212, 14);
		label_2.setText(ordine.getCf());
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("-");
		label_3.setBounds(295, 136, 212, 14);
		label_3.setText(ordine.getIndirizzo());
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("-");
		label_4.setBounds(295, 161, 212, 14);
		label_4.setText(ordine.getTelefono());
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("-");
		label_5.setBounds(295, 185, 212, 14);
		label_5.setText(ordine.getEmail());
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("-");
		label_6.setBounds(295, 211, 212, 14);
		label_6.setText(ordine.getModalitaConsegna());
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("-");
		label_7.setBounds(295, 238, 212, 14);
		label_7.setText(ordine.getDataAcquisto().toString());
		getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("-");
		label_8.setBounds(295, 263, 212, 14);
		label_8.setText(ordine.getStato());
		getContentPane().add(label_8);
		
		List<R2> listaR2 = HomeAreaAmministrativaGUI.r2DAO.getListaR2(codice);

		TabellaProdottiModel tabProdotti = new TabellaProdottiModel(listaR2);
		table = new JTable();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 291, 450, 171);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(table);
		table.setModel(tabProdotti);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setPreferredWidth(76);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		
		
		table.addMouseListener(new java.awt.event.MouseAdapter(){
			  public void mouseClicked(java.awt.event.MouseEvent e){
				  int row=table.rowAtPoint(e.getPoint());
				  int col= table.columnAtPoint(e.getPoint());
				  String nome = table.getValueAt(row, col).toString();
				  DettagliProdottoGUI dettagli;
					try {
						dettagli = new DettagliProdottoGUI(nome, 1, codice);
						dispose();
			        	dettagli.setVisible(true);
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
			  }
		  });
		
		JLabel lblImporto_1 = new JLabel("Importo");
		lblImporto_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		lblImporto_1.setBounds(128, 473, 86, 14);
		getContentPane().add(lblImporto_1);
		
		JLabel lblTotale = new JLabel("Totale");
		lblTotale.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		lblTotale.setBounds(128, 523, 86, 14);
		getContentPane().add(lblTotale);
		
		JLabel lblTotNetto = new JLabel("Tot. netto");
		lblTotNetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		lblTotNetto.setBounds(128, 573, 86, 14);
		getContentPane().add(lblTotNetto);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblIva.setBounds(128, 498, 86, 14);
		getContentPane().add(lblIva);
		
		JLabel lblSconto = new JLabel("Sconto %");
		lblSconto.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblSconto.setBounds(128, 548, 86, 14);
		getContentPane().add(lblSconto);
		
		float somma = 0;
		for(R2 r : listaR2) {
			Prodotto p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(r.getProdotto());
			somma += r.getQta()*p.getPrezzo();
		}
		
		JLabel importo1 = new JLabel("-");
		importo1.setBounds(209, 474, 46, 14);
		importo1.setText(Float.toString(somma));
		getContentPane().add(importo1);
		
		float i = (somma*22)/100;
		JLabel iva = new JLabel("-");
		iva.setBounds(209, 498, 46, 14);
		iva.setText(Float.toString(i));
		getContentPane().add(iva);
		
		JLabel totale = new JLabel("-");
		totale.setBounds(209, 524, 46, 14);
		totale.setText(Float.toString(somma+i));
		getContentPane().add(totale);
		
		JLabel sconto = new JLabel("-");
		sconto.setBounds(209, 548, 46, 14);
		sconto.setText(Integer.toString(ordine.getSconto()));
		getContentPane().add(sconto);
		
		JLabel totNetto = new JLabel("-");
		totNetto.setBounds(209, 574, 46, 14);
		totNetto.setText(Float.toString(ordine.getImportoTotale()));
		getContentPane().add(totNetto);
		
		JLabel lblCollegamentiRapidiRelativi = new JLabel("Collegamenti rapidi relativi all'ordine");
		lblCollegamentiRapidiRelativi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCollegamentiRapidiRelativi.setBounds(128, 598, 326, 25);
		getContentPane().add(lblCollegamentiRapidiRelativi);
		
		JLabel lblFatturaVenditaN = new JLabel("Fattura Vendite N.");
		lblFatturaVenditaN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblFatturaVenditaN.setBounds(128, 634, 120, 14);
		getContentPane().add(lblFatturaVenditaN);
		
		JLabel lblPraticaSpedizione = new JLabel("Pratica Spedizione");
		lblPraticaSpedizione.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPraticaSpedizione.setBounds(128, 658, 120, 14);
		getContentPane().add(lblPraticaSpedizione);
		
		JLabel lblPraticaTrasporto = new JLabel("Pratica Trasporto");
		lblPraticaTrasporto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblPraticaTrasporto.setBounds(128, 683, 120, 14);
		getContentPane().add(lblPraticaTrasporto);
		
		JLabel label_fattura = new JLabel("-");
		label_fattura.setForeground(Color.BLUE);
		label_fattura.setBounds(268, 634, 46, 14);
		label_fattura.setText(Integer.toString(ordine.getNumFatturaVend()));
		
		Font font = label_fattura.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		label_fattura.setFont(font.deriveFont(attributes));
		label_fattura.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		label_fattura.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					DettagliFatturaVenditeGUI schedaFattura = new DettagliFatturaVenditeGUI(Integer.parseInt(label_fattura.getText()), 1);
					schedaFattura.setVisible(true);
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
		
		getContentPane().add(label_fattura);
		
		JLabel label_spedizione = new JLabel("-");
		label_spedizione.setBounds(268, 658, 46, 14);
		getContentPane().add(label_spedizione);
		
		JLabel label_trasporto = new JLabel("-");
		label_trasporto.setBounds(268, 683, 46, 14);
		getContentPane().add(label_trasporto);
		
		JButton btnNewButton = new JButton("Elimina ordine");
		btnNewButton.setBounds(194, 717, 120, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HomeAreaAmministrativaGUI.ordineDAO.eliminaOrdine(codice);
					JOptionPane.showMessageDialog(DettagliOrdineGUI.this, "Ordine eliminato con successo.", "Ordine eliminato", JOptionPane.INFORMATION_MESSAGE);
					
					RisultatiOrdiniGUI risOrdini;
					try {
						risOrdini = new RisultatiOrdiniGUI(RisultatiOrdiniGUI.criterio, "", "", "");
						risOrdini.setVisible(true);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton btnModificaOrdine = new JButton("Modifica ordine");
		btnModificaOrdine.setBounds(324, 717, 122, 23);
		getContentPane().add(btnModificaOrdine);
		
		JButton btnInviaRiepilogo = new JButton("Invia riepilogo");
		btnInviaRiepilogo.setBounds(456, 717, 120, 23);
		getContentPane().add(btnInviaRiepilogo);
		
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
				RisultatiOrdiniGUI risOrdini;
				try {
					risOrdini = new RisultatiOrdiniGUI(RisultatiOrdiniGUI.criterio, "", "", "");
					risOrdini.setVisible(true);
					
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
		separator_2.setBounds(107, 0, 125, 753);
		contentPane.add(separator_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 107, 753);
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
		Map attributes5 = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
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
		
	}
	
}
