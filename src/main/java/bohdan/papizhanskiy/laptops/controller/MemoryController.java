package bohdan.papizhanskiy.laptops.controller;

import bohdan.papizhanskiy.laptops.dto.request.GraphicCardFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.MemoryFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.MemoryRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.GraphicCardResponse;
import bohdan.papizhanskiy.laptops.dto.response.MemoryResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;


    @GetMapping
    public List<MemoryResponse> findAll() {
        return memoryService.findAll();
    }

    @PostMapping("/filter")
    public DataResponse<MemoryResponse> findAllByFilter(@RequestBody MemoryFilterRequest memoryFilterRequest){
        return memoryService.findByFilter(memoryFilterRequest);
    }

    @PostMapping
    public MemoryResponse save(@RequestBody MemoryRequest memoryRequest) {
        return memoryService.save(memoryRequest);
    }

    @PutMapping("/{id}")
    public MemoryResponse update(@RequestBody MemoryRequest memoryRequest, @PathVariable Long id) throws WrongInputException {
        return memoryService.update(memoryRequest, id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) throws WrongInputException {
        memoryService.delete(id);
    }

    @PostMapping("/findOne")
    public MemoryResponse findOne (@RequestParam Long id) throws WrongInputException {
        return new MemoryResponse(memoryService.findOne(id));
    }
}
