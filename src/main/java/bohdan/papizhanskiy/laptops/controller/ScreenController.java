package bohdan.papizhanskiy.laptops.controller;


import bohdan.papizhanskiy.laptops.dto.request.ScreenFilterRequest;
import bohdan.papizhanskiy.laptops.dto.request.ScreenRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.ScreenResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/screen")
public class ScreenController {

    @Autowired
    private ScreenService screenService;


    @GetMapping
    public List<ScreenResponse> findAll() {
        return screenService.findAll();
    }


    @PostMapping
    public ScreenResponse save(@RequestBody ScreenRequest screenRequest) throws Exception {
        return screenService.save(screenRequest);
    }

    @DeleteMapping
    public void delete(Long id) throws WrongInputException {
        screenService.delete(id);
    }

    @PutMapping("/{id}")
    public ScreenResponse update(@RequestBody ScreenRequest screenRequest,@PathVariable Long id) throws Exception {
        return screenService.update(screenRequest, id);
    }

    @PostMapping("/filter")
    public DataResponse<ScreenResponse> findAllByFilter(@RequestBody ScreenFilterRequest screenFilterRequest){
        return screenService.findByFilter(screenFilterRequest);
    }

    @PostMapping("/findOne")
    public ScreenResponse findOne(@RequestParam Long id) throws WrongInputException{
        return new ScreenResponse(screenService.findOne(id));
    }
}
