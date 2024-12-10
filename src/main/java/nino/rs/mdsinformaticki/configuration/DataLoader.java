package nino.rs.mdsinformaticki.configuration;

import nino.rs.mdsinformaticki.model.Stock;
import nino.rs.mdsinformaticki.model.Value;
import nino.rs.mdsinformaticki.respository.StockRepository;
import nino.rs.mdsinformaticki.respository.ValueRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ValueRepository valueRepository;

    @Override
    public void run(String... args) throws Exception {

        Path directory = Paths.get("src/main/resources/data");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*.csv")) {
            for (Path entry : stream) {
                System.out.println("Učitavam fajl: " + entry.getFileName());
                loadFromCSV(entry.toString());
            }
        }
    }

    public void loadFromCSV(String filePath) throws IOException {

        FileReader reader = new FileReader(Paths.get(filePath).toFile());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()  // Skip the header row
                .withIgnoreHeaderCase()
                .withTrim()
                .parse(reader);

        // Uzimanje imena stock-a iz naziva fajla
        String stockName = Paths.get(filePath).getFileName().toString().replace(".csv", "");

        // Proveriti da li postoji stock sa istim imenom
        Stock stock = stockRepository.findByName(stockName);
        if (stock == null) {
            // Ako stock ne postoji, kreiraj novi
            stock = new Stock();
            stock.setName(stockName);
            stock.setValues(new HashSet<>());
            stock.setMark(stockName.substring(0, 3).toUpperCase());
            stock.setFoundingDate(LocalDate.of(2021, 1, 1));
            stockRepository.save(stock);
        }


        for (CSVRecord record : records) {

            Value value = new Value();

            value.setDate(LocalDate.parse(record.get("Date")));
            value.setOpen(parseDouble(record.get("Open")));
            value.setHigh(parseDouble(record.get("High")));
            value.setLow(parseDouble(record.get("Low")));
            value.setClose(parseDouble(record.get("Close")));
            value.setAdjClose(parseDouble(record.get("Adj Close")));
            value.setVolume(parseLong(record.get("Volume")));

            value.setStock(stock);

            valueRepository.save(value);

        }
    }

    private double parseDouble(String value) {
        return value.equals("null") || value.isEmpty() ? 0.0 : Double.parseDouble(value);
    }

    private long parseLong(String value) {
        return value.equals("null") || value.isEmpty() ? 0 : Long.parseLong(value);
    }

}
