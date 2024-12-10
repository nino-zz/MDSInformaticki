package nino.rs.mdsinformaticki.controller;

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
                .filter(stock -> seenNames.add(stock.getName())) // Dodaje samo ako ime nije već viđeno
                .collect(Collectors.toList());

        return ResponseEntity.ok(seenNames);

    }

    @DeleteMapping(value = "/delete/{name}")
    public String delete(@PathVariable String name) {
        stockService.deleteStock(name);
        return "Stock deleted";
    }

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> infoStock(@RequestBody StockReq stockReq) {

        return ResponseEntity.ok(stockService.info(stockReq));
    }

}