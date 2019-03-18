package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.request.RamFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.RamRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.dto.response.RamResponse;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.entity.Ram;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.RamRepository;
import bohdan.papizhanskiy.laptops.specification.RamSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RamService {

    @Autowired
    private RamRepository ramRepository;

    public List<RamResponse> findAll() {
        return ramRepository.findAll().stream().map(RamResponse::new).collect(Collectors.toList());
    }


    public RamResponse save(RamRequest ramRequest) throws Exception {
        return new RamResponse(ramRequestToRam(ramRequest, null));

    }

    public RamResponse update(RamRequest ramRequest, Long id) throws Exception {
        return new RamResponse(ramRequestToRam(ramRequest, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        ramRepository.delete(findOne(id));
    }

    private Ram ramRequestToRam(RamRequest ramRequest, Ram ram) throws Exception {
        if (ram == null) {
            ram = new Ram();
        }
        ram.setName(ramRequest.getName());
        ram.setTypeOfMemory(ramRequest.getTypeOfMemory());
        ram.setVolumeOfMemory(ramRequest.getVolumeOfMemory());
        ram.setWorkingFrequency(ramRequest.getWorkingFrequency());
        return ramRepository.save(ram);
    }

    public Ram findOne(Long id) throws WrongInputException {
        return ramRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Ram with id " + id + " not exists"));
    }

    public DataResponse<RamResponse> findAll(PaginationRequest paginationRequest) {
        Page<Ram> all = ramRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(RamResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

    public DataResponse<RamResponse> findByFilter(RamFilterRequest ramFilterRequest){
        Page<Ram> page = ramRepository.findAll( new RamSpecification(ramFilterRequest),ramFilterRequest.getPagination().mapToPageRequest());

        return new DataResponse<>(page.get().map(RamResponse::new ).collect(Collectors.toList()), page.getTotalPages(),page.getTotalElements());

    }
}
