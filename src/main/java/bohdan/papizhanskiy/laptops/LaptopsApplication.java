package bohdan.papizhanskiy.laptops;

import bohdan.papizhanskiy.laptops.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class LaptopsApplication {

    @Autowired
    private CorpsRepository corpsRepository;
    @Autowired
    private GraphicCardRepository graphicCardRepository;
    @Autowired
    private LaptopRepository laptopRepository;
//    @Autowired
//    private MakeRepository makeRepository;
    @Autowired
    private MemoryRepository memoryRepository;
    @Autowired
    private ProcessorRepository processorRepository;
    @Autowired
    private RamRepository ramRepository;
    @Autowired
    private ScreenRepository screenRepository;

    public static void main(String[] args) {
        SpringApplication.run(LaptopsApplication.class, args);
    }

    @PostConstruct
    public void init(){
//        Make make = new Make();
//        make.setName("FX705GE");
//
//        makeRepository.save(make);

       // makeRepository.findAllByName("FX705GE").forEach(System.out::println);
        //makeRepository.findAllByNameLike("vivo").forEach(System.out::println);


    }
}

