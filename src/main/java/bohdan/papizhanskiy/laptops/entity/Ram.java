package bohdan.papizhanskiy.laptops.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ram")
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String typeOfMemory;

    private Integer volumeOfMemory;

    private Integer workingFrequency;

    @OneToMany(mappedBy = "ram")
    private List<Laptop> laptop;


}
