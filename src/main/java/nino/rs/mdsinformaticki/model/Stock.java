package nino.rs.mdsinformaticki.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // Naziv kompanije
    @Column(name = "name", nullable = true)
    private String name;

    // Oznaka kompanije
    @Column(name = "mark", nullable = true)
    private String mark;

    // Datum nastanka kompanije
    @Column(name = "founding_date", nullable = true)
    private LocalDate foundingDate;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Value> values;

    public Set<Value> getValues() {
        return values;
    }

    public void setValues(Set<Value> values) {
        this.values = values;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }
}
