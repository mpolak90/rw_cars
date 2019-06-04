package pl.mateuszpolak.model;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class UploadImage {
    public static String upload(MultipartFile file) throws IOException {

        String fileFormat = "." + FilenameUtils.getExtension(file.getOriginalFilename());

        String filename;
        UUID uuid = UUID.randomUUID();

        try {

            System.out.println("Wygenerowano nr " + uuid);

            // filename = "/home/mateusz/auto/src/main/webapp/images/auctions/uploaded/upload_" + uuid.toString() + fileFormat;
            filename = "/opt/tomcat/webapps/ROOT/images/auctions/uploaded/upload_" + uuid.toString() + fileFormat;
            byte[] bytes = file.getBytes();

            File fsFile = new File(filename);

            fsFile.createNewFile();

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fsFile));
            stream.write(bytes);
            stream.close();
            System.out.println("File successfully uploaded");

            Path checking = new File(filename).toPath();
            String mimeType = Files.probeContentType(checking).substring(0,5);

            System.out.println(mimeType);

            if (mimeType.equals("image")) {
                System.out.println("Wszystko ok");
            } else {
                File danger = new File(filename);
                if (danger.delete()) {
                    System.out.println("Podejrzany plik został usunięty");
                }
                return "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png";
            }
        } catch (Exception e) {
            System.out.println("File has not been uploaded: " + e);
            return "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png";
        }

        return "/images/auctions/uploaded/upload_" + uuid.toString() + fileFormat;
    }

    public static void deleteUnused(String delete) {
        try {
            File file = new File("/opt/tomcat/webapps/ROOT" + delete);
            if (file.exists()) {
                System.out.println(file.delete());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
