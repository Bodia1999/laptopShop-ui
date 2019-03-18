package bohdan.papizhanskiy.laptops.controller;

import bohdan.papizhanskiy.laptops.dto.request.FileRequest;
import bohdan.papizhanskiy.laptops.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@CrossOrigin
@RestController
@RequestMapping("/photo")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestBody FileRequest fileRequest) throws IOException {
        String filePath = fileService.saveFile(fileRequest);
        return filePath;
    }
}
