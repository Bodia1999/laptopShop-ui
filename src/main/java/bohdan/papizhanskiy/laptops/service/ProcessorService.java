package bohdan.papizhanskiy.laptops.service;

import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.request.ProcessorFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.ProcessorRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.dto.response.ProcessorResponse;
import bohdan.papizhanskiy.laptops.entity.Make;
import bohdan.papizhanskiy.laptops.entity.Processor;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.repository.ProcessorRepository;
import bohdan.papizhanskiy.laptops.specification.ProcessorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessorService {
    @Autowired
    private ProcessorRepository processorRepository;

    public ProcessorResponse save(ProcessorRequest processorRequest) {
        return new ProcessorResponse(processorRequestToProcessor(null, processorRequest));
    }

    public List<ProcessorResponse> findAll() {
        return processorRepository.findAll().stream().map(ProcessorResponse::new).collect(Collectors.toList());

    }

    public ProcessorResponse update(ProcessorRequest processorRequest, Long id) throws WrongInputException {
        return new ProcessorResponse(processorRequestToProcessor(findOne(id), processorRequest));
    }

    private Processor processorRequestToProcessor(Processor processor, ProcessorRequest processorRequest) {
        if (processor == null) {
            processor = new Processor();
        }
        processor.setName(processorRequest.getName());
        processor.setModel(processorRequest.getModel());
        processor.setWorkingFrequency(processorRequest.getWorkingFrequency());
        processor.setQuantityOfCores(processorRequest.getQuantityOfCores());

        return processorRepository.save(processor);
    }

    public void delete(Long id) throws WrongInputException {
        processorRepository.delete(findOne(id));
    }

    public Processor findOne(Long id) throws WrongInputException {
        return processorRepository.findById(id).orElseThrow(() -> new WrongInputException("Processor with " + id + " id wasn`t found"));
    }

    public DataResponse<ProcessorResponse> findAll(PaginationRequest paginationRequest) {
        Page<Processor> all = processorRepository.findAll(paginationRequest.mapToPageRequest());
        return new DataResponse<>(all.get().map(ProcessorResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

    public DataResponse<ProcessorResponse> findByFilter(ProcessorFilterRequest processorFilterRequest){
        Page<Processor> page = processorRepository.findAll( new ProcessorSpecification(processorFilterRequest),processorFilterRequest.getPagination().mapToPageRequest());

        return new DataResponse<>(page.get().map(ProcessorResponse::new ).collect(Collectors.toList()), page.getTotalPages(),page.getTotalElements());

    }
}
