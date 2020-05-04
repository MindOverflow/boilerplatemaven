package implementation;

import abstraction.DisplayElement;
import abstraction.Observer;
import abstraction.Subject;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

@Slf4j
public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f;
    private float lastPressure;
    private float temperature;
    private float humidity;
    private float pressure;

    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        this.lastPressure = currentPressure;
        this.currentPressure = pressure;
        display();
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
