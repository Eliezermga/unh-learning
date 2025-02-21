from machine import I2C, Pin
import bmp180

class BMP180Sensor:
    def __init__(self, i2c_bus):
        self.bmp = bmp180.BMP180(i2c_bus)

    def read_pressure(self):
        pressure = self.bmp.read_pressure()
        return pressure

    def read_temperature(self):
        temperature = self.bmp.read_temperature()
        return temperature

    def read_data(self):
        temperature = self.read_temperature()
        pressure = self.read_pressure()
        return {
            "temperature": temperature,
            "pressure": pressure
        }