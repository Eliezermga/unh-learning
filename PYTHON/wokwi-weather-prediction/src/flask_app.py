from flask import Flask, render_template, jsonify, request
from ai_model import predict_weather
from sensors.dht22 import read_dht22
from sensors.bmp180 import read_bmp180

app = Flask(__name__)

temperature = None
humidity = None
pressure = None
prediction = None

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/update_sensor', methods=['POST'])
def update_sensor():
    global temperature, humidity, pressure, prediction
    data = request.json
    temperature = data.get('temperature')
    humidity = data.get('humidity')
    pressure = data.get('pressure')
    prediction = predict_weather(temperature, humidity, pressure)
    return jsonify({"status": "success", "message": "Values updated"})

@app.route('/get_sensor_data')
def get_sensor_data():
    return jsonify({
        "temperature": temperature,
        "humidity": humidity,
        "pressure": pressure,
        "prediction": prediction
    })

if __name__ == '__main__':
    app.run(debug=True, port=5001)