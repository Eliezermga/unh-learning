# README.md

# Weather Prediction Dashboard

This project is a Weather Prediction Dashboard that collects real-time weather data using DHT22 and BMP180 sensors, predicts short-term weather conditions using a simple AI model, and displays the information on a web interface.

## Project Structure

```
wokwi-weather-prediction
├── templates
│   └── index.html          # HTML structure for the web interface
├── static
│   └── style.css           # CSS styles for the web interface
├── src
│   ├── main.py             # Connects to Wi-Fi and collects sensor data
│   ├── flask_app.py        # Sets up the Flask web server
│   ├── ai_model.py         # Implements the AI model for weather prediction
│   └── sensors
│       ├── dht22.py        # Code to interface with the DHT22 sensor
│       └── bmp180.py       # Code to interface with the BMP180 sensor
├── requirements.txt         # Python dependencies for the project
└── README.md                # Documentation for the project
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd wokwi-weather-prediction
   ```

2. **Install the required packages:**
   ```
   pip install -r requirements.txt
   ```

3. **Connect the sensors:**
   - Connect the DHT22 sensor to the appropriate GPIO pins.
   - Connect the BMP180 sensor to the appropriate GPIO pins.

4. **Run the Flask application:**
   ```
   python src/flask_app.py
   ```

5. **Run the main script to collect data:**
   ```
   python src/main.py
   ```

6. **Access the web interface:**
   Open a web browser and navigate to `http://localhost:5001` to view the dashboard.

## Usage

The dashboard displays the current temperature, humidity, and atmospheric pressure readings from the sensors. It also shows AI predictions for short-term weather conditions based on the collected data.

## Components

- **DHT22 Sensor:** Measures temperature and humidity.
- **BMP180 Sensor:** Measures atmospheric pressure.
- **AI Model:** Utilizes historical data to predict short-term weather conditions.
- **Flask Web Server:** Serves the web interface and provides sensor data via JSON endpoints.

## License

This project is licensed under the MIT License.