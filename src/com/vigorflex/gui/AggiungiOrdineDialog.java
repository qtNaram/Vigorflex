package com.vigorflex.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.vigorflex.codice.FatturaVendite;
import com.vigorflex.codice.Ordine;
import com.vigorflex.codice.Prodotto;
import com.vigorflex.codice.R2;
import com.vigorflex.dati.FatturaVenditeDAO;
import com.vigorflex.dati.OrdineDAO;
import com.vigorflex.dati.ProdottoDAO;
import com.vigorflex.dati.R2DAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.Random;
import java.beans.PropertyChangeEvent;
import javax.swing.JRadioButtonMenuItem;

public class AggiungiOrdineDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldCF;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldTelefono;
	private JTextField textFieldEmail;
	private JTextField textFieldSconto;
	private JRadioButton rdbtnEsterno;
	private JRadioButton rdbtnInterno;
	private JRadioButton rdbtnRitiroAMano;
	private JLabel label_MLS, label_MLF, label_MLM, label_MPS, label_MPF, label_MPM, label_MFS, label_MFF, label_MFM, label_CS;
	private JLabel label_IMPORTO, label_IVA, label_IMPORTO_TOT, label_TOT;
	private JSpinner spinner_MLS, spinner_MLF, spinner_MLM, spinner_MPS, spinner_MPF, spinner_MPM, spinner_MFS, spinner_MFF, spinner_MFM, spinner_CS;
	private JSpinner[] spinners = new JSpinner[10];
	private String[] nomiProdotti = new String[10];
	private JFrame finestra;
	private JComboBox comboBox;
	private ButtonGroup bg;
	
	
	private RisultatiOrdiniGUI risOrdini;
	private RicercaOrdiniGUI ricercaOrdini;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AggiungiOrdineDialog dialog = new AggiungiOrdineDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	
	public AggiungiOrdineDialog() {
		
		
		setTitle("Aggiungi o Modifica Ordine");
		setBounds(100, 100, 454, 819);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel  lblAggiungiOModifica = new JLabel("Aggiungi o Modifica Ordine");
			lblAggiungiOModifica.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblAggiungiOModifica.setBounds(10, 11, 418, 27);
			contentPanel.add(lblAggiungiOModifica);
		}
		{
			JLabel  lblCompilaIDati = new JLabel("Compila i dati anagrafici del cliente");
			lblCompilaIDati.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblCompilaIDati.setBounds(10, 41, 418, 14);
			contentPanel.add(lblCompilaIDati);
		}
		{
			 JLabel lblNome = new JLabel("Nome*");
			lblNome.setBounds(10, 66, 146, 14);
			contentPanel.add(lblNome);
		}
		{
			JLabel lblCognome = new JLabel("Cognome*");
			lblCognome.setBounds(10, 91, 146, 14);
			contentPanel.add(lblCognome);
		}
		{
			JLabel  lblCodiceFiscale = new JLabel("Codice Fiscale*");
			lblCodiceFiscale.setBounds(10, 116, 146, 12);
			contentPanel.add(lblCodiceFiscale);
		}
		{
			JLabel  lblIndirizzo = new JLabel("Indirizzo*");
			lblIndirizzo.setBounds(10, 139, 146, 14);
			contentPanel.add(lblIndirizzo);
		}
		{
			JLabel  lblTelefono = new JLabel("Telefono*");
			lblTelefono.setBounds(10, 164, 146, 14);
			contentPanel.add(lblTelefono);
		}
		{
			JLabel  lblIndirizzoEmail = new JLabel("Indirizzo e-mail*");
			lblIndirizzoEmail.setBounds(10, 188, 146, 14);
			contentPanel.add(lblIndirizzoEmail);
		}
		{
			JLabel  lblModalitDiConsegna = new JLabel("Modalit\u00E0 di consegna");
			lblModalitDiConsegna.setBounds(10, 213, 146, 14);
			contentPanel.add(lblModalitDiConsegna);
		}
		{
			JLabel  lblStato = new JLabel("Stato");
			lblStato.setBounds(10, 238, 146, 14);
			contentPanel.add(lblStato);
		}
		{
			JLabel  lblInserisciLaQuantit = new JLabel("Inserisci la quantit\u00E0 richiesta di ogni prodotto");
			lblInserisciLaQuantit.setFont(new Font("Tahoma", Font.ITALIC, 11));
			lblInserisciLaQuantit.setBounds(10, 315, 249, 14);
			contentPanel.add(lblInserisciLaQuantit);
		}
		{
			JLabel lblobbligatorio = new JLabel("*obbligatorio");
			lblobbligatorio.setBounds(10, 722, 89, 14);
			contentPanel.add(lblobbligatorio);
		}
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(152, 63, 276, 20);
		contentPanel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(152, 88, 276, 20);
		contentPanel.add(textFieldCognome);
		
		textFieldCF = new JTextField();
		textFieldCF.setColumns(10);
		textFieldCF.setBounds(152, 112, 276, 20);
		contentPanel.add(textFieldCF);
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setColumns(10);
		textFieldIndirizzo.setBounds(152, 136, 276, 20);
		contentPanel.add(textFieldIndirizzo);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(152, 161, 276, 20);
		contentPanel.add(textFieldTelefono);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(152, 185, 276, 20);
		contentPanel.add(textFieldEmail);
		
		comboBox = new JComboBox();
		comboBox.setBounds(152, 235, 120, 20);
		comboBox.addItem("Preventivo");
		comboBox.addItem("Pagato");
		comboBox.addItem("In attesa di approvv.");
		comboBox.addItem("In attesa di produz.");
		comboBox.addItem("In produzione");
		comboBox.addItem("In attesa di spedizione");
		comboBox.addItem("In attesa di trasporto");
		comboBox.addItem("Completato");

		contentPanel.add(comboBox);
		
		JLabel  lblMaterassoLatticeSingolo = new JLabel("Materasso Lattice Singolo");
		lblMaterassoLatticeSingolo.setBounds(26, 350, 246, 14);
		contentPanel.add(lblMaterassoLatticeSingolo);
		
		JLabel  lblMaterassoLatticeAlla = new JLabel("Materasso Lattice Alla Francese");
		lblMaterassoLatticeAlla.setBounds(26, 375, 246, 14);
		contentPanel.add(lblMaterassoLatticeAlla);
		
		JLabel  lblMaterassoLatticeMatrimoniale = new JLabel("Materasso Lattice Matrimoniale");
		lblMaterassoLatticeMatrimoniale.setBounds(26, 400, 246, 14);
		contentPanel.add(lblMaterassoLatticeMatrimoniale);
		{
			JLabel  lblMaterassoPoliuretanoAlla = new JLabel("Materasso Poliuretano Alla Francese");
			lblMaterassoPoliuretanoAlla.setBounds(26, 450, 246, 14);
			contentPanel.add(lblMaterassoPoliuretanoAlla);
		}
		{
			JLabel  lblMaterassoPoliuretanoMatrimoniale = new JLabel("Materasso Poliuretano Matrimoniale");
			lblMaterassoPoliuretanoMatrimoniale.setBounds(26, 475, 246, 14);
			contentPanel.add(lblMaterassoPoliuretanoMatrimoniale);
		}
		{
			JLabel  lblMaterasso = new JLabel("Materasso Poliuretano Singolo");
			lblMaterasso.setBounds(26, 425, 246, 14);
			contentPanel.add(lblMaterasso);
		}
		{
			JLabel  lblMaterassoMemoryFoam_1 = new JLabel("Materasso Memory Foam Alla Francese");
			lblMaterassoMemoryFoam_1.setBounds(26, 525, 246, 14);
			contentPanel.add(lblMaterassoMemoryFoam_1);
		}
		{
			JLabel  lblMaterassoMemoryFoam_2 = new JLabel("Materasso Memory Foam Matrimoniale");
			lblMaterassoMemoryFoam_2.setBounds(26, 550, 246, 14);
			contentPanel.add(lblMaterassoMemoryFoam_2);
		}
		{
			JLabel  lblMaterassoMemoryFoam = new JLabel("Materasso Memory Foam Singolo");
			lblMaterassoMemoryFoam.setBounds(26, 500, 246, 14);
			contentPanel.add(lblMaterassoMemoryFoam);
		}
		{
			JLabel  lblCuscinoStandard = new JLabel("Cuscino Standard");
			lblCuscinoStandard.setBounds(26, 575, 246, 14);
			contentPanel.add(lblCuscinoStandard);
		}
		
		spinner_MLF = new JSpinner();
		spinner_MLF.setBounds(282, 372, 48, 20);
		contentPanel.add(spinner_MLF);
		
		ChangeListener listener = new ChangeListener() {
			  public void stateChanged(ChangeEvent e) {
				String nomeProdotto = "Materasso Lattice Alla Francese";
				Prodotto p;
				try {
					p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
					float valore = Float.parseFloat(spinner_MLF.getValue().toString());
					Float prezzo = new Float(p.getPrezzo()*valore);
					label_MLF.setText(prezzo.toString());
					impostaImporto();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			};
		spinner_MLF.addChangeListener(listener);

		 spinner_MLM = new JSpinner();
		spinner_MLM.setBounds(282, 397, 48, 20);
		contentPanel.add(spinner_MLM);
		ChangeListener listener1 = new ChangeListener() {
			  public void stateChanged(ChangeEvent e) {
				String nomeProdotto = "Materasso Lattice Matrimoniale";
				Prodotto p;
				try {
					p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
					float valore = Float.parseFloat(spinner_MLM.getValue().toString());
					Float prezzo = new Float(p.getPrezzo()*valore);
					label_MLM.setText(prezzo.toString());
					impostaImporto();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			};
		spinner_MLM.addChangeListener(listener1);
		
		 spinner_MPS = new JSpinner();
		spinner_MPS.setBounds(282, 422, 48, 20);
		contentPanel.add(spinner_MPS);
		ChangeListener listener2 = new ChangeListener() {
			  public void stateChanged(ChangeEvent e) {
				String nomeProdotto = "Materasso Poliuretano Singolo";
				Prodotto p;
				try {
					p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
					float valore = Float.parseFloat(spinner_MPS.getValue().toString());
					Float prezzo = new Float(p.getPrezzo()*valore);
					label_MPS.setText(prezzo.toString());
					impostaImporto();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			};
		spinner_MPS.addChangeListener(listener2);
		
		 spinner_MPF = new JSpinner();
		spinner_MPF.setBounds(282, 447, 48, 20);
		contentPanel.add(spinner_MPF);
		ChangeListener listener3 = new ChangeListener() {
			  public void stateChanged(ChangeEvent e) {
				String nomeProdotto = "Materasso Poliuretano Alla Francese";
				Prodotto p;
				try {
					p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
					float valore = Float.parseFloat(spinner_MPF.getValue().toString());
					Float prezzo = new Float(p.getPrezzo()*valore);
					label_MPF.setText(prezzo.toString());
					impostaImporto();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			};
		spinner_MPF.addChangeListener(listener3);
		
		 spinner_MPM = new JSpinner();
		spinner_MPM.setBounds(282, 472, 48, 20);
		contentPanel.add(spinner_MPM);
		ChangeListener listener4 = new ChangeListener() {
			  public void stateChanged(ChangeEvent e) {
				String nomeProdotto = "Materasso Poliuretano Matrimoniale";
				Prodotto p;
				try {
					p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
					float valore = Float.parseFloat(spinner_MPM.getValue().toString());
					Float prezzo = new Float(p.getPrezzo()*valore);
					label_MPM.setText(prezzo.toString());
					impostaImporto();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  }
			};
		spinner_MPM.addChangeListener(listener4);
		
			 spinner_MFS = new JSpinner();
			spinner_MFS.setBounds(282, 497, 48, 20);
			contentPanel.add(spinner_MFS);
			
			ChangeListener listener5 = new ChangeListener() {
				  public void stateChanged(ChangeEvent e) {
					String nomeProdotto = "Materasso Memory Foam Singolo";
					Prodotto p;
					try {
						p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
						float valore = Float.parseFloat(spinner_MFS.getValue().toString());
						Float prezzo = new Float(p.getPrezzo()*valore);
						label_MFS.setText(prezzo.toString());
						impostaImporto();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }
				};
			spinner_MFS.addChangeListener(listener5);
		
		
			 spinner_MFF = new JSpinner();
			spinner_MFF.setBounds(282, 522, 48, 20);
			contentPanel.add(spinner_MFF);
			ChangeListener listener6 = new ChangeListener() {
				  public void stateChanged(ChangeEvent e) {
					String nomeProdotto = "Materasso Memory Foam Alla Francese";
					Prodotto p;
					try {
						p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
						float valore = Float.parseFloat(spinner_MFF.getValue().toString());
						Float prezzo = new Float(p.getPrezzo()*valore);
						label_MFF.setText(prezzo.toString());
						impostaImporto();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }
				};
			spinner_MFF.addChangeListener(listener6);
		
		
			 spinner_MFM = new JSpinner();
			spinner_MFM.setBounds(282, 547, 48, 20);
			contentPanel.add(spinner_MFM);
			ChangeListener listener7 = new ChangeListener() {
				  public void stateChanged(ChangeEvent e) {
					String nomeProdotto = "Materasso Memory Foam Matrimoniale";
					Prodotto p;
					try {
						p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
						float valore = Float.parseFloat(spinner_MFM.getValue().toString());
						Float prezzo = new Float(p.getPrezzo()*valore);
						label_MFM.setText(prezzo.toString());
						impostaImporto();
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }
				};
			spinner_MFM.addChangeListener(listener7);
		
		
			 spinner_CS = new JSpinner();
			spinner_CS.setBounds(282, 572, 48, 20);
			contentPanel.add(spinner_CS);
			ChangeListener listener8 = new ChangeListener() {
				  public void stateChanged(ChangeEvent e) {
					String nomeProdotto = "Cuscino Standard";
					Prodotto p;
					try {
						p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
						float valore = Float.parseFloat(spinner_CS.getValue().toString());
						Float prezzo = new Float(p.getPrezzo()*valore);
						label_CS.setText(prezzo.toString());
						impostaImporto();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }
				};
			spinner_CS.addChangeListener(listener8);
		
		
			 spinner_MLS = new JSpinner();
			spinner_MLS.setBounds(282, 347, 48, 20);
			contentPanel.add(spinner_MLS);
			ChangeListener listener9 = new ChangeListener() {
				  public void stateChanged(ChangeEvent e) {
					String nomeProdotto = "Materasso Lattice Singolo";
					Prodotto p;
					try {
						p = HomeAreaAmministrativaGUI.prodottoDAO.getProdotto(nomeProdotto);
						float valore = Float.parseFloat(spinner_MLS.getValue().toString());
						Float prezzo = new Float(p.getPrezzo()*valore);
						label_MLS.setText(prezzo.toString());
						impostaImporto();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  }
				};
			spinner_MLS.addChangeListener(listener9);
		
		
		spinners[0] = spinner_MLS; nomiProdotti[0] = "Materasso Lattice Singolo";
		spinners[1] = spinner_MLF; nomiProdotti[1] = "Materasso Lattice Alla Francese";
		spinners[2] = spinner_MLM; nomiProdotti[2] = "Materasso Lattice Matrimoniale";
		spinners[3] = spinner_MPS; nomiProdotti[3] = "Materasso Poliuretano Singolo";
		spinners[4] = spinner_MPF; nomiProdotti[4] = "Materasso Poliuretano Alla Francese";
		spinners[5] = spinner_MPM; nomiProdotti[5] = "Materasso Poliuretano Matrimoniale";
		spinners[6] = spinner_MFS; nomiProdotti[6] = "Materasso Memory Foam Singolo";
		spinners[7] = spinner_MFF; nomiProdotti[7] = "Materasso Memory Foam Alla Francese";
		spinners[8] = spinner_MFM; nomiProdotti[8] = "Materasso Memory Foam Matrimoniale";
		spinners[9] = spinner_CS; nomiProdotti[9] = "Cuscino Standard";
		
		
		{
			 label_MLM = new JLabel("0");
			label_MLM.setBounds(363, 400, 65, 14);
			contentPanel.add(label_MLM);
		}
		{
			 JLabel label = new JLabel("Prezzo");
			label.setBounds(363, 329, 46, 14);
			contentPanel.add(label);
		}
		{
			 label_MLS = new JLabel("0");
			label_MLS.setBounds(363, 354, 65, 14);
			contentPanel.add(label_MLS);
		}
		{
			 label_MLF = new JLabel("0");
			label_MLF.setBounds(363, 375, 65, 14);
			contentPanel.add(label_MLF);
		}
		{
			 label_MPS = new JLabel("0");
			label_MPS.setBounds(363, 425, 65, 14);
			contentPanel.add(label_MPS);
		}
		{
			 label_MPF = new JLabel("0");
			label_MPF.setBounds(363, 450, 65, 14);
			contentPanel.add(label_MPF);
		}
		{
			 label_MPM = new JLabel("0");
			label_MPM.setBounds(363, 475, 65, 14);
			contentPanel.add(label_MPM);
		}
		{
			 label_MFS = new JLabel("0");
			label_MFS.setBounds(363, 500, 65, 14);
			contentPanel.add(label_MFS);
		}
		{
			 label_MFF = new JLabel("0");
			label_MFF.setBounds(363, 525, 65, 14);
			contentPanel.add(label_MFF);
		}
		{
			 label_MFM = new JLabel("0");
			label_MFM.setBounds(363, 550, 65, 14);
			contentPanel.add(label_MFM);
		}
		{
			 label_CS = new JLabel("0");
			label_CS.setBounds(363, 575, 65, 14);
			contentPanel.add(label_CS);
		}
		{
			 JLabel lblImporto = new JLabel("Importo");
			lblImporto.setBounds(274, 606, 60, 14);
			contentPanel.add(lblImporto);
		}
		{
			 JLabel lblIva = new JLabel("IVA");
			lblIva.setBounds(276, 630, 58, 14);
			contentPanel.add(lblIva);
		}
		{
			 JLabel lblTotale = new JLabel("Totale");
			lblTotale.setBounds(274, 655, 60, 14);
			contentPanel.add(lblTotale);
		}
		{
			 JLabel lblSconto = new JLabel("Sconto %");
			lblSconto.setBounds(262, 680, 72, 14);
			contentPanel.add(lblSconto);
		}
		{
			 JLabel lblTotNetto = new JLabel("Tot. netto");
			lblTotNetto.setBounds(262, 703, 72, 14);
			contentPanel.add(lblTotNetto);
		}
		{
			 label_IMPORTO = new JLabel("0");
			label_IMPORTO.setBounds(363, 606, 65, 14);
			contentPanel.add(label_IMPORTO);
			
		
		}
		{
			 label_IVA = new JLabel("22%");
			label_IVA.setBounds(363, 630, 65, 14);
			contentPanel.add(label_IVA);
		}
		{
			 label_TOT = new JLabel("0");
			label_TOT.setBounds(363, 655, 65, 14);
			contentPanel.add(label_TOT);
		}
		{
			 label_IMPORTO_TOT = new JLabel("0");
			label_IMPORTO_TOT.setBounds(363, 703, 65, 14);
			contentPanel.add(label_IMPORTO_TOT);
		}
		{
			textFieldSconto = new JTextField();
			textFieldSconto.setColumns(10);
			textFieldSconto.setBounds(352, 677, 35, 20);
			textFieldSconto.setText("0");
			contentPanel.add(textFieldSconto);
			
			textFieldSconto.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
				    impostaImporto();
				  }
				public void removeUpdate(DocumentEvent e) {
					
				  }
				 public void insertUpdate(DocumentEvent e) {
					impostaImporto();
				  }
			});
		
		rdbtnInterno = new JRadioButton("Interno");
		rdbtnInterno.setBounds(225, 209, 78, 23);
		contentPanel.add(rdbtnInterno);
		
		rdbtnRitiroAMano = new JRadioButton("Ritiro a mano");
		rdbtnRitiroAMano.setBounds(300, 209, 128, 23);
		contentPanel.add(rdbtnRitiroAMano);
		
		rdbtnEsterno = new JRadioButton("Esterno");
		rdbtnEsterno.setBounds(152, 209, 78, 23);
		contentPanel.add(rdbtnEsterno);
		
		bg = new ButtonGroup();
		bg.add(rdbtnEsterno);
		bg.add(rdbtnInterno);
		bg.add(rdbtnRitiroAMano);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Conferma");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							aggiungiSchedaOrdine();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK"); 
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annulla");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		}
	}
		
		private void impostaImporto() {
			float f1 = Float.parseFloat(label_MLS.getText());
			float f2 = Float.parseFloat(label_MLF.getText());
			float f3 = Float.parseFloat(label_MLM.getText());
			float f4 = Float.parseFloat(label_MPS.getText());
			float f5 = Float.parseFloat(label_MPF.getText());
			float f6 = Float.parseFloat(label_MPM.getText());
			float f7 = Float.parseFloat(label_MFS.getText());
			float f8 = Float.parseFloat(label_MFF.getText());
			float f9 = Float.parseFloat(label_MFM.getText());
			float f10 = Float.parseFloat(label_CS.getText());
			
			Float somma = new Float(f1+f2+f3+f4+f5+f6+f7+f8+f9+f10);
			
			label_IMPORTO.setText(somma.toString());
			Float sommaTot = new Float(((somma*22)/100)+somma);
			label_TOT.setText(sommaTot.toString());
			Integer sconto = Integer.parseInt(textFieldSconto.getText());
			Float importoTot = new Float(sommaTot-(sommaTot*sconto)/100);
			label_IMPORTO_TOT.setText(importoTot.toString());
			

			
		}
		protected void aggiungiSchedaOrdine() throws Exception {

			Random r = new Random();
			int x = r.nextInt(999); //codice ordine
			int y = r.nextInt(999); //numero fattura
			
			String codice = Integer.toString(x);
			String nome = textFieldNome.getText();
			String cognome = textFieldCognome.getText();
			String cf = textFieldCF.getText();
			String indirizzo = textFieldIndirizzo.getText();
			String email = textFieldEmail.getText();
			String telefono = textFieldTelefono.getText();
			
			String modalitaConsegna = null;
		
			if(rdbtnEsterno.isSelected()) modalitaConsegna = rdbtnEsterno.getText();
			else if(rdbtnInterno.isSelected()) modalitaConsegna = rdbtnInterno.getText();
			else modalitaConsegna = rdbtnRitiroAMano.getText();
				
			String stato = String.valueOf(comboBox.getSelectedItem());
					
			String sc = textFieldSconto.getText();
			int sconto = Integer.parseInt(sc);
			
			float importoTotale = Float.parseFloat(label_IMPORTO_TOT.getText());
			
			Date dataAcquisto = new Date(System.currentTimeMillis());
			
			Ordine ordineTmp = new Ordine(codice, nome, cognome, cf, indirizzo, telefono, email, modalitaConsegna, stato,
					importoTotale, sconto, y, dataAcquisto);
			
			FatturaVendite fatturaTmp = new FatturaVendite(y, nome, cognome, cf, indirizzo, dataAcquisto, importoTotale, sconto);
			
			try {
				
				
				for(int i=0; i<spinners.length; i++) {
					
						try {
							if(Integer.parseInt(spinners[i].getValue().toString()) > 0) 
								HomeAreaAmministrativaGUI.prodottoDAO.modificaQta(nomiProdotti[i], Integer.parseInt(spinners[i].getValue().toString()));
						}catch(Exception e) {
							JOptionPane.showMessageDialog(
									risOrdini,
									"Errore: scorte per prodotti selezionati esaurite", "Errore",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
				}
				
				
				HomeAreaAmministrativaGUI.ordineDAO.aggiungiOrdine(ordineTmp);
				HomeAreaAmministrativaGUI.fatturaVenditeDAO.aggiungiFatturaVendite(fatturaTmp);
				
				boolean check = false;
				for(int i=0; i<spinners.length; i++) {
					if(Integer.parseInt(spinners[i].getValue().toString()) > 0) {
						R2 r2Tmp = new R2(ordineTmp.getCodice(), nomiProdotti[i], Integer.parseInt(spinners[i].getValue().toString()));
						HomeAreaAmministrativaGUI.r2DAO.aggiungiR2(r2Tmp);
						
						check = HomeAreaAmministrativaGUI.prodottoDAO.controllaQta(nomiProdotti[i]);
					}
				}
				
				dispose();

				if(check) {
					JOptionPane.showMessageDialog(
							risOrdini,
							"Avviso: scorte per prodotti selezionati in esaurimento", "Avviso",
							JOptionPane.WARNING_MESSAGE);
				}
				
				if(risOrdini != null) {
					risOrdini.aggiornaLista();
					JOptionPane.showMessageDialog(risOrdini, "Ordine aggiunto con successo.", "Ordine aggiunto", JOptionPane.INFORMATION_MESSAGE);
				}
				else JOptionPane.showMessageDialog(ricercaOrdini, "Ordine aggiunto con successo.", "Ordine aggiunto", JOptionPane.INFORMATION_MESSAGE);

			}catch(Exception e) {
				e.printStackTrace();

				JOptionPane.showMessageDialog(
						risOrdini,
						"Errore: "
								+ e.getMessage(), "Errore",
						JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		  
	}



