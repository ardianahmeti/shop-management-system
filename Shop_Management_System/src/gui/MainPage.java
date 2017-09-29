package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
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



public class MainPage {

	private JFrame frmMyshop;
	
	
	private double s_width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private double s_height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	private JTextField txtSearchProduct;
	
	JLabel lblData = new JLabel("");
	JLabel lblOra = new JLabel("");
	private JTextField txtTotal;
	private JTable tblProducts;
	
	
	
	
	//DB Connection!
	Connection conn = null;
	ResultSet res = null;
	PreparedStatement pst = null;
	
	
	
	
	
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
		lblLogo.setBounds((int)(s_width/2 - 160), 27, 100, 90);
		frmMyshop.getContentPane().add(lblLogo);
		
		JLabel lblMyshop = new JLabel("MyShop");
		lblMyshop.setForeground(new Color(0, 102, 153));
		lblMyshop.setFont(new Font("Courier New", Font.BOLD, 40));
		lblMyshop.setHorizontalAlignment(SwingConstants.LEFT);
		lblMyshop.setBounds(648, 46, 171, 50);
		frmMyshop.getContentPane().add(lblMyshop);
		
		JButton btnProducts = new JButton("Products");
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
		btnProducts.setBounds(22, 267, 125, 40);
		frmMyshop.getContentPane().add(btnProducts);
		
		JButton btnSupliers = new JButton("Suppliers");
		btnSupliers.setFocusPainted(false);
		btnSupliers.setHorizontalAlignment(SwingConstants.LEFT);
		btnSupliers.setIcon(new ImageIcon(MainPage.class.getResource("/images/supliersLogo.png")));
		btnSupliers.setForeground(new Color(0, 102, 153));
		btnSupliers.setFont(new Font("Courier New", Font.BOLD, 13));
		btnSupliers.setBorder(null);
		btnSupliers.setBackground(Color.WHITE);
		btnSupliers.setBounds(22, 423, 125, 41);
		frmMyshop.getContentPane().add(btnSupliers);
		
		JButton btnSales = new JButton("Sales");
		btnSales.setFocusPainted(false);
		btnSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnSales.setIcon(new ImageIcon(MainPage.class.getResource("/images/salesLogo.png")));
		btnSales.setForeground(new Color(0, 102, 153));
		btnSales.setFont(new Font("Courier New", Font.BOLD, 13));
		btnSales.setBorder(null);
		btnSales.setBackground(Color.WHITE);
		btnSales.setBounds(22, 317, 125, 40);
		frmMyshop.getContentPane().add(btnSales);
		
		JPanel pnlProducts = new JPanel();
		pnlProducts.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 102, 153)));
		pnlProducts.setBackground(Color.WHITE);
		pnlProducts.setBounds(174, 169, 1159, 499);
		frmMyshop.getContentPane().add(pnlProducts);
		pnlProducts.setLayout(null);
		
		JLabel lblProductsList = new JLabel("PRODUCTS LIST");
		lblProductsList.setFont(new Font("Courier New", Font.BOLD, 18));
		lblProductsList.setForeground(new Color(0, 102, 153));
		lblProductsList.setBounds(22, 11, 143, 21);
		pnlProducts.add(lblProductsList);
		
		JLabel lblSearchProduct = new JLabel("Search product:");
		lblSearchProduct.setFont(new Font("Courier New", Font.BOLD, 12));
		lblSearchProduct.setBounds(22, 43, 116, 14);
		pnlProducts.add(lblSearchProduct);
		
		txtSearchProduct = new JTextField();
		txtSearchProduct.setBounds(148, 39, 379, 21);
		pnlProducts.add(txtSearchProduct);
		txtSearchProduct.setColumns(10);
		
		JButton btnAddProduct = new JButton("Add New Product");
		btnAddProduct.setFocusPainted(false);
		btnAddProduct.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
		btnAddProduct.setBackground(Color.WHITE);
		btnAddProduct.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddProduct.setIcon(new ImageIcon(MainPage.class.getResource("/images/shtoProdukt.png")));
		btnAddProduct.setFont(new Font("Courier New", Font.BOLD, 13));
		btnAddProduct.setForeground(new Color(0, 102, 153));
		btnAddProduct.setBounds(64, 430, 180, 51);
		pnlProducts.add(btnAddProduct);
		
		JButton btnCart = new JButton(" Add to Cart");
		btnCart.setEnabled(false);
		btnCart.setIcon(new ImageIcon(MainPage.class.getResource("/images/shporta.png")));
		btnCart.setHorizontalAlignment(SwingConstants.LEFT);
		btnCart.setForeground(new Color(0, 102, 153));
		btnCart.setFont(new Font("Courier New", Font.BOLD, 13));
		btnCart.setFocusPainted(false);
		btnCart.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
		btnCart.setBackground(Color.WHITE);
		btnCart.setBounds(315, 430, 180, 51);
		pnlProducts.add(btnCart);
		
		JLabel lblCart = new JLabel("CART");
		lblCart.setForeground(new Color(0, 102, 153));
		lblCart.setFont(new Font("Courier New", Font.BOLD, 18));
		lblCart.setBounds(709, 11, 143, 21);
		pnlProducts.add(lblCart);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(new Color(0, 102, 153));
		lblTotal.setFont(new Font("Courier New", Font.BOLD, 18));
		lblTotal.setBounds(886, 390, 72, 21);
		pnlProducts.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(968, 391, 180, 21);
		pnlProducts.add(txtTotal);
		
		JButton btnBuy = new JButton("BUY  ");
		btnBuy.setIcon(new ImageIcon(MainPage.class.getResource("/images/pay.png")));
		btnBuy.setForeground(new Color(0, 102, 153));
		btnBuy.setFont(new Font("Courier New", Font.BOLD, 13));
		btnBuy.setFocusPainted(false);
		btnBuy.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153), new Color(0, 102, 153)));
		btnBuy.setBackground(Color.WHITE);
		btnBuy.setBounds(1023, 430, 125, 40);
		pnlProducts.add(btnBuy);
		
		JScrollPane spProductsList = new JScrollPane();
		spProductsList.setBounds(22, 68, 650, 350);
		pnlProducts.add(spProductsList);
		
		tblProducts = new JTable();
		spProductsList.setViewportView(tblProducts);
		
		updateTable();
		
		
		JButton btnOrders = new JButton("Orders");
		btnOrders.setIcon(new ImageIcon(MainPage.class.getResource("/images/orders.png")));
		btnOrders.setHorizontalAlignment(SwingConstants.LEFT);
		btnOrders.setForeground(new Color(0, 102, 153));
		btnOrders.setFont(new Font("Courier New", Font.BOLD, 13));
		btnOrders.setFocusPainted(false);
		btnOrders.setBorder(null);
		btnOrders.setBackground(Color.WHITE);
		btnOrders.setBounds(22, 368, 125, 41);
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
		
		// cal function 'clock' to show date and time!
		
		clock();
	}
	
	public void updateTable() {

		try {
			conn = db_connection.connectDB();
			String sql = "select pid as 'ID', pname as 'NAME',pstock as 'STOCK', pprice as 'PRICE' from products";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();

			tblProducts.setModel(DbUtils.resultSetToTableModel(res));
			pst.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection Failed!\nError: "+e.toString());
		}

	}
}
