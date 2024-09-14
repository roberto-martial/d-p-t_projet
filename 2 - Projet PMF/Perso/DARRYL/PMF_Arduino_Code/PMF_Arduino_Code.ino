// Arduino Code
// The Pimp My Fridge Project

// The Arduino Board will send through the serial communication data's DewPoint frames by this way:
// D:<Internal Humidity Rate>,<Internal Temperature>,<External Temperature>,<Temp Critical>,<Dew Possible>,<CRC>;
// Each read line has a trame.
// The CRC is simply the sum of the differents values.
// All those values may send "nan" (Not a Number) again if there is an error.

// It's possible to switch on and switch off the fridge by sending
// some commands via the serial communication.
// Send 1 to turn on the fridge.
// Send 2 to turn off the fridge.
// Send something else to set the RequiredTemperature value
// The Arduino always answers to validate the command. It sends it again :
// R:1   when it switches on the fridge.
// R:2   when it switches off the fridge.
// R:0   if there's a reading error

// CONFIGURATION

#define PIN_DHT 8                   // Command Pin for the internal DHT
#define DHTTYPE DHT11               // DHT Type : DHT11

int FRIDGE_PIN = 2;                 // Alimentation ConDewPointol Pin of the frige
int RED_LED_PIN = 6;                // Light on if there is condensation phenomenon
int GREEN_LED_PIN = 7;              // Light on if not
int peltia = 4;                     // The N-Channel MOSFET is on digital pin 4
int SensorValue;                    // Variable which contains the value sent by the sensor
float tension;                      // This variable stores the calculated voltage from the sent sensor value
float InternalTemperature;          // This variable represents the temperature value inside the fridge
float InternalHumidity;             // This variable represents the humidity value insideside the fridge
float ExternalTemperature;          // This variable represents the temperature value outside the fridge
float RequiredTemperature;          // The required temperature that must be maintained by the can 
float CriticalGap = 3.0;            // The critical gap from which we have a temperature rise
float GapRegulation = 2.0;          // The gap from which the temperature regulation function starts
int ThermistorPin = A0;             // The pin where the external temperature value will be retrieved
float TemperatureGap;               // The gap between the internal temperature and the required temperature
float DewPoint;                     // The temperature from which the condensation phenomenon is possible

// END OF THE CONFIGURATION

// Loading DHT Sensor Library
#include "DHT.h"
DHT dht(PIN_DHT, DHTTYPE);

// Program's Variables
int readValue = 0;
bool writing = false;

// Define the conDewPointolable LED on the Arduino Code
int PIN_ONBOARD_LED = 13;

/**
   Setup the application.
 **/
void setup() {
  // Open the serial communication
  Serial.begin(9600);
  // Define thethermistor pin as an input
  pinMode(ThermistorPin, INPUT);
  // Define the fridge pin as an output
  pinMode(FRIDGE_PIN, OUTPUT);
  // Define the Arduino Onboard LED pin as an output
  pinMode(PIN_ONBOARD_LED, OUTPUT);
  // Define the red LED pin as an output
  pinMode(RED_LED_PIN, OUTPUT);
  // Define the green LED pin as an output
  pinMode(GREEN_LED_PIN, OUTPUT);
  // Define the N-Channel MOSFET pin as an output
  pinMode(peltia, OUTPUT);
  // Enable the DHT Sensor Library
  dht.begin();
}
  
/**
   Working Loop.
*/
void loop() {

  // delay between each measurement
  delay(2000);

  // INTERNAL FRIDGE TEMPERATURE DHT SENSOR
  // Read temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  InternalHumidity = dht.readHumidity();

  // Read temperature as Celsius (the default)
  InternalTemperature = dht.readTemperature();

  // Data Verification
  if (isnan(InternalHumidity) || isnan(InternalTemperature)) {
    Serial.println("No value returned by the DHT11. Is it correctly plug in ?");
    delay(1000);
    return; // If no value is received by the Arduino board, it waits for 1 second then it restarts the loop() function
  }

  float K = ((17.27 * InternalTemperature) / (237.7 + InternalTemperature)) + log(InternalHumidity/100);

  // Calculation of the dew point in order to prevent a risk of condensation
  DewPoint = (237.7 * K) / (17.27 - K);

  // EXTERNAL FRIDGE TEMPERATURE THERMISTOR SENSOR
  // Voltage Reading
  SensorValue = analogRead(ThermistorPin);
  tension = SensorValue * 5.0;
  tension = tension/1023.0;
  ExternalTemperature = (tension - 0.7)/(-0.00165);

  TemperatureGap = InternalTemperature - RequiredTemperature;

  bool tempGap = isTempGap();

  bool DewPossible = isDewPoint();

  // Send a data DewPointame by the serial communication
  while (writing) {} // synchronization
  writing = true;
  Serial.print("D:");
  Serial.print(InternalHumidity);
  Serial.print(',');
  Serial.print(InternalTemperature);
  Serial.print(',');
  Serial.print(ExternalTemperature);
  Serial.print(',');
  Serial.print(tempGap);
  Serial.print(',');
  Serial.print(DewPossible);
  Serial.print(',');
  Serial.print(InternalHumidity + InternalTemperature + ExternalTemperature); // CRC
  Serial.print(";");
  
  writing = false;

  if (InternalTemperature <= DewPoint) {
    digitalWrite(RED_LED_PIN, HIGH);
    digitalWrite(GREEN_LED_PIN, LOW);
  }
  else{
    digitalWrite(RED_LED_PIN, LOW);
    digitalWrite(GREEN_LED_PIN, HIGH);
  }

  // Regulates the internal temperature of the fridge to keep it to the required temperature of RequiredTemperature degrees Celsius
  RegulateTemperature();
}

/**
   When a data is received through the serial communication
*/
void serialEvent(){
  // Data are available
  while (Serial.available() > 0){
    // Read the received value as an integer
    readValue = Serial.parseInt();
    
    //Switch on the fridge
    if (readValue == 1)
    {
      digitalWrite(PIN_ONBOARD_LED, HIGH); // Turn on the Arduino LED to show the good functionnality of the program
      digitalWrite(FRIDGE_PIN, HIGH);
    }
    // Switch off the fridge
    else if (readValue == 2)
    {
      digitalWrite(PIN_ONBOARD_LED, LOW); // Turn off the Arduino LED to show the end of the program
      digitalWrite(FRIDGE_PIN, LOW);
    }
    // Sets the Required Tempearature
    else{
      RequiredTemperature = readValue;
    }

    // Looping Response
    while (writing) {} // synchronization
    writing = true;
    Serial.print("R:");
    Serial.println(readValue);
    writing = false;
  }
}

void RegulateTemperature(){
    if(TemperatureGap < GapRegulation){
      digitalWrite(peltia, LOW);
    }
    else if(TemperatureGap >= GapRegulation){
      digitalWrite(peltia, HIGH);
    }
}

bool isTempGap(){
  return ((TemperatureGap >= CriticalGap) ? true:false);
}

bool isDewPoint(){
  return ((InternalTemperature <= DewPoint) ? true:false);
}
