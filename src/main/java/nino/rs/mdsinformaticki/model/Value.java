package nino.rs.mdsinformaticki.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "value")
@Getter
@Setter
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
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

}
