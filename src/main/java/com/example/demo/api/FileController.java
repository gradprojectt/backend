package com.example.demo.api;

import com.example.demo.domain.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@RestController
public class FileController {
    private final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService; // Assuming FileService is a service that handles files
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        if(file.isEmpty()) {
            /* 파일을 업로드 하지 않았을 경우 처리 */
        }

        String fileName = file.getOriginalFilename(); // Assuming you want to use the original filename

        String downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                        .path("/download/")
                                                        .path(fileName)
                                                        .toUriString();

        return new ResponseEntity<>(downloadURI, HttpStatus.OK);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<?> downloadFile(@RequestParam("fileName") String fileName, HttpServletRequest request) throws FileNotFoundException {

        Resource resource = fileService.loadFile(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}


