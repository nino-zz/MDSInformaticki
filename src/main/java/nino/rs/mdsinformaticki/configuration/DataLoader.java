package nino.rs.mdsinformaticki.configuration;

import nino.rs.mdsinformaticki.model.Stock;
import nino.rs.mdsinformaticki.model.Value;
import nino.rs.mdsinformaticki.respository.StockRepository;
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

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StockRepository stockRepository;

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

        Stock stock = new Stock();
        stock.setName(Paths.get(filePath).getFileName().toString().replace(".csv", ""));
        stock.setValues(new ArrayList<>());
        stock.setMark(stock.getName().substring(0, 3).toUpperCase());
        stock.setFoundingDate(LocalDate.of(2021, 1, 1));

        for (CSVRecord record : records) {

            Value value = new Value();
            value.setStock(stock);  // Associate this value with the stock entity
            value.setDate(LocalDate.parse(record.get("Date")));

            if(record.get("Open").equals("null")) {
                value.setOpen(0.0);
            } else {
                value.setOpen(Double.parseDouble(record.get("Open")));
            }

            if(record.get("High").equals("null")) {
                value.setHigh(0.0);
            } else {
                value.setHigh(Double.parseDouble(record.get("High")));
            }

            if (record.get("Low").equals("null")) {
                value.setLow(0.0);
            } else {
                value.setLow(Double.parseDouble(record.get("Low")));
            }

            if(record.get("Close").equals("null")) {
                value.setClose(0.0);
            } else {
                value.setClose(Double.parseDouble(record.get("Close")));
            }

            if(record.get("Adj Close").equals("null")) {
                value.setAdjClose(0.0);
            } else {
                value.setAdjClose(Double.parseDouble(record.get("Adj Close")));
            }

            if(record.get("Volume").equals("null")) {
                value.setVolume(Long.parseLong(0 + ""));
            } else {
                value.setVolume(Long.parseLong(record.get("Volume")));
            }

            stock.getValues().add(value);
        }

        stockRepository.save(stock);
    }

}
