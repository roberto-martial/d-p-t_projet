/**
 * 
 */
package Contoller;

import View.ArduinoConnection;
import View.FirstWindow;

/**
 * @author NOUMEN DARRYL
 *
 */
public class MainApplication {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		FirstWindow frame = new FirstWindow();
		ArduinoConnection arduino = new ArduinoConnection();
	}

}
