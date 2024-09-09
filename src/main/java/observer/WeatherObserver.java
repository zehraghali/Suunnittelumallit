package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface WeatherObserver {
    void update(int temperature);
}

class ConcreteObserver implements WeatherObserver {
    private String observerName;

    public ConcreteObserver(String name) {
        this.observerName = name;
    }

    @Override
    public void update(int temperature) {
        System.out.println(observerName + " received an update: Current temperature is " + temperature + "°C");
    }
}


class WeatherStation implements Runnable {
    private List<WeatherObserver> observers;
    private int currentTemperature;
    private boolean running;
    private final int minTemperature = -10; // Minimum temperature
    private final int maxTemperature = 40;  // Maximum temperature

    public WeatherStation() {
        this.observers = new ArrayList<>();
        // Random initial temperature between min and max
        this.currentTemperature = new Random().nextInt((maxTemperature - minTemperature) + 1) + minTemperature;
        this.running = true;
    }


    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
        System.out.println("Observer added.");
    }

    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
        System.out.println("Observer removed.");
    }


    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(currentTemperature);
        }
    }

    private void updateTemperature() {
        Random random = new Random();
        int change = random.nextInt(3) - 1;  // Random change between -1, 0, 1
        currentTemperature += change;
        if (currentTemperature < minTemperature) {
            currentTemperature = minTemperature;
        } else if (currentTemperature > maxTemperature) {
            currentTemperature = maxTemperature;
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        while (running) {
            try {
                // Random delay between 1 to 5 seconds
                int delay = random.nextInt(5) + 1;
                Thread.sleep(delay * 1000);
                updateTemperature();
                System.out.println("Temperature updated to: " + currentTemperature + "°C");
                notifyObservers();
            } catch (InterruptedException e) {
                System.out.println("Weather station interrupted.");
            }
        }
    }

    public void stopStation() {
        this.running = false;
    }
}

class Main {
    public static void main(String[] args) {
        // Create a new weather station
        WeatherStation weatherStation = new WeatherStation();

        ConcreteObserver observer1 = new ConcreteObserver("Observer 1");
        ConcreteObserver observer2 = new ConcreteObserver("Observer 2");
        ConcreteObserver observer3 = new ConcreteObserver("Observer 3");

        weatherStation.registerObserver(observer1);
        weatherStation.registerObserver(observer2);
        weatherStation.registerObserver(observer3);

        Thread stationThread = new Thread(weatherStation);
        stationThread.start();

        try {
            Thread.sleep(15000);  // Let the simulation run for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        weatherStation.removeObserver(observer2);


        try {
            Thread.sleep(10000);  // Let it run for 10 more seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        weatherStation.stopStation();
    }
}
