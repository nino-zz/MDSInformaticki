package nino.rs.mdsinformaticki.service;

import nino.rs.mdsinformaticki.model.Stock;
import nino.rs.mdsinformaticki.model.Value;
import nino.rs.mdsinformaticki.respository.StockRepository;
import nino.rs.mdsinformaticki.respository.ValueRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public boolean deleteStock(String name) {

        Stock stock = stockRepository.findByName(name);

        System.out.println(stock);

        if(!stockRepository.existsById(stock.getId()))
            return false;

        stockRepository.deleteById(stock.getId());
        return true;
    }

}
