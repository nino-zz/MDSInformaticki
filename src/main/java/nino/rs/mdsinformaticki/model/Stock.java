package nino.rs.mdsinformaticki.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "stock")
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // Naziv kompanije
    @Column(name = "name", nullable = false)
    private String name;

    // Oznaka kompanije
    @Column(name = "mark", nullable = false)
    private String mark;

    // Datum nastanka kompanije
    @Column(name = "founding_date", nullable = false)
    private LocalDate foundingDate;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Value> values;
}
