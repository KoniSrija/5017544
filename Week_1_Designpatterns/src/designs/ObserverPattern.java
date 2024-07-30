package designs;

import java.util.*;
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}


class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double stockPrice;

    public StockMarket(String stockName) {
        this.stockName = stockName;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, stockPrice);
        }
    }
}


interface Observer {
    void update(String stockName, double stockPrice);
}


class MobileApp implements Observer {
    private String stockName;
    private double stockPrice;

    @Override
    public void update(String stockName, double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        display();
    }

    private void display() {
        System.out.println("MobileApp - Stock: " + stockName + ", Price: $" + stockPrice);
    }
}


class WebApp implements Observer {
    private String stockName;
    private double stockPrice;

    @Override
    public void update(String stockName, double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        display();
    }

    private void display() {
        System.out.println("WebApp - Stock: " + stockName + ", Price: $" + stockPrice);
    }
}
//Testing
public class ObserverPattern {
 public static void main(String[] args) {
     StockMarket stockMarket = new StockMarket("Sales");

     MobileApp mobileApp = new MobileApp();
     WebApp webApp = new WebApp();

   
     stockMarket.registerObserver(mobileApp);
     stockMarket.registerObserver(webApp);

  
     stockMarket.setStockPrice(150.75);

         stockMarket.deregisterObserver(webApp);

   
     stockMarket.setStockPrice(160.00);
 }
}
