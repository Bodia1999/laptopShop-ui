package bohdan.papizhanskiy.laptops.controller;

import bohdan.papizhanskiy.laptops.dto.request.MakeRequest;
import bohdan.papizhanskiy.laptops.dto.request.PaginationRequest;
import bohdan.papizhanskiy.laptops.dto.response.DataResponse;
import bohdan.papizhanskiy.laptops.dto.response.MakeResponse;
import bohdan.papizhanskiy.laptops.exception.WrongInputException;
import bohdan.papizhanskiy.laptops.service.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/make")
public class MakeController {

    @Autowired
    private MakeService makeService;

    @GetMapping
    public List<MakeResponse> findAll() {
        return makeService.findAll();
    }


    @PostMapping
    public MakeResponse save(@RequestBody MakeRequest makeRequest) throws Exception {
        return makeService.save(makeRequest);
    }

    @PostMapping("/findOne")
    public MakeResponse findOne(@RequestParam Long id) throws WrongInputException {
        return new MakeResponse(makeService.findOne(id));
    }

    @PostMapping("/page")
    public DataResponse<MakeResponse> getPage(@RequestBody PaginationRequest paginationRequest) {
        return makeService.findAll(paginationRequest);
    }

    @PutMapping("/{id}")
    public MakeResponse update(@RequestBody MakeRequest makeRequest, @PathVariable Long id) throws Exception {
        return makeService.update(makeRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws WrongInputException {
        makeService.delete(id);
    }

//    @PostMapping("/filter")
//    public DataResponse<MakeResponse> findAllByName(@RequestBody MakeRequest makeRequest,@RequestBody PaginationRequest paginationRequest) {
//        return makeService.findAllByName(paginationRequest, makeRequest);
//    }
}
