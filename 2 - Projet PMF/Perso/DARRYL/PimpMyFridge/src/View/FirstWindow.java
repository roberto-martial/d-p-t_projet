package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.CompoundBorder;

@SuppressWarnings("serial")
public class FirstWindow extends JFrame {

	private JPanel contentPane;
	private static JLabel LoadingText;
	private static JLabel LoadingValue;
	private static JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public FirstWindow() {
		this.initialize();
		setLocationRelativeTo(null);
		setVisible(true);
		this.ProgressBarEvolution();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unused")
	private void initialize() {
	
		setResizable(false);
		setForeground(Color.BLACK);
		setBackground(Color.LIGHT_GRAY);
		setTitle("My PMF Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		LoadingValue = new JLabel("0%");
		LoadingValue.setForeground(Color.WHITE);
		LoadingValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LoadingValue.setBounds(835, 413, 49, 26); 
		contentPane.add(LoadingValue);
		
	    progressBar = new JProgressBar();
	    progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setBounds(-13, 441, 921, 30);
		contentPane.add(progressBar);
		LoadingText = new JLabel("Loading...");
		LoadingText.setForeground(Color.WHITE);
		LoadingText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LoadingText.setBounds(10, 411, 413, 30);
		contentPane.add(LoadingText);
		
		JLabel Title = new JLabel("PIMP MY FRIDGE");
		Title.setForeground(Color.DARK_GRAY);
		Title.setFont(new Font("Tahoma", Font.BOLD, 60));
		Title.setBackground(Color.WHITE);
		Title.setBounds(196, 22, 526, 53);
		contentPane.add(Title);
		
		JLabel MainImage = new JLabel("Image");
		MainImage.setIcon(new ImageIcon("C:\\Users\\NOUMEN DARRYL\\Documents\\UCAC-ICAM\\X2\\PROSITS\\PROJET PMF\\Images\\MainImage.png"));
		MainImage.setBounds(326, 86, 239, 328);
		contentPane.add(MainImage);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\NOUMEN DARRYL\\Documents\\UCAC-ICAM\\X2\\PROSITS\\PROJET PMF\\Images\\BackgroundImage.png"));
		lblNewLabel.setBounds(0, 0, 908, 471);
		contentPane.add(lblNewLabel);
	
	}
	
	private void ProgressBarEvolution() {
		int i;
		try {
			
			for(i=0; i <= 100; i++) {
				progressBar.setValue(i);
				Thread.sleep(100);
				LoadingValue.setText(Integer.toString(i) + "%");
				
				if(i == 0) {
					progressBar.setValue(0);
					LoadingValue.setText(i + "%");
				}
			
				if(i == 10) {
					progressBar.setValue(10);
					LoadingText.setText("Turning On Modules...");
					LoadingValue.setText(i + "%");
				}

				if(i == 30) {
					progressBar.setValue(30);
					LoadingText.setText("Loading Modules...");
					LoadingValue.setText(i + "%");
				}
				
				if(i == 50) {
					progressBar.setValue(50);
					LoadingText.setText("Connecting all the Program's Components...");
					LoadingValue.setText(i + "%");
				}
				
				if(i == 70) {
					progressBar.setValue(70);
					LoadingText.setText("Connection Successful...");
					LoadingValue.setText(i + "%");
				}
				
				if(i == 90) {
					progressBar.setValue(90);
					LoadingText.setText("Launching Application...");
					LoadingValue.setText(i + "%");
				}
				
				if(i == 100) {
					Thread.sleep(200);
					dispose();
					Thread.sleep(100);
				}
				
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
