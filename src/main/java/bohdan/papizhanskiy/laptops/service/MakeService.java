package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.MakeRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.LaptopRepository;
import bohdan.papizhanskiy.laptops.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MakeService {

    @Autowired
    private MakeRepository makeRepository;


    public MakeResponse save(MakeRequest makeRequest) throws Exception {
        return new MakeResponse(makeRequestToMake(makeRequest, null));
    }

    public List<MakeResponse> findAll() {
        return makeRepository.findAll().stream().map(MakeResponse::new).collect(Collectors.toList());
    }


    public MakeResponse update(MakeRequest makeRequest, Long id) throws Exception {
        return new MakeResponse(makeRequestToMake(makeRequest, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        makeRepository.delete(findOne(id));
    }

    private Make makeRequestToMake(MakeRequest makeRequest, Make make) throws Exception {
        if (make == null) {
            make = new Make();
        }

        make.setName(makeRequest.getName());
        return makeRepository.save(make);
    }

    public Make findOne(Long id) throws WrongInputException {
        return makeRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Make with id " + id + " not exists"));
    }

//    public DataResponse<MakeResponse> findAll(PaginationRequest pagination) {
//        PageRequest pageRequest = PageRequest.of(pagination.getPage(), pagination.getSize(),
//                pagination.getSortRequest().getDirection(), pagination.getSortRequest().getFieldName());
//        Page<Make> all = makeRepository.findAll(pageRequest);
//        return new DataResponse<>(all.getContent().stream().map(MakeResponse::new).collect(Collectors.toList()),
//                all.getTotalPages(), all.getTotalElements());
//    }

    public DataResponse<MakeResponse> findAll(PaginationRequest paginationRequest) {
        Page<Make> all = makeRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(MakeResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

//    public DataResponse<MakeResponse> findAllByName(PaginationRequest paginationRequest, MakeRequest makeRequest) {
//
//        Page<Make> byName = makeRepository.findAllByNameLike('%'+makeRequest.getName()+'%', paginationRequest.mapToPageRequest());
//        return new DataResponse<>(byName.get().map(MakeResponse::new).collect(Collectors.toList()),
//                byName.getTotalPages(), byName.getTotalElements());
//    }

}
