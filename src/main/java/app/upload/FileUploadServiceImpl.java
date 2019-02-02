package app.upload;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(fileName);
        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();

                // Creating the directory to store file
//                String rootPath = "C:/Users/Kasia/Downloads/PDbeta2-master";
                String rootPath = System.getProperty("user.dir");
                File dir = new File(rootPath + File.separator + "attachments");
                if (!dir.exists())
                    dir.mkdirs();

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String finalName = timestamp.getTime() + "." + extension;

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + finalName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                return finalName;
            } catch (Exception e) {
                return e.getMessage();
            }
        } else {
            return "You failed to upload " + fileName
                    + " because the file was empty.";
        }
    }
}
