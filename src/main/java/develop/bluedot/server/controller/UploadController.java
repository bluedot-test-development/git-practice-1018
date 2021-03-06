package develop.bluedot.server.controller;

import develop.bluedot.server.component.Uploader;
import develop.bluedot.server.service.DotVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class UploadController {

    private final Uploader uploader;

    @Autowired
    private DotVideoService dotVideoService;

    @PostMapping("")
    public ApiResponse<Integer,String> upload(@RequestParam("data") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {

        ApiResponse<Integer,String> returnData = ApiResponse.of(uploader.upload(file, "static"));

        String videoURL = returnData.getData();

        dotVideoService.create(videoURL,userId);

        return returnData;
    }

    @GetMapping("/{id}")
    public Optional<Object> getAllDotVideo(@PathVariable Long id){
        return dotVideoService.getAllDotVideo(id);
    }

}
