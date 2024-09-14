package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Contoller.ArduinoConnectionController;
import Contoller.DataRetrieverController;

public class ArduinoConnection extends JFrame {

	/**
	 * Declaration of some variables
	 */
	private static final long serialVersionUID = 1L;
	private ArduinoConnectionController controller;
	@SuppressWarnings("unused")
	private DataRetrieverController datacontroller;
	private JPanel contentPane;
	private JButton Connection;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JTextArea textArea;
	
	public ArduinoConnection() {
		this.initialize();
		setVisible(true);
		setLocationRelativeTo(null);
		this.setController(new ArduinoConnectionController(this));
		this.Connection.addActionListener(controller);
	}
	
	/**
	 * Launch the Application.
	 */
	private void initialize() {
		
		setResizable(false);
		setTitle("Arduino Connection");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 508, 418);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel InternalTitle = new JLabel("SERIAL PORT CONNECTION");
		InternalTitle.setFont(new Font("Tahoma", Font.BOLD, 33));
		InternalTitle.setBounds(15, 0, 459, 83);
		contentPane.add(InternalTitle);
		
		JLabel HintText = new JLabel("Choose the suitable serial port :");
		HintText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		HintText.setBounds(109, 94, 285, 26);
		contentPane.add(HintText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 223, 472, 127);
		contentPane.add(scrollPane);
		
		this.textArea = new JTextArea();
		this.textArea.setFont(new Font("Segoe UI", Font.BOLD, 13));
		this.textArea.setRows(25);
		scrollPane.setViewportView(this.textArea);
			
		JLabel Logging = new JLabel("Log :");
		Logging.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Logging.setBounds(15, 192, 48, 26);
		contentPane.add(Logging);
		
		this.comboBox = new JComboBox<String>();
		this.comboBox.setForeground(Color.DARK_GRAY);
		this.comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.comboBox.setBounds(144, 146, 82, 31);
		contentPane.add(this.comboBox);
		
		this.Connection = new JButton("CONNECT");
		this.Connection.setForeground(Color.DARK_GRAY);
		this.Connection.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.Connection.setBounds(236, 146, 122, 31);
		contentPane.add(this.Connection);
		
	}

	/**
	 * @return the connection
	 */
	public JButton getConnection() {
		return Connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(JButton connection) {
		Connection = connection;
	}

	/**
	 * @return the comboBox
	 */
	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox() {
		return comboBox;
	}

	/**
	 * @param comboBox the comboBox to set
	 */
	@SuppressWarnings("rawtypes")
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	/**
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * @param textArea the textArea to set
	 */
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * @return the controller
	 */
	public ArduinoConnectionController getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(ArduinoConnectionController controller) {
		this.controller = controller;
	}

}
