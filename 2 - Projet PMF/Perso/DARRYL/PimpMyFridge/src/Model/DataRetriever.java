package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NOUMEN DARRYL
 *
 */

public class DataRetriever {
	
	private List<Float> values;
	
	private float humidity;
	private float temperatureOut;
	private float temperatureIn;
	private boolean temperatureGap;
	private boolean dewPossible;

	/**
	 * @param humidity  Humidity Percentage.
	 * @param tempIn    Internal Temperature.
	 * @param tempOut   External Temperature.
	 * @param temperatureGap  TemperatureGap.
	 * @param dewPossible  DewPossible.
	 */
	public DataRetriever(float humidity, float tempIn, float tempOut, boolean temperatureGap, boolean dewPossible) {
		this.humidity = humidity;
		this.temperatureOut = tempOut;
		this.temperatureIn = tempIn;
		this.temperatureGap = temperatureGap;
		this.dewPossible = dewPossible;
	}

	/**
	 * @return the humidity
	 */
	public float getHumidity() {
		return humidity;
	}

	/**
	 * @return the temperatureOut
	 */
	public float getTemperatureOut() {
		return temperatureOut;
	}

	/**
	 * @return the temperatureIn
	 */
	public float getTemperatureIn() {
		return temperatureIn;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	/**
	 * @param temperatureOut the temperatureOut to set
	 */
	public void setTemperatureOut(float temperatureOut) {
		this.temperatureOut = temperatureOut;
	}

	/**
	 * @param temperatureIn the temperatureIn to set
	 */
	public void setTemperatureIn(float temperatureIn) {
		this.temperatureIn = temperatureIn;
	}

	/**
	 * @return the dewPossible
	 */
	public boolean isDewPossible() {
		return dewPossible;
	}

	/**
	 * @return the temperatureGap
	 */
	public boolean isTemperatureGap() {
		return temperatureGap;
	}

	/**
	 * @param dewPossible the dewPossible to set
	 */
	public void setDewPossible(boolean dewPossible) {
		this.dewPossible = dewPossible;
	}

	/**
	 * @param temperatureGap the temperatureGap to set
	 */
	public void setTemperatureGap(boolean temperatureGap) {
		this.temperatureGap = temperatureGap;
	}
	
	@Override
	public String toString() {
		return String.format("Internal Temperature = %s°C  External Temperature = %s°C  Humidity = %s%%  TemperatureGap = %s DewPossible = %s ", temperatureIn, temperatureOut, humidity, temperatureGap, dewPossible);
	}
	
}
