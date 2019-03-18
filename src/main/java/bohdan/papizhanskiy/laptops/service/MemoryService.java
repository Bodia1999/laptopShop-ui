package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.GraphicCardFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.MemoryFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.MemoryRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.GraphicCardResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.dto.response.MemoryResponse;
import bohdan.papizhanskiy.laptops.entity.GraphicCard;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.entity.Memory;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.MemoryRepository;
import bohdan.papizhanskiy.laptops.specification.GraphicCardSpecification;
import bohdan.papizhanskiy.laptops.specification.MemorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoryService {

    @Autowired
    private MemoryRepository memoryRepository;

    public MemoryResponse save(MemoryRequest memoryRequest) {
        return new MemoryResponse(memoryRequestToMemory(memoryRequest, null));
    }

    public MemoryResponse update(MemoryRequest memoryRequest, Long id) throws WrongInputException {
        return new MemoryResponse(memoryRequestToMemory(memoryRequest, findOne(id)));
    }

    private Memory memoryRequestToMemory(MemoryRequest memoryRequest, Memory memory) {
        if (memory == null) {
            memory = new Memory();
        }
        memory.setName(memoryRequest.getName());
        memory.setTypeOfMemory(memoryRequest.getTypeOfMemory());
        memory.setVolumeOfMemory(memoryRequest.getVolumeOfMemory());
        return memoryRepository.save(memory);
    }

    public void delete(Long id) throws WrongInputException {
        memoryRepository.delete(findOne(id));
    }

    public List<MemoryResponse> findAll() {
        return memoryRepository.findAll().stream().map(MemoryResponse::new).collect(Collectors.toList());
    }

    public Memory findOne(Long id) throws WrongInputException {
        return memoryRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Memory with id " + id + " not exists"));
    }

    public DataResponse<MemoryResponse> findAll(PaginationRequest paginationRequest) {
        Page<Memory> all = memoryRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(MemoryResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

    public DataResponse<MemoryResponse> findByFilter(MemoryFilterRequest memoryFilterRequest){
        Page<Memory> page = memoryRepository.findAll( new MemorySpecification(memoryFilterRequest),memoryFilterRequest.getPagination().mapToPageRequest());

        return new DataResponse<>(page.get().map(MemoryResponse::new ).collect(Collectors.toList()), page.getTotalPages(),page.getTotalElements());

    }
}