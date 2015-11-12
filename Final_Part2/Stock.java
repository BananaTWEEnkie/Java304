package stock;

import java.text.NumberFormat;

public class Stock
{
    private String ticker;
    private double price;
        
    public Stock()
    {
        this("", 0);
    }

    public Stock(String ticker, double price)
    {
        this.ticker = ticker;
        this.price = price;
    }

    public void setTicker(String ticker)
    {
        this.ticker = ticker;
    }

    public String getTicker(){
        return ticker;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }
    
    public String getFormattedPrice()
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
    
    @Override
    public String toString()
    {
        return "Ticker: " + ticker + "\n" +
               "Price:  " + getFormattedPrice () + "\n";
    }
}