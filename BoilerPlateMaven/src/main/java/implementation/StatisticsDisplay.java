package implementation;

import abstraction.DisplayElement;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.Observable;
import java.util.Observer;

@Slf4j
public class StatisticsDisplay implements Observer, DisplayElement {
    private Observable observable;
    private float minTemp = 200f;
    private float maxTemp = 0.0f;
    private float sumTemp = 0.0f;
    private int numReadings;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object args) {
        if (observable instanceof WeatherData) {
            var weatherData = (WeatherData)observable;
            var temperature = weatherData.getTemperature();

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
    }

    @Override
    public void display() {
        log.info("Avg/Max/Min temperature = " + (sumTemp / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}
