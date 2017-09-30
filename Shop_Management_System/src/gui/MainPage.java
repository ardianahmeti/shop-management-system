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
import java.util.Calendar;
import java.util.GregorianCalendar;
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



public class MainPage {

	private JFrame frmMyshop;
	
	
	private double s_width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private double s_height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int pid;
	
	
	
	private DefaultTableModel model;
	
	private JTextField txtSearchProduct;
	
	JLabel lblData = new JLabel("");
	JLabel lblOra = new JLabel("");
	private JTextField txtTotal;
	private JTable tblProducts;
	private JTable tblCart;
	
	
	
	
	//DB Connection!
	Connection conn = null;
	ResultSet res = null;
	PreparedStatement pst = null;
	private JTextField txtProdId;
	private JTextField txtProdName;
	private JTextField txtProdStock;
	private JTextField txtProdPrice;
	
	
	
	
	
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
		
		
		frmMyshop = new JFrame();
		frmMyshop.setTitle("MyShop");
		frmMyshop.setIconImage(Toolkit.getDefaultToolkit().getImage(MainPage.class.getResource("/images/LogoPng.png")));
		frmMyshop.getContentPane().setBackground(Color.WHITE);
		frmMyshop.setBounds(0, 0, (int)s_width, (int)s_height);
		frmMyshop.setExtendedState(frmMyshop.MAXIMIZED_BOTH);
		frmMyshop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMyshop.getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(MainPage.class.getResource("/images/MainLogo.png")));
		lblLogo.setBounds((int)(s_width/2 - 160), 20, 100, 90);
		frmMyshop.getContentPane().add(lblLogo);
		
		JLabel lblMyshop = new JLabel("MyShop");
		lblMyshop.setForeground(new Color(0, 102, 153));
		lblMyshop.setFont(new Font("Courier New", Font.BOLD, 40));
		lblMyshop.setHorizontalAlignment(SwingConstants.LEFT);
		lblMyshop.setBounds(648, 39, 171, 50);
		frmMyshop.getContentPane().add(lblMyshop);
		
		JButton btnProducts = new JButton("Produktet");
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProducts.setFocusPainted(false);
		btnProducts.setHorizontalAlignment(SwingConstants.LEFT);
		btnProducts.setIcon(new ImageIcon(MainPage.class.getResource("/images/produktetPng.jpg")));
		btnProducts.setForeground(new Color(0, 102, 153));
		btnProducts.setFont(new Font("Courier New", Font.BOLD, 13));
		btnProducts.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnProducts.setBackground(Color.WHITE);
		btnProducts.setBounds(22, 267, 133, 40);
		frmMyshop.getContentPane().add(btnProducts);
		
		JButton btnSupliers = new JButton("Furnizuesit");
		btnSupliers.setFocusPainted(false);
		btnSupliers.setHorizontalAlignment(SwingConstants.LEFT);
		btnSupliers.setIcon(new ImageIcon(MainPage.class.getResource("/images/supliersLogo.png")));
		btnSupliers.setForeground(new Color(0, 102, 153));
		btnSupliers.setFont(new Font("Courier New", Font.BOLD, 13));
		btnSupliers.setBorder(null);
		btnSupliers.setBackground(Color.WHITE);
		btnSupliers.setBounds(22, 423, 133, 41);
		frmMyshop.getContentPane().add(btnSupliers);
		
		JButton btnSales = new JButton("Shitjet");
		btnSales.setFocusPainted(false);
		btnSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnSales.setIcon(new ImageIcon(MainPage.class.getResource("/images/salesLogo.png")));
		btnSales.setForeground(new Color(0, 102, 153));
		btnSales.setFont(new Font("Courier New", Font.BOLD, 13));
		btnSales.setBorder(null);
		btnSales.setBackground(Color.WHITE);
		btnSales.setBounds(22, 317, 133, 40);
		frmMyshop.getContentPane().add(btnSales);
		
		JPanel pnlProducts = new JPanel();
		pnlProducts.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 102, 153)));
		pnlProducts.setBackground(Color.WHITE);
		pnlProducts.setBounds(174, 121, 1159, 547);
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
		txtProdStock.setText("");
		txtProdStock.setBounds(395, 133, 110, 25);
		pnlProductInfo.add(txtProdStock);
		txtProdStock.setColumns(10);
		
		txtProdPrice = new JTextField();
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
										+"','"+Double.parseDouble(txtProdPrice.getText())+"';";
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
		lblProductsList.setBounds(22, 11, 198, 21);
		pnlProducts.add(lblProductsList);
		
		JLabel lblSearchProduct = new JLabel("K\u00EBrko produkt:");
		lblSearchProduct.setFont(new Font("Courier New", Font.BOLD, 12));
		lblSearchProduct.setBounds(22, 57, 116, 14);
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
		txtSearchProduct.setBounds(148, 53, 379, 21);
		pnlProducts.add(txtSearchProduct);
		txtSearchProduct.setColumns(10);
		
		
		
		
		JScrollPane spCart = new JScrollPane(tblCart);
		spCart.setBounds(709, 68, 450, 378);
		pnlProducts.add(spCart);
		
		JButton btnAddProduct = new JButton("Shto Produkt");
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
		btnAddProduct.setFocusPainted(false);
		btnAddProduct.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
		btnAddProduct.setBackground(Color.WHITE);
		btnAddProduct.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddProduct.setIcon(new ImageIcon(MainPage.class.getResource("/images/shtoProdukt.png")));
		btnAddProduct.setFont(new Font("Courier New", Font.BOLD, 13));
		btnAddProduct.setForeground(new Color(0, 102, 153));
		btnAddProduct.setBounds(127, 485, 180, 51);
		pnlProducts.add(btnAddProduct);
		
		JButton btnCart = new JButton("Shto n\u00EB shport\u00EB");
		btnCart.setIcon(new ImageIcon(MainPage.class.getResource("/images/shporta.png")));
		btnCart.setHorizontalAlignment(SwingConstants.LEFT);
		btnCart.setForeground(new Color(0, 102, 153));
		btnCart.setFont(new Font("Courier New", Font.BOLD, 13));
		btnCart.setFocusPainted(false);
		btnCart.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
		btnCart.setBackground(Color.WHITE);
		btnCart.setBounds(378, 485, 180, 51);
		pnlProducts.add(btnCart);
		
		JLabel lblCart = new JLabel("SHPORTA");
		lblCart.setForeground(new Color(0, 102, 153));
		lblCart.setFont(new Font("Courier New", Font.BOLD, 18));
		lblCart.setBounds(709, 11, 143, 21);
		pnlProducts.add(lblCart);
		
		JLabel lblTotal = new JLabel("Totali:");
		lblTotal.setForeground(new Color(0, 102, 153));
		lblTotal.setFont(new Font("Courier New", Font.BOLD, 18));
		lblTotal.setBounds(882, 457, 77, 21);
		pnlProducts.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(979, 458, 180, 21);
		pnlProducts.add(txtTotal);
		
		JButton btnBuy = new JButton("BLEJE");
		btnBuy.setIcon(new ImageIcon(MainPage.class.getResource("/images/pay.png")));
		btnBuy.setForeground(new Color(0, 102, 153));
		btnBuy.setFont(new Font("Courier New", Font.BOLD, 13));
		btnBuy.setFocusPainted(false);
		btnBuy.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
		btnBuy.setBackground(Color.WHITE);
		btnBuy.setBounds(1024, 490, 125, 40);
		pnlProducts.add(btnBuy);
		
		JScrollPane spProductsList = new JScrollPane();
		spProductsList.setBackground(Color.WHITE);
		spProductsList.setBounds(22, 85, 650, 389);
		pnlProducts.add(spProductsList);
		
		tblProducts = new JTable();
		tblProducts.setFont(new Font("Courier New", Font.BOLD, 13));
		spProductsList.setViewportView(tblProducts);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tblProducts, popupMenu);

		JMenuItem menuItem = new JMenuItem("Ndrysho");
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
		menuItem.setBackground(Color.WHITE);
		menuItem.setIcon(new ImageIcon(MainPage.class.getResource("/images/edit.jpg")));
		popupMenu.add(menuItem);
		
		
		
		updateTable();
		
		
		JButton btnOrders = new JButton("Porosit\u00EB");
		btnOrders.setIcon(new ImageIcon(MainPage.class.getResource("/images/orders.png")));
		btnOrders.setHorizontalAlignment(SwingConstants.LEFT);
		btnOrders.setForeground(new Color(0, 102, 153));
		btnOrders.setFont(new Font("Courier New", Font.BOLD, 13));
		btnOrders.setFocusPainted(false);
		btnOrders.setBorder(null);
		btnOrders.setBackground(Color.WHITE);
		btnOrders.setBounds(22, 368, 133, 41);
		frmMyshop.getContentPane().add(btnOrders);
		
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
