/**
 * 
 */
package Contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.PortUnreachableException;

import Model.ArduinoDataSource;
import Model.ArduinoSerial;
import View.ApplicationFrame;
import View.ArduinoConnection;

/**
 * @author NOUMEN DARRYL
 *
 */
public class DataRetrieverController {
	
	private ArduinoDataSource arduinoData;
	
	private ArduinoSerial arduinoSerial;
	
	private ApplicationFrame application;
	
	private ArduinoConnectionController arduinoConnection;
	
	private ArduinoConnection arduino;
	
	public InputStream input;

	public DataRetrieverController(ApplicationFrame Application, ArduinoConnection Arduino) {
		super();
		this.setApplication(Application);
		this.setArduinoData(new ArduinoDataSource());
		this.setArduino(Arduino);
		this.setArduinoSerial(this.getArduino().getController().getArduinoSerial());
		this.getArduinoSerial().writeData(this.getApplication().getValue());
		this.start();
		
		this.getApplication().getLabelConsigneTemp().setText(Float.parseFloat(this.getApplication().getValue()) + "\u00B0C");
		
		this.getApplication().chart.mark.setValue(Double.parseDouble(getApplication().getValue()));
		
		this.ApplicationButtonsControl();
		
	}
	
	public void start() {
		this.getArduinoSerial().getchosenPort().addDataListener(this.arduinoData);
		this.getArduinoData().addObserver(this);
		try {
			this.getArduinoSerial().connect();
		} catch (PortUnreachableException e) {
			e.printStackTrace();
		}
		
		System.out.println("=== Ouverture de la connexion sur le port " + this.getArduinoSerial().getchosenPort().getSystemPortName() + " ===");
	}

	public void close() {
		this.getArduinoSerial().getchosenPort().removeDataListener();
		this.getArduinoData().removeObserver(this);
		this.getArduinoSerial().disconnect();
		
		System.out.println("=== Fermeture de la connexion sur le port " + this.getArduinoSerial().getchosenPort().getSystemPortName() + " ===");
	}
	
	public void update() {
		System.out.println(this.getArduinoData().getFridge() + "\n");
		this.getApplication().getLabelHumitidy().setText(this.getArduinoData().getFridge().getHumidity() + "%");
		this.getApplication().getLabelTempInt().setText(this.getArduinoData().getFridge().getTemperatureIn() + "\u00B0C");
		this.getApplication().getLabelTempExt().setText(this.getArduinoData().getFridge().getTemperatureOut() + "\u00B0C");
	    this.getApplication().chart.addData((float)this.getArduinoData().getFridge().getTemperatureIn(), (float)this.getArduinoData().getFridge().getTemperatureOut());
	    this.getArduinoData().getFridge().isDewPossible();
		this.getArduinoData().getFridge().isTemperatureGap();
		this.checkInformativeMessage();
	}
	
	public void ApplicationButtonsControl() {
		
		this.getApplication().getDisconnect().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				DataRetrieverController.this.close();
				DataRetrieverController.this.application.dispose();
				DataRetrieverController.this.arduino.setVisible(true);
				DataRetrieverController.this.arduino.getComboBox().setEnabled(true);
			}
		});
		
		this.getApplication().getOnbutton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				DataRetrieverController.this.start();
				DataRetrieverController.this.getArduinoSerial().writeData("1");
				DataRetrieverController.this.getArduinoSerial().setPowerEnabled(true);
			}
		});
		
		this.getApplication().getOffbutton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				DataRetrieverController.this.getArduinoSerial().writeData("2");
				DataRetrieverController.this.getArduinoSerial().setPowerEnabled(false);
				DataRetrieverController.this.close();
			}
		});
		
		this.getApplication().getRequiredTemperatureIncrease().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				float newrequiredtemperature = Float.parseFloat(DataRetrieverController.this.getApplication().getValue()) + 0.5f;
				DataRetrieverController.this.getApplication().setValue("" + newrequiredtemperature + "");
				DataRetrieverController.this.getArduinoSerial().writeData(""+ newrequiredtemperature + "");
				DataRetrieverController.this.getApplication().getLabelConsigneTemp().setText(newrequiredtemperature + "\u00B0C");
				DataRetrieverController.this.getApplication().chart.mark.setValue(Double.parseDouble(getApplication().getValue()));
			}
		});
		
		this.getApplication().getRequiredTemperatureDecrease().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				float newrequiredtemperature = Float.parseFloat(DataRetrieverController.this.getApplication().getValue()) - 0.5f;
				DataRetrieverController.this.getApplication().setValue("" + newrequiredtemperature + "");
				DataRetrieverController.this.getArduinoSerial().writeData(""+ newrequiredtemperature + "");
				DataRetrieverController.this.getApplication().getLabelConsigneTemp().setText(newrequiredtemperature + "\u00B0C");
				DataRetrieverController.this.getApplication().chart.mark.setValue(Double.parseDouble(getApplication().getValue()));
			}
		});
		
	}
	
	public void checkInformativeMessage() {
		if(this.getArduinoData().getFridge().isTemperatureGap() == true) {
			this.getApplication().getAlertMessage().setText("Temperature Rise");
		}
		
		else if(this.getArduinoData().getFridge().isDewPossible() == true) {
			this.getApplication().getAlertMessage().setText("Risk Of Condensation");
		}
	}
	
	public void CollectData() throws IOException {
		
	}

	/**
	 * @return the arduinoData
	 */
	public ArduinoDataSource getArduinoData() {
		return arduinoData;
	}

	/**
	 * @return the arduinoSerial
	 */
	public ArduinoSerial getArduinoSerial() {
		return arduinoSerial;
	}

	/**
	 * @return the application
	 */
	public ApplicationFrame getApplication() {
		return application;
	}

	/**
	 * @param arduinoData the arduinoData to set
	 */
	public void setArduinoData(ArduinoDataSource arduinoData) {
		this.arduinoData = arduinoData;
	}

	/**
	 * @param arduinoSerial the arduinoSerial to set
	 */
	public void setArduinoSerial(ArduinoSerial arduinoSerial) {
		this.arduinoSerial = arduinoSerial;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(ApplicationFrame application) {
		this.application = application;
	}

	/**
	 * @return the arduinoConnection
	 */
	public ArduinoConnectionController getArduinoConnection() {
		return arduinoConnection;
	}

	/**
	 * @return the arduino
	 */
	public ArduinoConnection getArduino() {
		return arduino;
	}

	/**
	 * @param arduinoConnection the arduinoConnection to set
	 */
	public void setArduinoConnection(ArduinoConnectionController arduinoConnection) {
		this.arduinoConnection = arduinoConnection;
	}

	/**
	 * @param arduino the arduino to set
	 */
	public void setArduino(ArduinoConnection arduino) {
		this.arduino = arduino;
	}

	public void onPowerStatusChanged(boolean powerOn) {
		this.getArduinoData().notifyObservers(powerOn);
	}

}
