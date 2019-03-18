package bohdan.papizhanskiy.laptops.controller;

import bohdan.papizhanskiy.laptops.dto.request.CorpsFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.CorpsRequest;
import bohdan.papizhanskiy.laptops.dto.response.CorpsResponse;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.CorpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/corps")
public class CorpsController {

    @Autowired
    private CorpsService corpsService;

    @GetMapping
    public List<CorpsResponse> findAll() {
        return corpsService.findAll();
    }

    @PostMapping
    public CorpsResponse save(@RequestBody CorpsRequest corpsRequest) throws Exception {
        return corpsService.save(corpsRequest);
    }

    @DeleteMapping
    public void delete(Long id) throws WrongInputException {
        corpsService.delete(id);
    }

    @PutMapping("/{id}")
    public CorpsResponse update(@RequestBody CorpsRequest corpsRequest,@PathVariable Long id) throws Exception {
        return corpsService.update(corpsRequest, id);
    }

    @PostMapping("/filter")
    public DataResponse<CorpsResponse> findAllByFilter(@RequestBody CorpsFilterRequest corpsFilterRequest){
        return corpsService.findByFilter(corpsFilterRequest);
    }

    @PostMapping("/findOne")
    public CorpsResponse findOne (@RequestParam Long id) throws WrongInputException {
        return new CorpsResponse(corpsService.findOne(id));
    }



}
