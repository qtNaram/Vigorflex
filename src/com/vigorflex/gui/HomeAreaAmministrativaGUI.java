package com.vigorflex.gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.vigorflex.dati.FatturaVenditeDAO;
import com.vigorflex.dati.OrdineDAO;
import com.vigorflex.dati.ProdottoDAO;
import com.vigorflex.dati.R2DAO;
import com.vigorflex.dati.R8DAO;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Map;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Cursor;

public class HomeAreaAmministrativaGUI extends JFrame {

	private JPanel contentPane;
	
	
	public static OrdineDAO ordineDAO;
	public static ProdottoDAO prodottoDAO;
	public static R2DAO r2DAO;
	public static R8DAO r8DAO;
	public static FatturaVenditeDAO fatturaVenditeDAO;
	private String codice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeAreaAmministrativaGUI frame = new HomeAreaAmministrativaGUI();
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
	public HomeAreaAmministrativaGUI() {
		setResizable(false);
		
		try {
			ordineDAO = new OrdineDAO();
			prodottoDAO = new ProdottoDAO();
			r2DAO = new R2DAO();
			r8DAO = new R8DAO();
			fatturaVenditeDAO = new FatturaVenditeDAO();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this,  "Errore: " + e, "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("Home Area Amministrativa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHome.setBounds(127, 21, 67, 20);
		contentPane.add(lblHome);
	
		JLabel lblNewLabel = new JLabel("Accesso effettuato da");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel.setBounds(453, 22, 107, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAreaAmministrativa = new JLabel("Area Amministrativa");
		lblAreaAmministrativa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAreaAmministrativa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblAreaAmministrativa.setBounds(440, 35, 120, 14);
		contentPane.add(lblAreaAmministrativa);
		
		JLabel lblLogout = new JLabel("Esci");
		lblLogout.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLogout.setBounds(525, 47, 35, 19);
		contentPane.add(lblLogout);
		
		Font font2 = lblLogout.getFont();
		Map attributes2 = font2.getAttributes();
		attributes2.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblLogout.setFont(font2.deriveFont(attributes2));
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btnInviaNotifica = new JButton("Invia Notifica");
		btnInviaNotifica.setBounds(446, 251, 114, 23);
		contentPane.add(btnInviaNotifica);
		
		JLabel lblAlert = new JLabel("Alert");
		lblAlert.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblAlert.setBounds(127, 81, 67, 20);
		contentPane.add(lblAlert);
		
		JLabel lblNotifiche = new JLabel("Notifiche");
		lblNotifiche.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNotifiche.setBounds(127, 250, 67, 20);
		contentPane.add(lblNotifiche);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(273, 298, 125, 61);
		contentPane.add(separator_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 107, 463);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Home");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 54, 46, 14);
		panel.add(lblNewLabel_1);
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(127, 112, 433, 20);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(127, 154, 433, 20);
		contentPane.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(127, 195, 433, 20);
		contentPane.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(127, 298, 433, 20);
		contentPane.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(127, 339, 433, 20);
		contentPane.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.LIGHT_GRAY);
		panel_6.setBounds(127, 386, 433, 20);
		contentPane.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setBounds(127, 423, 433, 20);
		contentPane.add(panel_7);
		
		JLabel lblRicevute = new JLabel("Ricevute");
		lblRicevute.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRicevute.setBounds(127, 281, 72, 14);
		contentPane.add(lblRicevute);
		
		JLabel lblInviate = new JLabel("Inviate");
		lblInviate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInviate.setBounds(127, 370, 72, 14);
		contentPane.add(lblInviate);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(108, 0, 125, 463);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 464, 591, 20);
		contentPane.add(separator_1);
	}
}
