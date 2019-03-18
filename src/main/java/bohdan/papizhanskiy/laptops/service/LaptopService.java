package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.LaptopFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.LaptopRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.LaptopResponse;
import bohdan.papizhanskiy.laptops.entity.Laptop;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.GraphicCardRepository;
import bohdan.papizhanskiy.laptops.repository.LaptopRepository;
import bohdan.papizhanskiy.laptops.specification.LaptopSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private GraphicCardRepository graphicCardRepository;

    @Autowired
    private MakeService makeService;

    @Autowired
    private GraphicCardService graphicCardService;

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private MemoryService memoryService;

    @Autowired
    private RamService ramService;

    @Autowired
    private ScreenService screenService;

    @Autowired
    private CorpsService corpsService;


    public LaptopResponse save(LaptopRequest laptopRequest) throws WrongInputException {
        return new LaptopResponse(laptopRequestToLaptop(null, laptopRequest));
    }

    private Laptop laptopRequestToLaptop(Laptop laptop, LaptopRequest laptopRequest) throws WrongInputException {
        if (laptop == null) {
            laptop = new Laptop();
        }
        laptop.setModel(laptopRequest.getModel());
        laptop.setMake(makeService.findOne(laptopRequest.getMakeId()));
        laptop.setMainImagePath(laptopRequest.getImageDirection());
        laptop.setDescriptionImagePath1(laptopRequest.getDescriptionImagePath1());
        laptop.setDescriptionImagePath2(laptopRequest.getDescriptionImagePath2());
        laptop.setDescriptionImagePath3(laptopRequest.getDescriptionImagePath3());
        laptop.setPrice(laptopRequest.getPrice());
        laptop.setDescription(laptopRequest.getDescription());
        laptop.setGraphicCard(graphicCardService.findOne(laptopRequest.getGraphicCardId()));
        laptop.setRam(ramService.findOne(laptopRequest.getRamId()));
        laptop.setCorps(corpsService.findOne(laptopRequest.getCorpsId()));
        laptop.setMemory(memoryService.findOne(laptopRequest.getMemoryId()));
        laptop.setProcessor(processorService.findOne(laptopRequest.getProcessorId()));
        laptop.setScreen(screenService.findOne(laptopRequest.getScreenId()));
        laptop.setAvailabilityOfWIFI(laptopRequest.getAvailabilityOfWIFI());
        laptop.setAvailabilityOfAUX(laptopRequest.getAvailabilityOfAUX());
        laptop.setAvailabilityOfBluetooth(laptopRequest.getAvailabilityOfBluetooth());
        laptop.setAvailabilityOfHDMI(laptopRequest.getAvailabilityOfHDMI());
        laptop.setAvailabilityOfLAN(laptopRequest.getAvailabilityOfLAN());
        laptop.setAvailabilityOfUSBSecondGeneration(laptopRequest.getAvailabilityOfUSBSecondGeneration());
        laptop.setAvailabilityOfUSBThirdGeneration(laptopRequest.getAvailabilityOfUSBThirdGeneration());
        laptop.setAvailabilityOfUSBTypeC(laptopRequest.getAvailabilityOfUSBTypeC());
        return laptopRepository.save(laptop);
    }

    public LaptopResponse update(Long id, LaptopRequest laptopRequest) throws WrongInputException {
        return new LaptopResponse(laptopRequestToLaptop(findOne(id), laptopRequest));
    }

    public Laptop findOne(Long id) throws WrongInputException {
        return laptopRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Laptop with id " + id + " not exists"));
    }

    public void delete(Long id) throws WrongInputException {
        laptopRepository.delete(findOne(id));
    }

    public List<LaptopResponse> findAll() {
        return laptopRepository.findAll().stream().map(LaptopResponse::new).collect(Collectors.toList());
    }

    public DataResponse<LaptopResponse> findAll(PaginationRequest paginationRequest) {
        Page<Laptop> all = laptopRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(LaptopResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }


    public DataResponse<LaptopResponse> findByFilter(LaptopFilterRequest laptopFilterRequest) {
        Page<Laptop> page = laptopRepository.findAll(new LaptopSpecification(laptopFilterRequest), laptopFilterRequest.getPagination().mapToPageRequest());
//        Page<GraphicCard> all = graphicCardRepository.findAll(new GraphicCardSpecification(laptopFilterRequest.getGraphicCardFilterRequest()), laptopFilterRequest.getGraphicCardFilterRequest().getPagination().mapToPageRequest());
//List<Page> pages = new ArrayList<>();
//pages.add(page);
//pages.add(all);
        return new DataResponse<>(page.get().map(LaptopResponse::new).collect(Collectors.toList()), page.getTotalPages(), page.getTotalElements());
    }
//    public List<LaptopResponse> findAll(Long id){
//        return laptopRepository.findAllById(id).stream().map(LaptopResponse::new).collect(Collectors.toList());
//    }

//    public List<LaptopResponse> findAllByMake(LaptopRequest laptopRequest){
//        return laptopRepository.findAllByMake(laptopRequest.getMake()).stream().map(LaptopResponse::new).collect(Collectors.toList());
//    }

}
