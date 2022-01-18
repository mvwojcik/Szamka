package pl.mvwojcik.vitamins.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.mvwojcik.model.Unit;
import pl.mvwojcik.vitamins.data.VitaminType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "VITAMIN")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vitamin {

    @Id
    @GeneratedValue(generator = "vitamin_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "vitamin_generator", sequenceName = "vitamin_seq", allocationSize = 5, initialValue = 50)
    @Column(name = "id", updatable = false, unique = true)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private VitaminType type;
    @Enumerated(EnumType.STRING)
    private Unit scale;
    private Double dailyRequirement;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vitamin vitamin = (Vitamin) o;
        return Objects.equals(id, vitamin.id) && Objects.equals(name, vitamin.name) && type == vitamin.type && scale == vitamin.scale && Objects.equals(dailyRequirement, vitamin.dailyRequirement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, scale, dailyRequirement);
    }
}
