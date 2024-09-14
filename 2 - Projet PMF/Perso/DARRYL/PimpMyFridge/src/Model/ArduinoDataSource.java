/**
 * 
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.SerialPort;
//import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import Contoller.DataRetrieverController;

/**
 * @author NOUMEN DARRYL
 *
 */
public class ArduinoDataSource implements SerialPortMessageListener{
	
	/** Data Trame Identifier **/
	private static final String DataIdentifier = "D:";
	
	/** Feedback Identifier **/
	private static final String FeedbackIdentifier = "R:";
	
	private DataRetriever fridge;
	
	private ArrayList<DataRetrieverController> observers;
	
	private byte DELIMITER = (byte) 0x3B;

	
	public ArduinoDataSource() {
		this.fridge = new DataRetriever(0f, 0f, 0f, false, false);
		this.observers = new ArrayList<DataRetrieverController>();
	}

			
	@Override
	public  void serialEvent(SerialPortEvent oEvent) {
		// Uniquement les évents d'arrivée de données
		if (oEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
			System.out.println("[Arduino] Event : " + oEvent.getEventType());
			return;
		}
		
		byte[] rawData = oEvent.getReceivedData();
		String message = new String(rawData);
		System.out.println(message);
		
		// On tente de lire une trame
		try {
			// Trame de données
			if (message.startsWith(DataIdentifier)) {
				
				// On split la chaîne
				String[] tokens = message.substring(2).replaceFirst(";", "").split(",");
				// On devrait avoir 5 tokens
				if (tokens.length != 6) {
					System.out.println("[Arduino] Invalid data message (length)");
					return;
				}
				// Conversion en floats
				float valueOne = parseFloatArray(tokens[0]);
				float valueTwo = parseFloatArray(tokens[1]);
				float valueThree = parseFloatArray(tokens[2]);
				boolean valueFour = parseBooleanArray(tokens[3]);
				boolean valueFive = parseBooleanArray(tokens[4]);
				float valueSix = parseFloatArray(tokens[5]);
				
				// Vérification CRC
				if (tokens[5].equals("nan") && valueOne + valueTwo + valueThree != valueSix) {
					System.out.println("[Arduino] Invalid data message (crc)");
				}
				
				this.getFridge().setHumidity(valueOne);
				this.getFridge().setTemperatureIn(valueTwo);
				this.getFridge().setTemperatureOut(valueThree);
				this.getFridge().setDewPossible(valueFour);
				this.getFridge().setTemperatureGap(valueFive);
				
				this.notifyObservers();
			}
			
			
			// Trame de feedback après l'envoi d'une commande
			else if (message.startsWith(FeedbackIdentifier)) {
				int returnCode = Integer.parseInt(message.substring(2));
				if (returnCode == 1) {
					System.out.println("[Arduino] Fridge ON");
					notifyObservers(true);
				}
				else if (returnCode == 2) {
					System.out.println("[Arduino] Fridge OFF");
					notifyObservers(false);
				}
				else if (returnCode != 1 && returnCode != 2) {
					JOptionPane.showMessageDialog(null,"The given required is taken into consideration.");
					this.notifyObservers();
					notifyObservers(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"[Arduino] Invalid feedback code: " + returnCode, "Arduino Data Reception Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			// Trame inconnue ?!
			else{
				System.out.println("[Arduino] Invalid unknown message");
			}
		}
		catch (Throwable e) {
			System.out.println(String.format("[Arduino] Error %s ", e.toString()));
		}

	}

	private float parseFloatArray(String tokens) {
		if (!tokens.toLowerCase().equals("nan")) {
			float r = Float.parseFloat(tokens);
			return r;
		}
		return 0;
	}
	
	private boolean parseBooleanArray(String tokens) {
		boolean r = false;
		if (!tokens.equals("1")) {
			r = true;
		}
		else if (tokens.equals("0")){
			r = false;
		}
		return r;
	}
	
	/**
	 * 
	 * @param dataRetrieverController
	 */
	public void addObserver(DataRetrieverController dataRetrieverController) {
		this.observers.add(dataRetrieverController);
	}

	public void notifyObservers() {
		for (DataRetrieverController observer : this.observers) {
			observer.update();
		}
	}

	/**
	 * @param dataRetrieverController
	 */
	public void removeObserver(DataRetrieverController dataRetrieverController) {
		this.observers.remove(dataRetrieverController);
	}
	
	public void notifyObservers(final boolean powerOn) {
		this.observers.forEach(new Consumer<DataRetrieverController>() {
			@Override
			public void accept(DataRetrieverController observer) {
				observer.onPowerStatusChanged(powerOn);
			}
		});
	}
	
	/**
	 * @return the fridge
	 */
	public DataRetriever getFridge() {
		return fridge;
	}

	/**
	 * @param fridge the fridge to set
	 */
	public void setFridge(DataRetriever fridge) {
		this.fridge = fridge;
	}
	
	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_RECEIVED; 
	}
	
	public boolean delimiterIndicatesEndOfMessage() {
		return true;
	}

	@Override
	public byte[] getMessageDelimiter() {
		return new byte[] { DELIMITER };
	}

}
