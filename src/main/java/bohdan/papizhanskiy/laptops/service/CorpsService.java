package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.CorpsFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.CorpsRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.CorpsResponse;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.entity.Corps;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.CorpsRepository;
import bohdan.papizhanskiy.laptops.specification.CorpsSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorpsService {
    @Autowired
    private CorpsRepository corpsRepository;

    public CorpsResponse save(CorpsRequest corpsRequest) throws Exception {
        return new CorpsResponse(corpsRequestToCorps(corpsRequest, null));

    }

    public List<CorpsResponse> findAll() {
        return corpsRepository.findAll().stream().map(CorpsResponse::new).collect(Collectors.toList());
    }

    public CorpsResponse update(CorpsRequest corpsRequest, Long id) throws Exception {
        return new CorpsResponse(corpsRequestToCorps(corpsRequest, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        corpsRepository.delete(findOne(id));
    }

    private Corps corpsRequestToCorps(CorpsRequest corpsRequest, Corps corps) throws Exception {
        if (corps == null) {
            corps = new Corps();
        }
        corps.setDimensions(corpsRequest.getDimensions());
        corps.setColorOfCorps(corpsRequest.getColorOfCorps());
        corps.setWeight(corpsRequest.getWeight());
        corps.setMaterialOfCorps(corpsRequest.getMaterialOfCorps());
        return corpsRepository.save(corps);
    }

    public Corps findOne(Long id) throws WrongInputException {
        return corpsRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Corps with id " + id + " not exists"));
    }

    public DataResponse<CorpsResponse> findAll(PaginationRequest paginationRequest) {
        Page<Corps> all = corpsRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(CorpsResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

    public DataResponse<CorpsResponse> findByFilter(CorpsFilterRequest corpsFilterRequest){
        Page<Corps> page = corpsRepository.findAll( new CorpsSpecification(corpsFilterRequest),corpsFilterRequest.getPagination().mapToPageRequest());

        return new DataResponse<>(page.get().map(CorpsResponse::new ).collect(Collectors.toList()), page.getTotalPages(),page.getTotalElements());

    }
}
