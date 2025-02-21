import numpy as np
from sklearn.linear_model import LinearRegression
import joblib

class WeatherPredictor:
    def __init__(self):
        self.model = LinearRegression()
        self.history = []

    def add_data(self, temperature, humidity, pressure):
        self.history.append([temperature, humidity, pressure])
        if len(self.history) > 100:  # Limit history to the last 100 entries
            self.history.pop(0)

    def train_model(self):
        if len(self.history) < 2:
            return  # Not enough data to train the model
        X = np.array(self.history[:-1])  # All but the last entry
        y = np.array([self.history[i][0] for i in range(1, len(self.history))])  # Temperature as target
        self.model.fit(X, y)

    def predict(self, temperature, humidity, pressure):
        return self.model.predict(np.array([[temperature, humidity, pressure]]))[0]

    def save_model(self, filename):
        joblib.dump(self.model, filename)

    def load_model(self, filename):
        self.model = joblib.load(filename)