package app.controller;

import app.upload.FileUploadService;
import app.upload.FileUploadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/upload/")
public class UploadController {

    @Autowired
    FileUploadService fileUploadService;

    public UploadController() {
        fileUploadService = new FileUploadServiceImpl();
    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFileHandler(
            @RequestParam("file") MultipartFile file
    ) {
        return fileUploadService.uploadFile(file);
    }
}
