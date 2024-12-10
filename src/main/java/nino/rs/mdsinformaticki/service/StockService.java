package nino.rs.mdsinformaticki.service;

import nino.rs.mdsinformaticki.model.Sell;
import nino.rs.mdsinformaticki.model.Stock;
import nino.rs.mdsinformaticki.model.Value;
import nino.rs.mdsinformaticki.request.StockReq;
import nino.rs.mdsinformaticki.respository.StockRepository;
import nino.rs.mdsinformaticki.respository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ValueRepository valueRepository;

    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public boolean addStock(Stock stock) {

        if(stockRepository.existsByName(stock.getName()))
            return false;

        stockRepository.save(stock);

        System.out.println(stock.getValues().size());
        for(Value value : stock.getValues())
            valueRepository.save(value);

        return true;
    }

    public boolean updateStock(Stock stock) {
        // Provera da li entitet Stock postoji
        if (stockRepository.findByName(stock.getName()) == null) {
            return false;
        }

        Stock existingStock = stockRepository.findByName(stock.getName());

        if (stock.getName() != null) {
            existingStock.setName(stock.getName());
        }

        if (stock.getMark() != null) {
            existingStock.setMark(stock.getMark());
        }

        if (stock.getFoundingDate() != null) {
            existingStock.setFoundingDate(stock.getFoundingDate());
        }


        if (stock.getValues() == null) {
            return true;
        }

        Set<Value> updatedValues = stock.getValues();
        Set<Value> existingValues = existingStock.getValues();

        existingValues.removeIf(existingValue ->
                updatedValues.stream().noneMatch(updatedValue -> updatedValue.getId() != null && updatedValue.getId().equals(existingValue.getId())));

        for (Value updatedValue : updatedValues) {
            Optional<Value> existingValueOpt = existingValues.stream()
                    .filter(ev -> updatedValue.getId() != null && ev.getId().equals(updatedValue.getId()))
                    .findFirst();

            if (existingValueOpt.isPresent()) {
                Value existingValue = existingValueOpt.get();
                if (updatedValue.getDate() != null) {
                    existingValue.setDate(updatedValue.getDate());
                }

                if (updatedValue.getClose() != null) {
                    existingValue.setClose(updatedValue.getClose());
                }

            } else {
                updatedValue.setStock(existingStock);
                existingValues.add(updatedValue);
            }
        }

        stockRepository.save(existingStock);

        return true;
    }


    public boolean deleteStock(String name) {

        Stock stock = stockRepository.findByName(name);

        System.out.println(stock);

        if(!stockRepository.existsById(stock.getId()))
            return false;

        stockRepository.deleteById(stock.getId());
        return true;
    }

    public List<Sell> info(StockReq stockReq){

        Stock stock = stockRepository.findByName(stockReq.getName());

        LocalDate from = stockReq.getFrom();
        LocalDate to = stockReq.getTo();

        long days = ChronoUnit.DAYS.between(from, to);

        LocalDate previousFrom = from.minusDays(days + 1); // Dodajemo 1 da bismo izbegli preklapanje
        LocalDate previousTo = from.minusDays(1);

        LocalDate nextFrom = to.plusDays(1);
        LocalDate nextTo = to.plusDays(days + 1);

        List<Value> valuesBefore = valueRepository.findAllByStockAndDateBetween(stock, previousFrom, previousTo);
        List<Value> valuesCrr = valueRepository.findAllByStockAndDateBetween(stock, stockReq.getFrom(), stockReq.getTo());
        List<Value> valuesAfter = valueRepository.findAllByStockAndDateBetween(stock, nextFrom, nextTo);

        Sell sellBefore = calculatePeriod(valuesBefore);
        Sell sellCurr = calculatePeriod(valuesCrr);
        Sell sellAfter = calculatePeriod(valuesAfter);

        return new ArrayList<>(List.of(sellBefore, sellCurr, sellAfter));
    }

    private Sell calculatePeriod(List<Value> values){

        LocalDate sellDate = null;
        LocalDate buyDate = null;

        double minClose = Double.MAX_VALUE;
        double maxProfit = 0.0;

        double sumProfit = 0.0;

        Sell sell = null;

        while(true){

            for(Value value : values){

                if(value.getClose() < minClose){
                    minClose = value.getClose();
                    buyDate = value.getDate();
                }

                if(value.getClose() - minClose > maxProfit){
                    maxProfit = value.getClose() - minClose;
                    sellDate = value.getDate();
                }

            }

            if(sellDate != null && buyDate != null && sellDate.isAfter(buyDate) && sell == null){
                sell = new Sell(buyDate, sellDate, minClose,minClose + maxProfit, maxProfit);
                System.out.println("dodao sam najbolji trade: " + maxProfit + "za period: " + buyDate + " - " + sellDate);

                values.remove(getValueFromList(values, minClose));

                minClose = Double.MAX_VALUE;
                maxProfit = 0.0;

                continue;
            }

            if(sellDate != null && buyDate != null && !sellDate.isAfter(buyDate)){
                values.remove(getValueFromList(values, minClose));
                minClose = Double.MAX_VALUE;
                maxProfit = 0.0;
                continue;
            }

            if(sellDate != null && buyDate != null && sellDate.isAfter(buyDate)){
                sumProfit += maxProfit;
                values.remove(getValueFromList(values, minClose));
                System.out.println("dodao sam profit: " + maxProfit + "za period: " + buyDate + " - " + sellDate);
            }


            if(values.size() == 1)
                break;

            minClose = Double.MAX_VALUE;
            maxProfit = 0.0;

        }

        sell.setMaxProfit(sumProfit);

        return sell;
    }

    private Value getValueFromList(List<Value> values, double close){

        for(Value value : values){
            if(value.getClose().equals(close))
                return value;
        }

        return null;
    }
}
