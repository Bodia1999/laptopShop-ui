package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.request.RamRequest;
import bohdan.papizhanskiy.laptops.dto.request.ScreenFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.ScreenRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.dto.response.RamResponse;
import bohdan.papizhanskiy.laptops.dto.response.ScreenResponse;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.entity.Ram;
import bohdan.papizhanskiy.laptops.entity.Screen;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.ScreenRepository;
import bohdan.papizhanskiy.laptops.specification.ScreenSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    public ScreenResponse save(ScreenRequest screenRequest) throws Exception {
        return new ScreenResponse(screenRequestToScreen(screenRequest, null));

    }

    public List<ScreenResponse> findAll() {
        return screenRepository.findAll().stream().map(ScreenResponse::new).collect(Collectors.toList());
    }

    public ScreenResponse update(ScreenRequest screenRequest, Long id) throws Exception {
        return new ScreenResponse(screenRequestToScreen(screenRequest, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        screenRepository.delete(findOne(id));
    }

    private Screen screenRequestToScreen(ScreenRequest screenRequest, Screen screen) throws Exception {
        if (screen == null) {
            screen = new Screen();
        }
        screen.setType(screenRequest.getType());
        screen.setResolution(screenRequest.getResolution());
        screen.setSize(screenRequest.getSize());
        return screenRepository.save(screen);
    }

    public Screen findOne(Long id) throws WrongInputException {
        return screenRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Screen with id " + id + " not exists"));
    }

    public DataResponse<ScreenResponse> findAll(PaginationRequest paginationRequest) {
        Page<Screen> all = screenRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(ScreenResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

    public DataResponse<ScreenResponse> findByFilter(ScreenFilterRequest screenFilterRequest){
        Page<Screen> all = screenRepository.findAll(new ScreenSpecification(screenFilterRequest), screenFilterRequest.getPagination().mapToPageRequest());
        return new DataResponse<>(all.get().map(ScreenResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }
}
