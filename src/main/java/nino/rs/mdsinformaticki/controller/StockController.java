package nino.rs.mdsinformaticki.controller;

import nino.rs.mdsinformaticki.model.Stock;
import nino.rs.mdsinformaticki.request.StockReq;
import nino.rs.mdsinformaticki.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> get() {

        Set<String> seenNames = new HashSet<>();

        stockService.getStocks().stream()
                .filter(stock -> seenNames.add(stock.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(seenNames);

    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addStock(@RequestBody Stock stock) {
        return ResponseEntity.ok((stockService.addStock(stock)) ? "Stock added" : "Stock already exists");
    }


    @DeleteMapping(value = "/delete/{name}")
    public String delete(@PathVariable String name) {
        if(stockService.deleteStock(name))
            return "Stock deleted";
        return "Stock not found";
    }

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> infoStock(@RequestBody StockReq stockReq) {
        return ResponseEntity.ok(stockService.info(stockReq));
    }

}