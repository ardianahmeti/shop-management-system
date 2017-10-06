package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.awt.event.ActionEvent;
//import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import gui.db_connection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;



public class MainPage {

	private JFrame frmMyshop;
	
	
	private double s_width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private double s_height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int pid;
	private int tableCounter=0;
	
	
	private DefaultTableModel model;

	private DefaultTableModel model2;
	
	private JTextField txtSearchProduct;
	
	JLabel lblData = new JLabel("");
	JLabel lblOra = new JLabel("");
	private JTextField txtTotal;
	private JTable tblProducts;
	private JTable tblCart;
	private JTable tblFurnizuesit;
	
	
	
	
	//DB Connection!
	Connection conn = null;
	ResultSet res = null;
	PreparedStatement pst = null;
	private JTextField txtProdId;
	private JTextField txtProdName;
	private JTextField txtProdStock;
	private JTextField txtProdPrice;
	private JTextField txtKerkoFurnizues;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtFID;
	private JTextField txtFEmri;
	private JTextField txtFFirma;
	private JTextField txtFTel;
	
	
	
	
	
	// clock function - use it to show date and time
		
	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar cal = new GregorianCalendar();
						int month = cal.get(Calendar.MONTH);
						int day = cal.get(Calendar.DAY_OF_MONTH);
						int year = cal.get(Calendar.YEAR);

						int sec = cal.get(Calendar.SECOND);
						int min = cal.get(Calendar.MINUTE);
						int hour = cal.get(Calendar.HOUR);

