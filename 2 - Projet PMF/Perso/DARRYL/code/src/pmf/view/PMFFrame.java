package pmf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class PMFFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public PMFFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("0 \u00B0C");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 50));
		lblNewLabel.setBounds(170, 88, 152, 83);
		contentPane.add(lblNewLabel);
	}
	
	public JLabel getLabel() {
		return this.lblNewLabel;
	}

}
