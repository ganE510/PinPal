#include <ESP8266HTTPClient.h>
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>
#include <SoftwareSerial.h>
#include <WifiLocation.h>
#include <WiFiClient.h>
#include <time.h>

//TinyGPSPlus gps;  // The TinyGPS++ object

SoftwareSerial ss(4, 5); // The serial connection to the GPS device

const char* ssid = "ssid";
const char* password = "password";

//String key = "AIzaSyA5kTcYoudi-ciltTx-LXCzvnpPFhmHVpE";
//int status = WL_IDLE_STATUS;
const char* googleApiKey = "googleApiKey";

WifiLocation location(googleApiKey);

//WiFiServer server(80);
void setup()
{
  
  Serial.begin(9600);
  configTime(0, 0, "pool.ntp.org");  
  setenv("TZ", "CET-1CEST,M3.5.0,M10.5.0/3", 0); 
  //Serial.setDebugOutput(true);
  //NETWORK SCAN

  WiFi.disconnect(true);
  WiFi.setAutoConnect(false);
  WiFi.setPhyMode(WIFI_PHY_MODE_11G);
  WiFi.mode(WIFI_STA);
  



  // Wifi connexion
  
  //delay(1000);
  WiFi.begin(ssid, password);
  WiFi.printDiag(Serial);
  Serial.println(WiFi.getPhyMode());

  Serial.print("Connecting");
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println();
  Serial.print("Connected, IP address: ");
  Serial.println(WiFi.localIP());

  delay(5000);

 
 
 
   Serial.println("launching softwareserial ");
   ss.begin(9600);

}

void loop()
{
  time_t tnow = time(nullptr);
  Serial.println(" Starting loop");

  if(ss.available()==0){
    
    Serial.println("API LOOP");
  
    location_t loc = location.getGeoFromWiFi();
     delay(500);

    
    Serial.println("Location request data");
    String lat = String(loc.lat, 7);
    String lng = String(loc.lon, 7);
   // Serial.println(location.getSurroundingWiFiJson());
    Serial.println("Latitude: " + lat);
    Serial.println("Longitude: " + lng);
    Serial.println("Accuracy: " + String(loc.accuracy));


  if (WiFi.status() == WL_CONNECTED) { //Check WiFi connection status

    const int capacity = JSON_OBJECT_SIZE(3);

    // Declare a buffer to hold the result
    char output[128];
    StaticJsonDocument<200> doc;
    doc["reg"] = 123456;
    doc["lat"] = lat;
    doc["lng"] = lng;
    serializeJson(doc, output);
    Serial.println(output);

    HTTPClient http;    //Declare object of class HTTPClient

    http.begin("http://192.168.137.1:5000/update");      //Specify request destination
    http.addHeader("Content-Type", "application/json");  //Specify content-type header

    int httpCode = http.POST(output);   //Send the request
    String payload = http.getString();                                        //Get the response payload

    Serial.println(httpCode);   //Print HTTP return code
    Serial.println(payload);    //Print request response payload

    http.end();  //Close connection

  } else {

    Serial.println("Error in WiFi connection");

  }

    
 
    delay(500);


    Serial.print(String(ctime(&tnow)));
    delay(1000);
 
  } else {
    
  delay(1000);
  
  while (ss.available() > 0){
    
     //Serial.println("in SS, B:");
    // get the byte data from the GPS
    byte gpsData = ss.read();
    Serial.write(gpsData);
    //Serial.println(ss.available());

    // Serial.print(String(ctime(&tnow)));
    //  delay(1000);

   

  }
     

    
  
  

  


  }

  
  }
