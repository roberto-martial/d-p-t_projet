/**
 * 
 */
package Contoller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PortUnreachableException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.SerialPort;

import Model.ArduinoSerial;
import View.ApplicationFrame;
import View.ArduinoConnection;

/**
 * @author NOUMEN DARRYL
 *
 */
public class ArduinoConnectionController implements ActionListener{
	
	//map the port names to CommPortIdentifiers
    @SuppressWarnings("rawtypes")
	private HashMap portMap = new HashMap();
	
	private ArduinoConnection arduino;
	
	private ApplicationFrame application;
	
	private ArduinoSerial arduinoSerial;
	
	private DataRetrieverController dataController;
	
	private Date date = new Date();
			
    //A string for recording what goes on in the program. This string is written to the log part
    String logText = "";

	public ArduinoConnectionController(ArduinoConnection arduinoConnection) {
		super();
		this.arduino = arduinoConnection;
		this.searchForPorts();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (this.arduino.getConnection().getText().equals("CONNECT")) {
			this.setArduinoSerial(new ArduinoSerial(this.arduino.getComboBox().getSelectedItem().toString()));
			try {
				this.arduinoSerial.connect();
				this.LoggingState();
				Thread.sleep(300);
			} catch (PortUnreachableException | InterruptedException error) {
				JOptionPane.showMessageDialog(null, error.toString());
			}
			if(this.arduinoSerial.isConnected() == true) {
				this.arduino.hide();
				this.application = new ApplicationFrame();
				this.dataController = new DataRetrieverController(application, arduino);
			}
		}
	}
	
	
	//search for all the serial ports
    //pre: none
    //post: adds all the found ports to a combo box on the GUI
    @SuppressWarnings("unchecked")
	public void searchForPorts() {
    	// populate the drop-down box
    	SerialPort[] portNames = SerialPort.getCommPorts();
    	for(int i = 0; i < portNames.length; i++) {
    		this.arduino.getComboBox().addItem(portNames[i].getSystemPortName());
    		portMap.put(portNames[i].getSystemPortName(), portNames[i]);
    	}
    }
    
    
    //connect to the selected port in the combo box
    //pre: ports are already found by using the searchForPorts method
    //post: the connected com port is stored in commPort, otherwise,
    //an exception is generated
    public void LoggingState() {
		
		if(this.arduinoSerial.isConnected() == true) {
			
			this.arduino.getComboBox().setEnabled(false);

            //logging
            logText = this.arduinoSerial.getchosenPort().getSystemPortName() + " opened successfully.";
            this.arduino.getTextArea().setForeground(Color.GREEN);
            this.arduino.getTextArea().append(" ---> " + new SimpleDateFormat("yyyy/MM/dd").format(date) + " " + new SimpleDateFormat("HH:mm:ss").format(date) + " : " + logText + "\n");
		}
		else {
			logText = "Failed to open " + this.arduinoSerial.getchosenPort().getSystemPortName();
            this.arduino.getTextArea().append(" ---> " + new SimpleDateFormat("yyyy/MM/dd").format(date) + " " + new SimpleDateFormat("HH:mm:ss").format(date) + " : " + logText + "\n");
            this.arduino.getTextArea().setForeground(Color.RED);
		}
		
    }

	/**
	 * @return the arduinoSerial
	 */
	public ArduinoSerial getArduinoSerial() {
		return arduinoSerial;
	}

	/**
	 * @param arduinoSerial the arduinoSerial to set
	 */
	public void setArduinoSerial(ArduinoSerial arduinoSerial) {
		this.arduinoSerial = arduinoSerial;
	}

	/**
	 * @return the arduino
	 */
	public ArduinoConnection getArduino() {
		return arduino;
	}

	/**
	 * @return the application
	 */
	public ApplicationFrame getApplication() {
		return application;
	}

	/**
	 * @return the dataController
	 */
	public DataRetrieverController getDataController() {
		return dataController;
	}

	/**
	 * @param arduino the arduino to set
	 */
	public void setArduino(ArduinoConnection arduino) {
		this.arduino = arduino;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(ApplicationFrame application) {
		this.application = application;
	}

	/**
	 * @param dataController the dataController to set
	 */
	public void setDataController(DataRetrieverController dataController) {
		this.dataController = dataController;
	}

}
