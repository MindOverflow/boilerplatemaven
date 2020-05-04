package implementation;

import abstraction.DisplayElement;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.Observable;
import java.util.Observer;

@Slf4j
public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f;
    private float lastPressure;

    public ForecastDisplay(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof WeatherData) {
            var weatherData = (WeatherData)observable;
            this.lastPressure = currentPressure;
            this.currentPressure = weatherData.getPressure();
            display();
        }
    }

    @Override
    public void display() {
        var f = "Forecast: ";
        if (currentPressure > lastPressure) {
            log.info(f + "Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            log.info(f + "More of the same");
        } else if (currentPressure < lastPressure) {
            log.info(f + "Watch out for cooler, rainy weather");
        }
    }
}
