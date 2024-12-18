package nino.rs.mdsinformaticki.model;

import java.time.LocalDate;

public class Sell {

    private LocalDate dateBuy;
    private LocalDate dateSell;

    private double priceBuy;
    private double priceSell;

    private double profit;

    private double maxProfit;

    public Sell(LocalDate dateBuy, LocalDate dateSell, double priceBuy, double priceSell, double profit) {
        this.dateBuy = dateBuy;
        this.dateSell = dateSell;
        this.priceBuy = priceBuy;
        this.priceSell = priceSell;
        this.profit = profit;
    }

    public LocalDate getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(LocalDate dateBuy) {
        this.dateBuy = dateBuy;
    }

    public double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(double priceSell) {
        this.priceSell = priceSell;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public LocalDate getDateSell() {
        return dateSell;
    }

    public void setDateSell(LocalDate dateSell) {
        this.dateSell = dateSell;
    }

    public double getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(double maxProfit) {
        this.maxProfit = maxProfit;
    }
}
