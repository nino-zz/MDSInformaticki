package nino.rs.mdsinformaticki.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class StockReq {

    public LocalDate from;
    public LocalDate to;
    public String name;


    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
