import network
import time
from machine import Pin
import dht
import ujson
from umqtt.simple import MQTTClient
import requests

url = "https://eliezermga.pythonanywhere.com/update_sensor"
sensor = dht.DHT22(Pin(15))

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
  sensor.measure() 
  data = {
    "temperature": sensor.temperature(),
    "humidity": sensor.humidity(),
  }
  message = ujson.dumps(data)
  if message != prev_weather:
    prev_weather = message
    response = requests.post(url, json=data)  
    print("Posted to API {}: {}: {}".format(url, message, response.status_code))
  time.sleep(1)