						lblOra.setText(
								String.valueOf(hour) + ":" + String.valueOf(min) + ":" + String.valueOf(sec));
						lblData.setText(
								String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));

						sleep(1000);
					}
				} catch (Exception iex) {
					iex.printStackTrace();

				}

			}

		};
		clock.start();
	}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frmMyshop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		HashMap hm = new HashMap();
		
		frmMyshop = new JFrame();
		frmMyshop.setTitle("MyShop");
		frmMyshop.setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/images/LogoPng.png")));
		frmMyshop.getContentPane().setBackground(new Color(0, 102, 153));
		frmMyshop.setBounds(0, 0, (int)s_width, (int)s_height);
		frmMyshop.setExtendedState(frmMyshop.MAXIMIZED_BOTH);
		frmMyshop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyshop.getContentPane().setLayout(null);
		
		
		JPanel pnlProducts = new JPanel();
		pnlProducts.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 102, 153)));
		pnlProducts.setBackground(Color.WHITE);
		pnlProducts.setBounds(210, 0, 1162, 678);
		frmMyshop.getContentPane().add(pnlProducts);
		pnlProducts.setLayout(null);
		
		
		JPanel pnlProductInfo = new JPanel();
		pnlProductInfo.setVisible(false);
		pnlProductInfo.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		pnlProductInfo.setBackground(new Color(0, 102, 153));
		pnlProductInfo.setBounds(169, 43, 683, 291);
		pnlProducts.add(pnlProductInfo);
		pnlProductInfo.setLayout(null);
		
		
		
		JLabel lblTDhnatE = new JLabel("T\u00CB DH\u00CBNAT E PRODUKTIT");
		lblTDhnatE.setFont(new Font("Courier New", Font.BOLD, 26));
		lblTDhnatE.setForeground(Color.WHITE);
		lblTDhnatE.setBounds(177, 41, 336, 30);
		pnlProductInfo.add(lblTDhnatE);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Courier New", Font.BOLD, 14));
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(27, 105, 24, 17);
		pnlProductInfo.add(lblId);
		
		JLabel lblEmri = new JLabel("EMRI I PRODUKTIT:");
		lblEmri.setFont(new Font("Courier New", Font.BOLD, 14));
		lblEmri.setForeground(Color.WHITE);
		lblEmri.setBounds(177, 105, 136, 17);
		pnlProductInfo.add(lblEmri);
		
		JLabel lblSasiaNDepo = new JLabel("SASIA N\u00CB DEPO");
		lblSasiaNDepo.setFont(new Font("Courier New", Font.BOLD, 14));
		lblSasiaNDepo.setForeground(Color.WHITE);
		lblSasiaNDepo.setBounds(395, 105, 104, 17);
		pnlProductInfo.add(lblSasiaNDepo);
		
		JLabel lblCmimiPerCope = new JLabel("\u00C7MIMI P\u00CBR COP\u00CB");
		lblCmimiPerCope.setFont(new Font("Courier New", Font.BOLD, 14));
		lblCmimiPerCope.setForeground(Color.WHITE);
		lblCmimiPerCope.setBounds(532, 105, 112, 17);
		pnlProductInfo.add(lblCmimiPerCope);
		
		txtProdId = new JTextField();
		txtProdId.setEditable(false);
		txtProdId.setBounds(27, 133, 110, 25);
		pnlProductInfo.add(txtProdId);
		txtProdId.setColumns(10);
		
		txtProdName = new JTextField();
		txtProdName.setBounds(167, 133, 197, 25);
		pnlProductInfo.add(txtProdName);
		txtProdName.setColumns(10);
		
		txtProdStock = new JTextField();
		txtProdStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) 
			{
				char vChar = e.getKeyChar();

				if (!Character.isDigit(vChar)) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				} 
			}
		});
		txtProdStock.setText("");
		txtProdStock.setBounds(395, 133, 110, 25);
		pnlProductInfo.add(txtProdStock);
		txtProdStock.setColumns(10);
		
		txtProdPrice = new JTextField();
		txtProdPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char vChar = e.getKeyChar();

				if (!(Character.isDigit(vChar) || vChar=='.')) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				} 
			}
		});
		txtProdPrice.setText("");
		txtProdPrice.setBounds(532, 133, 120, 25);
		pnlProductInfo.add(txtProdPrice);
		txtProdPrice.setColumns(10);
		
		JLabel lblX = new JLabel("");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				txtProdId.setText("");
				txtProdName.setText("");
				txtProdStock.setText("");
				txtProdPrice.setText("");
				pnlProductInfo.setVisible(false);
				
			}
		});
		lblX.setIcon(new ImageIcon(MainPage.class.getResource("/images/xicon.jpg")));
		lblX.setBounds(648, 5, 30, 30);
		pnlProductInfo.add(lblX);
		
		JButton btnShtoProduktin = new JButton("Shto Produktin");
		btnShtoProduktin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try{
					conn = db_connection.connectDB();
					String insert = "insert into products (pname,pstock,pprice) values('"+txtProdName.getText()+" ','"+txtProdStock.getText()
										+"','"+Double.parseDouble(txtProdPrice.getText())+"');";
					pst = conn.prepareStatement(insert);
					pst.execute();
					pst.close();
					
					JOptionPane.showMessageDialog(null, "Produkti u shtua me sukses!");
					
					txtProdId.setText("");
					txtProdName.setText("");
					txtProdStock.setText("");
					txtProdPrice.setText("");
					updateTable();
					pnlProductInfo.setVisible(false);
					
					}
					catch (Exception eUpdate)
					{
						JOptionPane.showMessageDialog(null, "Të dhënat nuk mund të ruhen!\n Error: "+eUpdate.toString());
					}
			}
		});
		btnShtoProduktin.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 255, 204)));
		btnShtoProduktin.setBackground(new Color(0, 102, 153));
		btnShtoProduktin.setFont(new Font("Courier New", Font.BOLD, 17));
		btnShtoProduktin.setForeground(Color.WHITE);
		btnShtoProduktin.setBounds(405, 187, 247, 30);
		pnlProductInfo.add(btnShtoProduktin);
		

		
		
		JButton btnRuajTeDhenat = new JButton("Ruaj t\u00EB Dh\u00EBnat");
		btnRuajTeDhenat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try{
				conn = db_connection.connectDB();
				String update = "Update  products set pname='"+txtProdName.getText()+" ', pstock='"+txtProdStock.getText()
									+"  ', pprice='"+Double.parseDouble(txtProdPrice.getText())+"' where pid=' "+txtProdId.getText()+"  ';";
				pst = conn.prepareStatement(update);
				pst.execute();
				pst.close();
				
				JOptionPane.showMessageDialog(null, "Të dhënat u ruajtën me sukses!");
				
				txtProdId.setText("");
				txtProdName.setText("");
				txtProdStock.setText("");
				txtProdPrice.setText("");
				updateTable();
				pnlProductInfo.setVisible(false);
				
				}
				catch (Exception eUpdate)
				{
					JOptionPane.showMessageDialog(null, "Të dhënat nuk mund të ruhen!\n Error: "+eUpdate.toString());
				}

			}
		});
		btnRuajTeDhenat.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 255, 204)));
		btnRuajTeDhenat.setBackground(new Color(0, 102, 153));
		btnRuajTeDhenat.setFont(new Font("Courier New", Font.BOLD, 17));
		btnRuajTeDhenat.setForeground(Color.WHITE);
		btnRuajTeDhenat.setBounds(405, 222, 247, 30);
		pnlProductInfo.add(btnRuajTeDhenat);
		
	    
		
		
		
		JLabel lblProductsList = new JLabel("LISTA E PRODUKTEVE");
		lblProductsList.setFont(new Font("Courier New", Font.BOLD, 18));
		lblProductsList.setForeground(new Color(0, 102, 153));
		lblProductsList.setBounds(22, 39, 198, 21);
		pnlProducts.add(lblProductsList);
		
		JLabel lblSearchProduct = new JLabel("K\u00EBrko produkt:");
		lblSearchProduct.setFont(new Font("Courier New", Font.BOLD, 12));
		lblSearchProduct.setBounds(22, 78, 116, 14);
		pnlProducts.add(lblSearchProduct);
		
		txtSearchProduct = new JTextField();
		
		
		txtSearchProduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				try {
					conn = db_connection.connectDB();
					String sql = "select pid as 'ID', pname as 'EMRI', pstock as 'DEPO', pprice as 'ÇMIMI' from products where pname like '"+txtSearchProduct.getText()+"%'";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();

					tblProducts.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Connection Failed!\nError: "+e1.toString());
				}
				
			}
		});
		txtSearchProduct.setBounds(148, 74, 379, 21);
		pnlProducts.add(txtSearchProduct);
		txtSearchProduct.setColumns(10);
		
		JLabel lblCart = new JLabel("SHPORTA");
		lblCart.setForeground(new Color(0, 102, 153));
		lblCart.setFont(new Font("Courier New", Font.BOLD, 18));
		lblCart.setBounds(22, 366, 143, 21);
		pnlProducts.add(lblCart);
		
		JLabel lblTotal = new JLabel("Totali:");
		lblTotal.setForeground(new Color(0, 102, 153));
		lblTotal.setFont(new Font("Courier New", Font.BOLD, 18));
		lblTotal.setBounds(648, 645, 77, 21);
		pnlProducts.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(738, 646, 180, 21);
		pnlProducts.add(txtTotal);
		
		JButton btnBuy = new JButton("SHITE");
		btnBuy.setIcon(new ImageIcon(MainPage.class.getResource("/images/pay.png")));
		btnBuy.setForeground(new Color(0, 102, 153));
		btnBuy.setFont(new Font("Courier New", Font.BOLD, 13));
		btnBuy.setFocusPainted(false);
		btnBuy.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
		btnBuy.setBackground(Color.WHITE);
		btnBuy.setBounds(948, 636, 125, 40);
		pnlProducts.add(btnBuy);
		
		JScrollPane spProductsList = new JScrollPane();
		spProductsList.setBackground(Color.WHITE);
		spProductsList.setBounds(22, 104, 1100, 230);
		pnlProducts.add(spProductsList);
		
		tblProducts = new JTable();
		tblProducts.setGridColor(new Color(0, 102, 153));
		tblProducts.setRowHeight(tblProducts.getRowHeight() + 3);
		tblProducts.setSurrendersFocusOnKeystroke(true);
		tblProducts.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		tblProducts.setFont(new Font("Century Gothic", tblProducts.getFont().getStyle(), tblProducts.getFont().getSize()));
		
		spProductsList.setViewportView(tblProducts);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tblProducts, popupMenu);
		
				JMenuItem menuItem = new JMenuItem("Ndrysho");
				
				menuItem.setBackground(Color.WHITE);
				menuItem.setIcon(new ImageIcon(MainPage.class.getResource("/images/edit.jpg")));
				popupMenu.add(menuItem);
				
				JButton btnAddProduct = new JButton("Shto Produkt t\u00EB Ri");
				btnAddProduct.setBounds(651, 345, 198, 35);
				pnlProducts.add(btnAddProduct);
				
				btnAddProduct.setFocusPainted(false);
				btnAddProduct.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
				btnAddProduct.setBackground(Color.WHITE);
				btnAddProduct.setHorizontalAlignment(SwingConstants.LEFT);
				btnAddProduct.setIcon(new ImageIcon(MainPage.class.getResource("/images/add.png")));
				btnAddProduct.setFont(new Font("Courier New", Font.BOLD, 13));
				btnAddProduct.setForeground(new Color(0, 102, 153));
				JButton btnCart = new JButton("Shto n\u00EB shport\u00EB");
				btnCart.setBounds(875, 345, 198, 35);
				pnlProducts.add(btnCart);
				
				btnCart.setIcon(new ImageIcon(MainPage.class.getResource("/images/cart.png")));
				btnCart.setHorizontalAlignment(SwingConstants.LEFT);
				btnCart.setForeground(new Color(0, 102, 153));
				btnCart.setFont(new Font("Courier New", Font.BOLD, 13));
				btnCart.setFocusPainted(false);
				btnCart.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
				btnCart.setBackground(Color.WHITE);
				
	    
	    tblCart = new JTable(model2);
	    tblCart.setGridColor(new Color(0, 102, 153));
	    tblCart.setRowHeight(tblProducts.getRowHeight() + 3);
	    tblCart.setSurrendersFocusOnKeystroke(true);
	    tblCart.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
	    tblCart.setFont(new Font("Century Gothic", tblProducts.getFont().getStyle(), tblProducts.getFont().getSize()));
	    
		JScrollPane spCart = new JScrollPane(tblCart);
		spCart.setBounds(22, 395, 1100, 230);
		pnlProducts.add(spCart);
		
		
		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				try{
					DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
					pid = ((int) (model.getValueAt(tblProducts.getSelectedRow(), 0)));
					
						
					
					try {
						String sql = "select * from products where pid='" + pid + "'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						while (res.next()) {
							txtProdId.setText(res.getString("PID"));
							txtProdName.setText(res.getString("pname"));
							txtProdStock.setText(res.getString("pstock"));
							txtProdPrice.setText(res.getString("pprice"));
							
							
							
						}
						btnShtoProduktin.setEnabled(false);
						btnShtoProduktin.setVisible(false);
						
						btnRuajTeDhenat.setEnabled(true);
						btnRuajTeDhenat.setVisible(true);
						pnlProductInfo.setVisible(true);
						pst.close();

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error: " + e2);
					}
				}
				catch (Exception e3){
					JOptionPane.showMessageDialog(null, "Ju lutem klikoni një produkt!!!");
				}
				
				
				
				
			}
		});
		
			
			
			
			
			btnCart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					
					try{
						DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
						pid = ((int) (model.getValueAt(tblProducts.getSelectedRow(), 0)));
						int x;
						x= Integer.parseInt(JOptionPane.showInputDialog("Sasia e produkteve?"));
						
						
						
						ArrayList<String> productNames = new ArrayList();
						
						try {
							String sql = "select * from products where pid='" + pid + "'";
							pst = conn.prepareStatement(sql);
							res = pst.executeQuery();
							
							while (res.next()) {
								//JOptionPane.showMessageDialog(null, tableCounter);
								//JOptionPane.showMessageDialog(null, "Produkti po tfutet dhe row= "+model2.getRowCount());
								// kodi per futje ne cart!!!
								 
								if (x<=Integer.parseInt(res.getString("PSTOCK")))
								{
								
								 if(model2.getRowCount()==0)
									{
										String [] addToCart = {res.getString("PNAME"),res.getString("PPRICE"),String.valueOf(x),String.valueOf(Double.parseDouble(res.getString("PPRICE"))*x)};
										model2.addRow(addToCart);
										hm.put(0,res.getString("PNAME"));
									}
									else
									{
										for(int count=0;count<hm.size();count++)
										{
											if(res.getString("PNAME").equals(hm.get(count)))
											{
												if(Integer.parseInt(res.getString("PSTOCK"))>=Integer.parseInt(model2.getValueAt(count, 2).toString())+x)
												{
													 //JOptionPane.showMessageDialog(null, "Produkti ne hash osht "+hm.get(count));										
													 model2.setValueAt(Integer.parseInt(model2.getValueAt(count, 2).toString())+x, count, 2);
													 x=(int) model2.getValueAt(count, 2);
													 model2.setValueAt(Double.parseDouble(model2.getValueAt(count, 1).toString())*x, count, 3);
													 break;
												}
												else
												{
													JOptionPane.showMessageDialog(null, "Sasia e kërkuar nuk gjendet në depo!\n\tPROVO PRAPE","KUJDES!",
													        JOptionPane.WARNING_MESSAGE);
													break;
												}
											}
											else if ((count==hm.size()-1) && !(res.getString("PNAME")==hm.get(count)))
											{
												String [] addToCart = {res.getString("PNAME"),res.getString("PPRICE"),String.valueOf(x),String.valueOf(Double.parseDouble(res.getString("PPRICE"))*x)};
												model2.addRow(addToCart);
												hm.put(hm.size(),res.getString("PNAME"));
												break;
											}
										}
											
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Sasia e kërkuar nuk gjendet në depo!\n\tPROVO PRAPE","KUJDES!",
									        JOptionPane.WARNING_MESSAGE);
									
								}

								
							double s=0;
							for (int count = 0; count < model2.getRowCount(); count++){
									  s = s + Double.parseDouble(model2.getValueAt(count, 3).toString());
							}
							
							txtTotal.setText(String.valueOf(s));
								
							}
							
							pst.close();

						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Error: " + e2);
						}
					}catch (Exception e3){
						JOptionPane.showMessageDialog(null, "Ju lutem klikoni një produkt!!!");
					}
					
					
					
					
					
					
				}
			});
			
			
			btnAddProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					try{
					
						String sql = "SELECT * FROM products ORDER BY PID DESC LIMIT 1";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
							while (res.next()) 
							{
								
								txtProdId.setText(String.valueOf((Integer.parseInt(res.getString("PID"))+1)));
							}
						
						txtProdName.setText("");
						txtProdStock.setText("");
						txtProdPrice.setText("");
						
						btnShtoProduktin.setEnabled(true);
						btnShtoProduktin.setVisible(true);
						
						btnRuajTeDhenat.setEnabled(false);
						btnRuajTeDhenat.setVisible(false);
						pnlProductInfo.setVisible(true);
						
					}
					catch (Exception e4)
					{
						
					}
				}
			});
		
		JPanel pnlFurnizuesit = new JPanel();
		pnlFurnizuesit.setBackground(Color.WHITE);
		pnlFurnizuesit.setBounds(210, 0, 1162, 678);
		pnlFurnizuesit.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 102, 153)));
		frmMyshop.getContentPane().add(pnlFurnizuesit);
		pnlFurnizuesit.setLayout(null);
		
		JPanel pnlFInfo = new JPanel();
		pnlFInfo.setVisible(false);
		pnlFInfo.setForeground(Color.WHITE);
		pnlFInfo.setBackground(new Color(0, 102, 153));
		pnlFInfo.setBounds(243, 50, 460, 324);
		pnlFurnizuesit.add(pnlFInfo);
		pnlFInfo.setLayout(null);
		
		JLabel lblTDhnatE_1 = new JLabel("T\u00EB dh\u00EBnat e furnizuesit");
		lblTDhnatE_1.setFont(new Font("Courier New", Font.BOLD, 24));
		lblTDhnatE_1.setForeground(Color.WHITE);
		lblTDhnatE_1.setBounds(65, 36, 322, 28);
		pnlFInfo.add(lblTDhnatE_1);
		
		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setFont(new Font("Courier New", Font.BOLD, 13));
		lblId_1.setBounds(130, 82, 24, 15);
		pnlFInfo.add(lblId_1);
		
		JLabel lblEmri_1 = new JLabel("Emri:");
		lblEmri_1.setFont(new Font("Courier New", Font.BOLD, 13));
		lblEmri_1.setBounds(114, 118, 40, 15);
		pnlFInfo.add(lblEmri_1);
		
		JLabel lblFirma = new JLabel("Firma:");
		lblFirma.setFont(new Font("Courier New", Font.BOLD, 13));
		lblFirma.setBounds(106, 155, 48, 15);
		pnlFInfo.add(lblFirma);
		
		JLabel lblNrtelefonit = new JLabel("Nr.Telefonit:");
		lblNrtelefonit.setFont(new Font("Courier New", Font.BOLD, 13));
		lblNrtelefonit.setBounds(50, 191, 104, 15);
		pnlFInfo.add(lblNrtelefonit);
		
		txtFID = new JTextField();
		txtFID.setEditable(false);
		txtFID.setBounds(167, 75, 144, 28);
		pnlFInfo.add(txtFID);
		txtFID.setColumns(10);
		
		txtFEmri = new JTextField();
		txtFEmri.setBounds(167, 111, 144, 28);
		pnlFInfo.add(txtFEmri);
		txtFEmri.setColumns(10);
		
		txtFFirma = new JTextField();
		txtFFirma.setText("");
		txtFFirma.setBounds(167, 148, 144, 28);
		pnlFInfo.add(txtFFirma);
		txtFFirma.setColumns(10);
		
		txtFTel = new JTextField();
		txtFTel.setBounds(167, 184, 144, 28);
		pnlFInfo.add(txtFTel);
		txtFTel.setText("");
		txtFTel.setColumns(10);
		
		JButton btnFRuaj = new JButton("Ruaj t\u00EB Dh\u00EBnat");
		btnFRuaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					conn = db_connection.connectDB();
					String update = "Update  furnizuesit set fname='"+txtFEmri.getText()+" ', ffirma='"+txtFFirma.getText()
										+"  ', ftel='"+Integer.parseInt(txtFTel.getText())+"' where fid=' "+txtFID.getText()+"  ';";
					pst = conn.prepareStatement(update);
					pst.execute();
					pst.close();
					
					JOptionPane.showMessageDialog(null, "Të dhënat u ruajtën me sukses!");
					
					txtFID.setText("");
					txtFEmri.setText("");
					txtFFirma.setText("");
					txtFTel.setText("");
					updateFurnizuesit();
					pnlFInfo.setVisible(false);
					
					}
					catch (Exception eUpdate)
					{
						JOptionPane.showMessageDialog(null, "Të dhënat nuk mund të ruhen!\n Error: "+eUpdate.toString());
					}
				
			}
		});
		btnFRuaj.setFocusPainted(false);
		btnFRuaj.setBackground(new Color(0, 102, 153));
		btnFRuaj.setFont(new Font("Courier New", Font.BOLD, 12));
		btnFRuaj.setForeground(Color.WHITE);
		btnFRuaj.setBounds(160, 223, 151, 32);
		pnlFInfo.add(btnFRuaj);
		
		JButton btnFShto = new JButton("Shto Furnizuesin");
		btnFShto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					conn = db_connection.connectDB();
					String insert = "insert into furnizuesit (fname,ffirma,ftel) values('"+txtFEmri.getText()+" ','"+txtFFirma.getText()
										+"','"+Integer.parseInt(txtFTel.getText())+"');";
					pst = conn.prepareStatement(insert);
					pst.execute();
					pst.close();
					
					JOptionPane.showMessageDialog(null, "Furnizuesi u shtua me sukses!");
					
					txtFID.setText("");
					txtFEmri.setText("");
					txtFFirma.setText("");
					txtFTel.setText("");
					updateFurnizuesit();
					pnlFInfo.setVisible(false);
					
					}
					catch (Exception eUpdate)
					{
						JOptionPane.showMessageDialog(null, "Të dhënat nuk mund të ruhen!\n Error: "+eUpdate.toString());
					}
				
			}
		});
		
		btnFShto.setFocusPainted(false);
		btnFShto.setBackground(new Color(0, 102, 153));
		btnFShto.setFont(new Font("Courier New", Font.BOLD, 12));
		btnFShto.setForeground(Color.WHITE);
		btnFShto.setBounds(160, 257, 151, 32);
		pnlFInfo.add(btnFShto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFID.setText("");
				txtFEmri.setText("");
				txtFFirma.setText("");
				txtFTel.setText("");
				pnlFInfo.setVisible(false);
			}
		});
		lblNewLabel.setIcon(new ImageIcon(MainPage.class.getResource("/images/xicon.jpg")));
		lblNewLabel.setBounds(427, 0, 30, 38);
		pnlFInfo.add(lblNewLabel);
		
		JScrollPane spFurnizuesit = new JScrollPane();
		spFurnizuesit.setBounds(155, 189, 852, 330);
		pnlFurnizuesit.add(spFurnizuesit);
		
		
		
		
		
		tblFurnizuesit = new JTable();
		tblFurnizuesit.setGridColor(new Color(0, 102, 153));
		tblFurnizuesit.setRowHeight(tblFurnizuesit.getRowHeight() + 3);
		tblFurnizuesit.setSurrendersFocusOnKeystroke(true);
		tblFurnizuesit.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		tblFurnizuesit.setFont(new Font("Century Gothic", tblFurnizuesit.getFont().getStyle(), tblFurnizuesit.getFont().getSize()));
		
		spFurnizuesit.setViewportView(tblFurnizuesit);
		
		JButton btnShtoFurnizues = new JButton("Shto Furnizues t\u00EB Ri");
		btnShtoFurnizues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql = "SELECT * FROM furnizuesit ORDER BY FID DESC LIMIT 1";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
						while (res.next()) 
						{
							
							txtFID.setText(String.valueOf((Integer.parseInt(res.getString("FID"))+1)));
						}
				}
				catch(Exception a)
				{
					
				}
				txtFEmri.setText("");
				txtFFirma.setText("");
				txtFTel.setText("");
				
				btnFRuaj.setEnabled(false);
				btnFRuaj.setVisible(false);
				
				btnFShto.setEnabled(true);
				btnFShto.setVisible(true);
				
				pnlFInfo.setVisible(true);
				
			}
		});
		
		
		btnShtoFurnizues.setFont(new Font("Courier New", Font.BOLD, 13));
		btnShtoFurnizues.setForeground(new Color(0, 102, 153));
		btnShtoFurnizues.setBackground(Color.WHITE);
		btnShtoFurnizues.setBounds(780, 554, 218, 41);
		pnlFurnizuesit.add(btnShtoFurnizues);
		
		JLabel lblFurnizuesit = new JLabel("Furnizuesit:");
		lblFurnizuesit.setForeground(new Color(0, 102, 153));
		lblFurnizuesit.setFont(new Font("Courier New", Font.BOLD, 30));
		lblFurnizuesit.setBackground(Color.WHITE);
		lblFurnizuesit.setBounds(155, 63, 216, 34);
		pnlFurnizuesit.add(lblFurnizuesit);
		
		JLabel lblKrkoFurnizuesin = new JLabel("K\u00EBrko Furnizuesin sipas:");
		lblKrkoFurnizuesin.setFont(new Font("Courier New", Font.BOLD, 13));
		lblKrkoFurnizuesin.setBounds(155, 132, 192, 15);
		pnlFurnizuesit.add(lblKrkoFurnizuesin);
		
		txtKerkoFurnizues = new JTextField();
		
		txtKerkoFurnizues.setBounds(155, 155, 440, 23);
		pnlFurnizuesit.add(txtKerkoFurnizues);
		txtKerkoFurnizues.setColumns(10);
		
		JRadioButton rdbtnEmrit = new JRadioButton("Emrit");
		rdbtnEmrit.setSelected(true);
		buttonGroup.add(rdbtnEmrit);
		rdbtnEmrit.setBackground(Color.WHITE);
		rdbtnEmrit.setFont(new Font("Courier New", Font.BOLD, 13));
		rdbtnEmrit.setBounds(375, 128, 109, 23);
		pnlFurnizuesit.add(rdbtnEmrit);
		
		JRadioButton rdbtnFirmes = new JRadioButton("Firm\u00EBs");
		buttonGroup.add(rdbtnFirmes);
		rdbtnFirmes.setBackground(Color.WHITE);
		rdbtnFirmes.setFont(new Font("Courier New", Font.BOLD, 13));
		rdbtnFirmes.setBounds(486, 128, 109, 23);
		pnlFurnizuesit.add(rdbtnFirmes);
				
				
				JPopupMenu popupFurnizuesit = new JPopupMenu();
				addPopup(tblFurnizuesit, popupFurnizuesit);
				
				JMenuItem menuItem1 = new JMenuItem("Ndrysho");
				menuItem1.setBackground(Color.WHITE);
				menuItem1.setIcon(new ImageIcon(MainPage.class.getResource("/images/edit.jpg")));
				popupFurnizuesit.add(menuItem1);
		
		
		menuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				try{
					DefaultTableModel model1 = (DefaultTableModel) tblFurnizuesit.getModel();
					int fid = ((int) (model1.getValueAt(tblFurnizuesit.getSelectedRow(), 0)));
					
						
					
					try {
						String sql = "select * from furnizuesit where fid='" + fid + "'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						while (res.next()) {
							
							txtFID.setText(res.getString("FID"));
							txtFEmri.setText(res.getString("fname"));
							txtFFirma.setText(res.getString("ffirma"));
							txtFTel.setText(res.getString("ftel"));
							
							btnFRuaj.setEnabled(true);
							btnFRuaj.setVisible(true);
							
							btnFShto.setEnabled(false);
							btnFShto.setVisible(false);
							
							pnlFInfo.setVisible(true);
							
							
						}
						
						pst.close();

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Error: " + e2);
					}
				}
				catch (Exception e3){
					JOptionPane.showMessageDialog(null, "Ju lutem klikoni një furnizues!!!");
				}
				
				
				
				
			}
		});
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(MainPage.class.getResource("/images/MainLogo.png")));
		lblLogo.setBounds(58, 11, 100, 90);
		frmMyshop.getContentPane().add(lblLogo);
		
		JLabel lblMyshop = new JLabel("MyShop");
		lblMyshop.setForeground(Color.WHITE);
		lblMyshop.setFont(new Font("Courier New", Font.BOLD, 40));
		lblMyshop.setHorizontalAlignment(SwingConstants.LEFT);
		lblMyshop.setBounds(34, 99, 149, 50);
		frmMyshop.getContentPane().add(lblMyshop);
		
		JButton btnProducts = new JButton("Produktet");
		
		btnProducts.setFocusPainted(false);
		btnProducts.setHorizontalAlignment(SwingConstants.LEFT);
		btnProducts.setIcon(new ImageIcon(MainPage.class.getResource("/images/Webp.net-resizeimage (4).jpg")));
		btnProducts.setForeground(Color.WHITE);
		btnProducts.setFont(new Font("Courier New", Font.BOLD, 13));
		btnProducts.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnProducts.setBackground(new Color(0, 102, 153));
		btnProducts.setBounds(22, 267, 133, 40);
		frmMyshop.getContentPane().add(btnProducts);
		
		JButton btnSupliers = new JButton("Furnizuesit");
		
		btnSupliers.setFocusPainted(false);
		btnSupliers.setHorizontalAlignment(SwingConstants.LEFT);
		btnSupliers.setIcon(new ImageIcon(MainPage.class.getResource("/images/Webp.net-resizeimage (10).png")));
		btnSupliers.setForeground(Color.WHITE);
		btnSupliers.setFont(new Font("Courier New", Font.BOLD, 13));
		btnSupliers.setBorder(null);
		btnSupliers.setBackground(new Color(0, 102, 153));
		btnSupliers.setBounds(22, 420, 133, 41);
		frmMyshop.getContentPane().add(btnSupliers);
		
		JButton btnSales = new JButton("Shitjet");
		
		btnSales.setFocusPainted(false);
		btnSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnSales.setIcon(new ImageIcon(MainPage.class.getResource("/images/Webp.net-resizeimage (9).png")));
		btnSales.setForeground(Color.WHITE);
		btnSales.setFont(new Font("Courier New", Font.BOLD, 13));
		btnSales.setBorder(null);
		btnSales.setBackground(new Color(0, 102, 153));
		btnSales.setBounds(22, 317, 133, 40);
		frmMyshop.getContentPane().add(btnSales);
		
		
		
		

		
		
		
		updateTable();
		
		
		JButton btnOrders = new JButton("Porosit\u00EB");
		
		btnOrders.setIcon(new ImageIcon(MainPage.class.getResource("/images/orders.png")));
		btnOrders.setHorizontalAlignment(SwingConstants.LEFT);
		btnOrders.setForeground(Color.WHITE);
		btnOrders.setFont(new Font("Courier New", Font.BOLD, 13));
		btnOrders.setFocusPainted(false);
		btnOrders.setBorder(null);
		btnOrders.setBackground(new Color(0, 102, 153));
		btnOrders.setBounds(22, 368, 133, 41);
		frmMyshop.getContentPane().add(btnOrders);
		
		JPanel pnlShitjet = new JPanel();
		pnlShitjet.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 102, 153)));
		pnlShitjet.setBackground(Color.WHITE);
		pnlShitjet.setBounds(210, 0, 1162, 678);
		frmMyshop.getContentPane().add(pnlShitjet);
		
		JPanel pnlPorosite = new JPanel();
		pnlPorosite.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 102, 153)));
		pnlPorosite.setBackground(Color.WHITE);
		pnlPorosite.setBounds(210, 0, 1162, 678);
		frmMyshop.getContentPane().add(pnlPorosite);
		
		JPanel pnlStatusBar = new JPanel();
		pnlStatusBar.setBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(0, 102, 153)));
		pnlStatusBar.setBackground(Color.WHITE);
		pnlStatusBar.setBounds(0, 674, 1373, 31);
		frmMyshop.getContentPane().add(pnlStatusBar);
		pnlStatusBar.setLayout(null);
		
		JLabel lblProgramStarted = new JLabel("Program started ...");
		lblProgramStarted.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblProgramStarted.setForeground(new Color(0, 102, 153));
		lblProgramStarted.setBounds(10, 11, 133, 14);
		pnlStatusBar.add(lblProgramStarted);
		
		
		lblOra.setFont(new Font("Courier New", Font.BOLD, 12));
		lblOra.setForeground(new Color(0, 102, 153));
		lblOra.setBounds(1187, 11, 85, 14);
		pnlStatusBar.add(lblOra);
		
		
		lblData.setForeground(new Color(0, 102, 153));
		lblData.setFont(new Font("Courier New", Font.BOLD, 12));
		lblData.setBounds(1266, 11, 78, 14);
		pnlStatusBar.add(lblData);
		
		

		model2 = new DefaultTableModel();
	    model2.addColumn("Produkti");
	    model2.addColumn("Çmimi për copë");
	    model2.addColumn("Sasia e kërkuar");
	    model2.addColumn("Çmimi për Sasi");
		
	    txtKerkoFurnizues.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				if(rdbtnEmrit.isSelected())
				{
					try {
						conn = db_connection.connectDB();
						String sql1 = "select fid as 'ID', fname as 'EMRI',fFirma as 'FIRMA', fTel as 'Nr.TELEFONIT' from furnizuesit where fname like '"+txtKerkoFurnizues.getText()+"%';";
						pst = conn.prepareStatement(sql1);
						res = pst.executeQuery();

						tblFurnizuesit.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();

					} catch (Exception e5) {
						JOptionPane.showMessageDialog(null, "Connection Failed!\nError: "+e5.toString());
					}
					
					
				}
				else if(rdbtnFirmes.isSelected())
				{
					try {
						conn = db_connection.connectDB();
						String sql1 = "select fid as 'ID', fname as 'EMRI',fFirma as 'FIRMA', fTel as 'Nr.TELEFONIT' from furnizuesit where ffirma like '"+txtKerkoFurnizues.getText()+"%';";
						pst = conn.prepareStatement(sql1);
						res = pst.executeQuery();

						tblFurnizuesit.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();

					} catch (Exception e5) {
						JOptionPane.showMessageDialog(null, "Connection Failed!\nError: "+e5.toString());
					}
				}
			}
		});
		
		
		
		
		//btnProducts Events
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlShitjet.setVisible(false);
				pnlProducts.setVisible(false);
				pnlPorosite.setVisible(true);
				pnlFurnizuesit.setVisible(false);
			}
		});
			
				btnProducts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pnlShitjet.setVisible(false);
						pnlProducts.setVisible(true);
						pnlPorosite.setVisible(false);
						pnlFurnizuesit.setVisible(false);
					}
				});
				
				btnSupliers.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						
						
						updateFurnizuesit();
						
						
						
						pnlShitjet.setVisible(false);
						pnlProducts.setVisible(false);
						pnlPorosite.setVisible(false);
						pnlFurnizuesit.setVisible(true);
					}
				});
		
				btnSales.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pnlShitjet.setVisible(true);
						pnlProducts.setVisible(false);
						pnlPorosite.setVisible(false);
						pnlFurnizuesit.setVisible(false);
					}
				});
		
		
		
		
		// call function 'clock' to show date and time!
		
		clock();
		
		
	}
	
	
	
	
	public void updateTable() {

		try {
			conn = db_connection.connectDB();
			String sql = "select pid as 'ID', pname as 'EMRI',pstock as 'DEPO', pprice as 'ÇMIMI' from products";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();

			tblProducts.setModel(DbUtils.resultSetToTableModel(res));
			pst.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection Failed!\nError: "+e.toString());
		}

	}
	
	
	public void updateFurnizuesit(){
		try {
			conn = db_connection.connectDB();
			String sql1 = "select fid as 'ID', fname as 'EMRI',fFirma as 'FIRMA', fTel as 'Nr.TELEFONIT' from furnizuesit";
			pst = conn.prepareStatement(sql1);
			res = pst.executeQuery();

			tblFurnizuesit.setModel(DbUtils.resultSetToTableModel(res));
			pst.close();

		} catch (Exception e5) {
			JOptionPane.showMessageDialog(null, "Connection Failed!\nError: "+e5.toString());
		}
	}
	
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});

	}
}
