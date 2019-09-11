package com.vigorflex.gui;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.vigorflex.codice.Ordine;
import com.vigorflex.codice.Prodotto;
import com.vigorflex.codice.R2;
import com.vigorflex.dati.OrdineDAO;
import com.vigorflex.dati.ProdottoDAO;
import com.vigorflex.dati.R2DAO;

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

import javax.swing.SwingConstants;
import java.awt.Window.Type;

public class DettagliFatturaVenditeGUI extends JFrame{
	private JPanel contentPane;
	private static int numero;
	private static int n;

	JTable table;

	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DettagliFatturaVenditeGUI frame = new DettagliFatturaVenditeGUI(numero, n);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public DettagliFatturaVenditeGUI(int numero, int n) throws Exception {
		setTitle("Dettagli Fattura Vendite");
		setResizable(false);
		DettagliFatturaVenditeGUI.numero=numero;
		DettagliFatturaVenditeGUI.n=n;

		Ordine ordine = HomeAreaAmministrativaGUI.ordineDAO.trovaOrdine(numero);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblSchedaOrdine = new JLabel("Fattura Vendite n. " + numero);
		lblSchedaOrdine.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSchedaOrdine.setBounds(128, 36, 278, 14);
		getContentPane().add(lblSchedaOrdine);
		
		JLabel lblCodice = new JLabel("Numero");
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
		
		JLabel lblDataEmissione = new JLabel("Data emissione");
		lblDataEmissione.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblDataEmissione.setBounds(128, 161, 120, 14);
		getContentPane().add(lblDataEmissione);
		
		JLabel label = new JLabel("-");
		label.setBounds(295, 61, 212, 14);
		label.setText(Integer.toString(numero));
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
		
		JLabel label_7 = new JLabel("-");
		label_7.setBounds(295, 161, 212, 14);
		label_7.setText(ordine.getDataAcquisto().toString());
		getContentPane().add(label_7);
		
		List<R2> listaR2 = HomeAreaAmministrativaGUI.r2DAO.getListaR2(ordine.getCodice());
		
		TabellaProdottiModel tabProdotti = new TabellaProdottiModel(listaR2);
		table = new JTable();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 197, 450, 171);
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
						dettagli = new DettagliProdottoGUI(nome, 2, Integer.toString(numero));
						dispose();
			        	dettagli.setVisible(true);
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
			  }
		  });
		
		
		JLabel lblTotNetto = new JLabel("Importo");
		lblTotNetto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		lblTotNetto.setBounds(128, 404, 86, 14);
		getContentPane().add(lblTotNetto);
		
		JLabel lblSconto = new JLabel("Sconto %");
		lblSconto.setFont(new Font("Dialog", Font.ITALIC, 11));
		lblSconto.setBounds(128, 379, 86, 14);
		getContentPane().add(lblSconto);
		
		float somma = 0;
		for(R2 r : listaR2) {
			Prodotto p;
			p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(r.getProdotto());
			somma += r.getQta()*p.getPrezzo();
		}
		
		
		
		JLabel sconto = new JLabel("-");
		sconto.setBounds(209, 379, 46, 14);
		sconto.setText(Integer.toString(ordine.getSconto()));
		getContentPane().add(sconto);
		
		JLabel totNetto = new JLabel("-");
		totNetto.setBounds(209, 405, 46, 14);
		totNetto.setText(Float.toString(ordine.getImportoTotale()));
		getContentPane().add(totNetto);
	
		
		JButton btnInviaRiepilogo = new JButton("Invia riepilogo");
		btnInviaRiepilogo.setBounds(469, 434, 109, 23);
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
				try {
					if(n==1) {
						DettagliOrdineGUI schedaOrdine;
						schedaOrdine = new DettagliOrdineGUI(ordine.getCodice());
						dispose();
						schedaOrdine.setVisible(true);
					}else {
						RisultatiFattureVenditeGUI risFatture = new RisultatiFattureVenditeGUI();
						dispose();
						risFatture.setVisible(true);
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
		separator_2.setBounds(107, 0, 125, 468);
		contentPane.add(separator_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 107, 468);
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
		
		Font font = lblNewLabel_1.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblNewLabel_1.setFont(font.deriveFont(attributes));
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
		separator.setBounds(0, 470, 601, 14);
		contentPane.add(separator);
		
	}
	
}
