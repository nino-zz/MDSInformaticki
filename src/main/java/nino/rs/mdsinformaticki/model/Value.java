package nino.rs.mdsinformaticki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "vrednosti")
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = true)
    @JsonIgnore
    private Stock stock;

    // Datum
    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Početna cena
    @Column(name = "open", nullable = false)
    private Double open;

    // Najviša cena
    @Column(name = "high", nullable = false)
    private Double high;

    // Najniža cena
    @Column(name = "low", nullable = false)
    private Double low;

    // Cena zatvaranja
    @Column(name = "close", nullable = false)
    private Double close;

    // Prilagođena cena zatvaranja
    @Column(name = "adj_close", nullable = false)
    private Double adjClose;

    // Obim trgovine
    @Column(name = "volume", nullable = false)
    private Long volume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}
