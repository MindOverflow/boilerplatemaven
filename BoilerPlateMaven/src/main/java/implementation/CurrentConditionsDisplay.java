package implementation;

import abstraction.DisplayElement;
import abstraction.Observer;
import abstraction.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        log.info("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity and " + pressure + " pressure");
    }
}
