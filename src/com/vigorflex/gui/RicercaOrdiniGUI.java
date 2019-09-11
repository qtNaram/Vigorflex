package com.vigorflex.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import com.vigorflex.dati.OrdineDAO;
import com.vigorflex.dati.ProdottoDAO;
import com.vigorflex.dati.R2DAO;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Map;
import java.awt.Font;

public class RicercaOrdiniGUI extends JFrame {

	private JPanel contentPane;
	private JTextField ricercaOrdiniTextField;
	private JButton btnReset;

	private JLabel lblRicercaOrdini;
	private JButton btnNewButton_1;
	private String codice;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaOrdiniGUI frame = new RicercaOrdiniGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RicercaOrdiniGUI() {
		setResizable(false);
		

		setTitle("Ricerca Ordini");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextField textField_da = new JTextField();
		textField_da.setBounds(253, 147, 86, 20);
		contentPane.add(textField_da);
		textField_da.setColumns(10);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblData.setBounds(127, 150, 46, 14);
		contentPane.add(lblData);
		
		JTextField textField_a = new JTextField();
		textField_a.setColumns(10);
		textField_a.setBounds(378, 147, 86, 20);
		contentPane.add(textField_a);
		
		JLabel lblDa = new JLabel("da");
		lblDa.setBounds(236, 150, 27, 14);
		contentPane.add(lblDa);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(107, 0, 125, 391);
		contentPane.add(separator_2);
		
		JLabel lblA = new JLabel("a");
		lblA.setBounds(366, 150, 27, 14);
		contentPane.add(lblA);
		
		
		JButton btnNewButton = new JButton("Cerca");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					codice = ricercaOrdiniTextField.getText();
					
					String dataDa;
					String dataA;
					
					dataDa = textField_da.getText();
					dataA = textField_a.getText();
					
					if(!dataDa.equals("") && !dataA.equals("")) {
						String daFormattata = ""+dataDa.charAt(6)+dataDa.charAt(7)+dataDa.charAt(8)+dataDa.charAt(9)+"/"+dataDa.charAt(3)+dataDa.charAt(4)+"/"+dataDa.charAt(0)+dataDa.charAt(1);
						String aFormattata = ""+dataA.charAt(6)+dataA.charAt(7)+dataA.charAt(8)+dataA.charAt(9)+"/"+dataA.charAt(3)+dataA.charAt(4)+"/"+dataA.charAt(0)+dataA.charAt(1);

						RisultatiOrdiniGUI risOrdini = new RisultatiOrdiniGUI(codice, daFormattata, aFormattata, "");
						risOrdini.setVisible(true);

					}else {
						RisultatiOrdiniGUI risOrdini = new RisultatiOrdiniGUI(codice, "", "", "");
						risOrdini.setVisible(true);

					}
					
					dispose();
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(RicercaOrdiniGUI.this,  "Errore: " + e, "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(375, 356, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblInserisciUnaParolachiave = new JLabel("Inserisci una parolachiave e/o un periodo temporale per effettuare la ricerca");
		lblInserisciUnaParolachiave.setBounds(127, 67, 463, 14);
		contentPane.add(lblInserisciUnaParolachiave);
		
		ricercaOrdiniTextField = new JTextField();
		ricercaOrdiniTextField.setBounds(127, 92, 433, 20);
		contentPane.add(ricercaOrdiniTextField);
		ricercaOrdiniTextField.setColumns(10);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ricercaOrdiniTextField.setText("");
				textField_a.setText("");
				textField_da.setText("");
				
			}
		});
		btnReset.setBounds(474, 356, 89, 23);
		contentPane.add(btnReset);
		
		lblRicercaOrdini = new JLabel("Ricerca Ordini");
		lblRicercaOrdini.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRicercaOrdini.setBounds(127, 36, 124, 14);
		contentPane.add(lblRicercaOrdini);
		
		btnNewButton_1 = new JButton("Aggiungi Ordine");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AggiungiOrdineDialog aggOrdine = new AggiungiOrdineDialog();
				aggOrdine.setVisible(true);
			}
		});

		btnNewButton_1.setBounds(425, 34, 135, 23);
		contentPane.add(btnNewButton_1);
		
		

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
				HomeAreaAmministrativaGUI home;
				try {
					home = new HomeAreaAmministrativaGUI();
					home.setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 107, 391);
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
		separator.setBounds(107, 390, 476, 20);
		contentPane.add(separator);
		
	
	}
}
