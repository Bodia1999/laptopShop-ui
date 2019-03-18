package bohdan.papizhanskiy.laptops.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "graphicCard")
public class GraphicCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String model;

    private String typeOfGraphicCard;

    private Integer volumeOfMemory;

    private String typeOfMemory;

    @OneToMany(mappedBy = "graphicCard")
    private List<Laptop> laptop;
}
