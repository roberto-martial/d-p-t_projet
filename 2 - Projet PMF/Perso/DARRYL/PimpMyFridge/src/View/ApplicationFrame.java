package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;

import Contoller.DataRetrieverController;

public class ApplicationFrame extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1673388500927286919L;
	
	private DataRetrieverController controller;
	private JLabel labelConsigneTemp;
	private JLabel labelTempInt;
	private JLabel labelTempExt;
	private JLabel labelHumitidy;
	private JLabel labelConsoWatt;
	private JLabel AlertMessage;
	public LineChart chart;
	private JButton Disconnect;
	private JButton requiredTemperatureDecrease;
	private JButton requiredTemperatureIncrease;
	private JButton Onbutton;
	private JButton Offbutton;
	private String value; 

	/**
	 * Launch the application.
	 */
	public ApplicationFrame() {
		this.initialize();
		setLocationRelativeTo(null);
		setVisible(true);
		this.setValue(JOptionPane.showInputDialog(null, "What is your required temperature?"));
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setResizable(false);
		setTitle("USB Fridge Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTop = new JPanel();
		panelTop.setBorder(null);
				
		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		chart = new LineChart("Courbe des températures", "Température extérieure et intérieure");
		ChartPanel component = new ChartPanel(chart.getJChart());
		component.setForeground(Color.WHITE);
		
		panelCenter.add(component, "name");
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panelRight = new JPanel();
		panelRight.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTop, GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelLeft, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelRight, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
						.addComponent(panelCenter, GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelTop, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelCenter, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelLeft, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelRight, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelCenter.setLayout(new CardLayout(0, 0));
		
		JLabel lblConsigne = new JLabel("Required Temperature");
		lblConsigne.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblConsigne.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.labelConsigneTemp = new JLabel("0\u00B0C");
		this.labelConsigneTemp.setForeground(Color.ORANGE);
		this.labelConsigneTemp.setFont(new Font("Tahoma", Font.PLAIN, 22));
		this.labelConsigneTemp.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.AlertMessage = new JLabel("No alert detected till now");
		this.AlertMessage.setFont(new Font("Segoe UI", Font.BOLD, 13));
		this.AlertMessage.setForeground(new Color(241, 61, 7));
		
		JLabel lblNewLabel_2 = new JLabel("R\u00E9alis\u00E9 par Darryl, Iris, Yves et Aoudou");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		labelConsoWatt = new JLabel("Groupe II");
		labelConsoWatt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel AlertImage = new JLabel("\r\n");
		AlertImage.setIcon(new ImageIcon("C:\\Users\\NOUMEN DARRYL\\Documents\\UCAC-ICAM\\X2\\PROSITS\\PROJET PMF\\Images\\alert.png"));
		
		this.requiredTemperatureDecrease = new JButton("-");
		this.requiredTemperatureDecrease.setFont(new Font("Segoe UI", Font.BOLD, 13));
		
		this.requiredTemperatureIncrease = new JButton("+");
		this.requiredTemperatureIncrease.setFont(new Font("Segoe UI", Font.BOLD, 13));
		
		GroupLayout gl_panelRight = new GroupLayout(panelRight);
		gl_panelRight.setHorizontalGroup(
			gl_panelRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRight.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRight.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConsigne, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelRight.createSequentialGroup()
							.addComponent(labelConsigneTemp, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelRight.createParallelGroup(Alignment.LEADING, false)
								.addComponent(this.requiredTemperatureDecrease, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.requiredTemperatureIncrease, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(6)
					.addComponent(AlertImage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRight.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(labelConsoWatt, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(AlertMessage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelRight.setVerticalGroup(
			gl_panelRight.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRight.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRight.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConsigne)
						.addComponent(AlertMessage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(AlertImage))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRight.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRight.createSequentialGroup()
							.addGroup(gl_panelRight.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelRight.createSequentialGroup()
									.addGap(8)
									.addComponent(labelConsoWatt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addComponent(requiredTemperatureDecrease))
							.addGroup(gl_panelRight.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelRight.createSequentialGroup()
									.addGap(7)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelRight.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(requiredTemperatureIncrease))))
						.addComponent(labelConsigneTemp, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelRight.setLayout(gl_panelRight);
		
		JLabel lblTempratureInterne = new JLabel("Internal Temperature");
		lblTempratureInterne.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTempratureInterne.setBounds(10, 11, 139, 14);
		
		labelTempInt = new JLabel("0\u00B0C");
		labelTempInt.setBounds(10, 36, 139, 55);
		labelTempInt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelTempInt.setForeground(new Color(0, 174, 189));
		labelTempInt.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTempratureExterne = new JLabel("External Temperature");
		lblTempratureExterne.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTempratureExterne.setBounds(170, 11, 139, 14);
		
		labelTempExt = new JLabel("0\u00B0C");
		labelTempExt.setBounds(170, 36, 139, 55);
		labelTempExt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelTempExt.setForeground(new Color(241, 61, 7));
		labelTempExt.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTauxDhumidit = new JLabel("Humidity Rate");
		lblTauxDhumidit.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTauxDhumidit.setBounds(334, 11, 94, 14);
		
		labelHumitidy = new JLabel("0 %");
		labelHumitidy.setBounds(334, 36, 94, 55);
		labelHumitidy.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelHumitidy.setHorizontalAlignment(SwingConstants.CENTER);
		panelLeft.setLayout(null);
		panelLeft.add(labelTempInt);
		panelLeft.add(lblTempratureInterne);
		panelLeft.add(labelTempExt);
		panelLeft.add(lblTempratureExterne);
		panelLeft.add(labelHumitidy);
		panelLeft.add(lblTauxDhumidit);
		
		JLabel Title = new JLabel("Pimp My Fridge ");
		Title.setFont(new Font("Arial Narrow", Font.BOLD, 30));
		Title.setForeground(Color.BLACK);
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.Onbutton = new JButton("ON");
		this.Onbutton.setForeground(Color.GREEN);
		this.Onbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		this.Offbutton = new JButton("OFF");
		this.Offbutton.setForeground(Color.RED);
		this.Offbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		this.Disconnect = new JButton("DISCONNECT");
		this.Disconnect.setForeground(Color.DARK_GRAY);
		this.Disconnect.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_panelTop = new GroupLayout(panelTop);
		gl_panelTop.setHorizontalGroup(
			gl_panelTop.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addComponent(Title)
					.addPreferredGap(ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
					.addComponent(Onbutton)
					.addGap(18)
					.addComponent(Offbutton)
					.addGap(18)
					.addComponent(Disconnect))
		);
		gl_panelTop.setVerticalGroup(
			gl_panelTop.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelTop.createSequentialGroup()
					.addGroup(gl_panelTop.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelTop.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelTop.createParallelGroup(Alignment.BASELINE)
								.addComponent(Disconnect, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(Offbutton)
								.addComponent(Onbutton, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
						.addComponent(Title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		panelTop.setLayout(gl_panelTop);
		getContentPane().setLayout(groupLayout);
	
	}

	/**
	 * @return the labelConsigneTemp
	 */
	public JLabel getLabelConsigneTemp() {
		return labelConsigneTemp;
	}

	/**
	 * @return the labelTempInt
	 */
	public JLabel getLabelTempInt() {
		return labelTempInt;
	}

	/**
	 * @return the labelTempExt
	 */
	public JLabel getLabelTempExt() {
		return labelTempExt;
	}

	/**
	 * @return the labelHumitidy
	 */
	public JLabel getLabelHumitidy() {
		return labelHumitidy;
	}

	/**
	 * @return the labelConsoWatt
	 */
	public JLabel getLabelConsoWatt() {
		return labelConsoWatt;
	}

	/**
	 * @return the disconnect
	 */
	public JButton getDisconnect() {
		return Disconnect;
	}

	/**
	 * @return the RequiredTemperatureDecrease
	 */
	public JButton getRequiredTemperatureDecrease() {
		return requiredTemperatureDecrease;
	}

	/**
	 * @return the RequiredTemperatureIncrease
	 */
	public JButton getRequiredTemperatureIncrease() {
		return requiredTemperatureIncrease;
	}

	/**
	 * @return the onbutton
	 */
	public JButton getOnbutton() {
		return Onbutton;
	}

	/**
	 * @return the offbutton
	 */
	public JButton getOffbutton() {
		return Offbutton;
	}

	/**
	 * @param labelConsigneTemp the labelConsigneTemp to set
	 */
	public void setLabelConsigneTemp(JLabel labelConsigneTemp) {
		this.labelConsigneTemp = labelConsigneTemp;
	}

	/**
	 * @param labelTempInt the labelTempInt to set
	 */
	public void setLabelTempInt(JLabel labelTempInt) {
		this.labelTempInt = labelTempInt;
	}

	/**
	 * @param labelTempExt the labelTempExt to set
	 */
	public void setLabelTempExt(JLabel labelTempExt) {
		this.labelTempExt = labelTempExt;
	}

	/**
	 * @param labelHumitidy the labelHumitidy to set
	 */
	public void setLabelHumitidy(JLabel labelHumitidy) {
		this.labelHumitidy = labelHumitidy;
	}

	/**
	 * @param labelConsoWatt the labelConsoWatt to set
	 */
	public void setLabelConsoWatt(JLabel labelConsoWatt) {
		this.labelConsoWatt = labelConsoWatt;
	}

	/**
	 * @param disconnect the disconnect to set
	 */
	public void setDisconnect(JButton disconnect) {
		Disconnect = disconnect;
	}

	/**
	 * @param RequiredTemperatureDecrease the RequiredTemperatureDecrease to set
	 */
	public void setRequiredTemperatureDecrease(JButton RequiredTemperatureDecrease) {
		requiredTemperatureDecrease = RequiredTemperatureDecrease;
	}

	/**
	 * @param RequiredTemperatureIncrease the RequiredTemperatureIncrease to set
	 */
	public void setRequiredTemperatureIncrease(JButton RequiredTemperatureIncrease) {
		requiredTemperatureIncrease = RequiredTemperatureIncrease;
	}

	/**
	 * @param onbutton the onbutton to set
	 */
	public void setOnbutton(JButton onbutton) {
		Onbutton = onbutton;
	}

	/**
	 * @param offbutton the offbutton to set
	 */
	public void setOffbutton(JButton offbutton) {
		Offbutton = offbutton;
	}

	/**
	 * @return the alertMessage
	 */
	public JLabel getAlertMessage() {
		return AlertMessage;
	}

	/**
	 * @param alertMessage the alertMessage to set
	 */
	public void setAlertMessage(JLabel alertMessage) {
		AlertMessage = alertMessage;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
