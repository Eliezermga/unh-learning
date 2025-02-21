import network
import time
import ujson
import requests
from machine import Pin
from sensors.dht22 import DHT22
from sensors.bmp180 import BMP180

url = "https://eliezermga.pythonanywhere.com/update_sensor"
dht_sensor = DHT22(Pin(15))
bmp_sensor = BMP180(Pin(16))

print("Connecting to WiFi", end="")
sta_if = network.WLAN(network.STA_IF)
sta_if.active(True)
sta_if.connect('Wokwi-GUEST', '')
while not sta_if.isconnected():
    print(".", end="")
    time.sleep(0.1)
print(" Connected!")

prev_weather = ""
while True:
    dht_sensor.measure()
    bmp_sensor.measure()
    
    data = {
        "temperature": dht_sensor.temperature(),
        "humidity": dht_sensor.humidity(),
        "pressure": bmp_sensor.pressure(),
    }
    
    message = ujson.dumps(data)
    if message != prev_weather:
        prev_weather = message
        response = requests.post(url, json=data)  
        print("Posted to API {}: {}: {}".format(url, message, response.status_code))
    
    time.sleep(1)