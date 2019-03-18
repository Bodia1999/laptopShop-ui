package bohdan.papizhanskiy.laptops.controller;

import bohdan.papizhanskiy.laptops.dto.request.RamFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.RamRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.RamResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.RamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/ram")
public class RamController {

    @Autowired
    private RamService ramService;


    @GetMapping
    public List<RamResponse> findAll() {
        return ramService.findAll();
    }

    @PostMapping
    public RamResponse save(@RequestBody RamRequest ramRequest) throws Exception {
        return ramService.save(ramRequest);
    }

    @DeleteMapping
    public void delete(Long id) throws WrongInputException {
        ramService.delete(id);
    }

    @PutMapping("/{id}")
    public RamResponse update(@RequestBody RamRequest ramRequest,@PathVariable Long id) throws Exception {
        return ramService.update(ramRequest, id);
    }

    @PostMapping("/filter")
    public DataResponse<RamResponse> findAllByFilter(@RequestBody RamFilterRequest ramFilterRequest){
        return ramService.findByFilter(ramFilterRequest);
    }

    @PostMapping("/findOne")
    public RamResponse findOne(@RequestParam Long id) throws WrongInputException{
        return new RamResponse(ramService.findOne(id));
    }
}
