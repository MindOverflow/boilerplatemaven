package implementation;

import abstraction.DisplayElement;
import abstraction.Observer;
import abstraction.Subject;
import implementation.WeatherData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatisticsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private float minTemp = 200f;
    private float maxTemp = 0.0f;
    private float sumTemp = 0.0f;
    private int numReadings;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;

        sumTemp += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
        if (temperature < minTemp) {
            minTemp = temperature;
        }
        display();
    }

    @Override
    public void display() {
        log.info("Avg/Max/Min temperature = " + (sumTemp / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}
