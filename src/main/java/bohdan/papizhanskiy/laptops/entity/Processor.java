package bohdan.papizhanskiy.laptops.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "processor")
public class Processor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String model;

    private Integer workingFrequency;

    private Integer quantityOfCores;

    @OneToMany(mappedBy = "processor")

    private List<Laptop> laptop;


}
