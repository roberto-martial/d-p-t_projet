package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.PortUnreachableException;

import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.SerialPort;

/**
 * @author NOUMEN DARRYL
 *
 */

public class ArduinoSerial {
	
	public SerialPort chosenPort;
	private boolean Connected;
	
	/** Milliseconds to block while waiting for port open **/
	public static final int TIME_OUT = 2000;
	
	/** Default bits per second for COM port **/
	public static final int DATA_RATE = 9600;
	
	/** Writing Flow on the Serial Communication **/
	public OutputStream output;
	
	private boolean powerEnabled;
	
	public ArduinoSerial(String portName) {
		this.setchosenPort(SerialPort.getCommPort(portName));
		this.getchosenPort().setComPortTimeouts(TIME_OUT, 0, 0);
		this.getchosenPort().setBaudRate(DATA_RATE);
		this.setConnected(false);
		
	}
	
	public void connect() throws PortUnreachableException {
		try {
			this.getchosenPort().openPort();
			this.setConnected(true);
			this.setPowerEnabled(true);
		}
		catch (Throwable e) {
			throw new RuntimeException(e);
		}
		
	}

	public synchronized void disconnect() {
		if (this.getchosenPort() != null) {
			this.getchosenPort().closePort();
			this.setConnected(false);
			this.setPowerEnabled(false);
		}
	}
	
	public void writeData(String data) {
		this.output = this.getchosenPort().getOutputStream();
		try {
			this.output.write(data.getBytes());
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, String.format("Could not write data to serial, %s : %s", e.getClass().getSimpleName(), e.getMessage()));
		}
	}
	
	/**
	 * @return the connected
	 */
	public boolean isConnected() {
		return Connected;
	}

	/**
	 * @param connected the connected to set
	 */
	public void setConnected(boolean connected) {
		Connected = connected;
	}
	
	/**
	 * @return the chosenPort
	 */
	public SerialPort getchosenPort() {
		return chosenPort;
	}

	/**
	 * @param chosenPort the chosenPort to set
	 */
	public void setchosenPort(SerialPort chosenPort) {
		this.chosenPort = chosenPort;
	}
	
	/**
	 * @return the powerEnabled
	 */
	public boolean isPowerEnabled() {
		return powerEnabled;
	}
	
	/**
	 * @param powerEnabled the powerEnabled to set
	 */
	public void setPowerEnabled(boolean powerEnabled) {
		this.powerEnabled = powerEnabled;
	}

}
