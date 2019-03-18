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
@Table(name = "corps")
public class Corps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight;

    private String dimensions;

    private String colorOfCorps;

    private String materialOfCorps;

    @OneToMany(mappedBy = "corps")
    private List<Laptop> laptop;

}

