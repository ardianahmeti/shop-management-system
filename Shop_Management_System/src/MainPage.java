import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;

public class MainPage {

	private JFrame frmMyshop;
	
	
	private double s_width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private double s_height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();


	/**
	 * Launch the application.
	 */
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
		btnProducts.setFocusPainted(false);
		btnProducts.setHorizontalAlignment(SwingConstants.LEFT);
		btnProducts.setIcon(new ImageIcon(MainPage.class.getResource("/images/produktetPng.jpg")));
		btnProducts.setForeground(new Color(0, 102, 153));
		btnProducts.setFont(new Font("Courier New", Font.BOLD, 13));
		btnProducts.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnProducts.setBackground(Color.WHITE);
		btnProducts.setBounds(22, 267, 155, 40);
		frmMyshop.getContentPane().add(btnProducts);
		
		JButton btnSupliers = new JButton("Suppliers");
		btnSupliers.setFocusPainted(false);
		btnSupliers.setHorizontalAlignment(SwingConstants.LEFT);
		btnSupliers.setIcon(new ImageIcon(MainPage.class.getResource("/images/supliersLogo.png")));
		btnSupliers.setForeground(new Color(0, 102, 153));
		btnSupliers.setFont(new Font("Courier New", Font.BOLD, 13));
		btnSupliers.setBorder(null);
		btnSupliers.setBackground(Color.WHITE);
		btnSupliers.setBounds(22, 423, 155, 41);
		frmMyshop.getContentPane().add(btnSupliers);
		
		JButton btnSales = new JButton("Sales");
		btnSales.setFocusPainted(false);
		btnSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnSales.setIcon(new ImageIcon(MainPage.class.getResource("/images/salesLogo.png")));
		btnSales.setForeground(new Color(0, 102, 153));
		btnSales.setFont(new Font("Courier New", Font.BOLD, 13));
		btnSales.setBorder(null);
		btnSales.setBackground(Color.WHITE);
		btnSales.setBounds(22, 317, 155, 40);
		frmMyshop.getContentPane().add(btnSales);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(204, 169, 1129, 525);
		frmMyshop.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblProductsList = new JLabel("Products List");
		lblProductsList.setFont(new Font("Courier New", Font.BOLD, 11));
		lblProductsList.setForeground(new Color(0, 102, 153));
		lblProductsList.setBounds(83, 37, 91, 14);
		panel.add(lblProductsList);
		
		JButton btnOrders = new JButton("Orders");
		btnOrders.setIcon(new ImageIcon(MainPage.class.getResource("/images/orders.png")));
		btnOrders.setHorizontalAlignment(SwingConstants.LEFT);
		btnOrders.setForeground(new Color(0, 102, 153));
		btnOrders.setFont(new Font("Courier New", Font.BOLD, 13));
		btnOrders.setFocusPainted(false);
		btnOrders.setBorder(null);
		btnOrders.setBackground(Color.WHITE);
		btnOrders.setBounds(22, 368, 155, 41);
		frmMyshop.getContentPane().add(btnOrders);
		//comment added to test cloning git
	}
}
