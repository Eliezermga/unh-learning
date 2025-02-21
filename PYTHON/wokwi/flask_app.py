from flask import Flask, render_template, jsonify, request

app = Flask(__name__)

temperature = None
humidity = None

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/update_sensor', methods=['POST'])
def update_sensor():
    global temperature, humidity
    temperature = request.json.get('temperature')
    humidity = request.json.get('humidity')
    return jsonify({"status": "success", "message": "Values updated"})

@app.route('/get_sensor_data')
def get_sensor_data():
    return jsonify({"temperature": temperature, "humidity": humidity})

if __name__ == '__main__':
    app.run(debug=True, port=5001)


