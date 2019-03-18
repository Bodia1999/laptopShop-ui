package bohdan.papizhanskiy.laptops.controller;


import bohdan.papizhanskiy.laptops.dto.request.ProcessorFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.ProcessorRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.ProcessorResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/processor")
public class ProcessorController {

    @Autowired
    private ProcessorService processorService;


    @GetMapping
    public List<ProcessorResponse> findAll() {
        return processorService.findAll();
    }


    @PostMapping
    public ProcessorResponse save(@RequestBody ProcessorRequest processorRequest) {
        return processorService.save(processorRequest);
    }

    @DeleteMapping
    public void delete(Long id) throws WrongInputException {
        processorService.delete(id);
    }

    @PutMapping("/{id}")
    public ProcessorResponse update(@RequestBody ProcessorRequest processorRequest, @PathVariable Long id) throws WrongInputException {
        return processorService.update(processorRequest, id);
    }

    @PostMapping("/filter")
    public DataResponse<ProcessorResponse> findAllByFilter(@RequestBody ProcessorFilterRequest processorFilterRequest){
        return processorService.findByFilter(processorFilterRequest);
    }

    @PostMapping("/findOne")
    public ProcessorResponse findOne(@RequestParam Long id) throws WrongInputException{
        return new ProcessorResponse(processorService.findOne(id));
    }
}
