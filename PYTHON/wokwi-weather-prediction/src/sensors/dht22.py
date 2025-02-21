from machine import Pin
import dht

class DHT22:
    def __init__(self, pin):
        self.sensor = dht.DHT22(Pin(pin))

    def read(self):
        self.sensor.measure()
        temperature = self.sensor.temperature()
        humidity = self.sensor.humidity()
        return temperature, humidity