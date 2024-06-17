package com.smartpoke.api.common.ImageStorage;

import com.smartpoke.api.common.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageStorageService {

    private static final Path rootLocation = Paths.get("upload-dir");

    public ImageStorageService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar el almacenamiento", e);
        }
    }

    public static byte[] dowloadImage(String data) {
        try {
            if (data!= null && data.startsWith("FILE:")) {
                return downloadFileFromStorage((data.substring(5)));
            } else {
                return downloadImageFromUrl(data);
            }
        }catch (IOException e){
            throw new ResourceNotFoundException("Image non available");
        }
    }

    public static String storeImage(byte[] fileBytes) {
        return storeImage(fileBytes,"");
    }

    public static String storeImage(byte[] fileBytes, String originalFilename) {
        String filename = UUID.randomUUID().toString() + "_" + originalFilename;
        Path destinationFile;
        try {
            if (fileBytes == null || fileBytes.length == 0) {
                throw new RuntimeException("No se puede almacenar un archivo vacío");
            }
            destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new RuntimeException("No se pu ede almacenar el archivo fuera del directorio actual");
            }
            Files.write(destinationFile, fileBytes);
        } catch (IOException e) {
            throw new RuntimeException("Error al almacenar el archivo", e);
        }

        return "FILE:"+ destinationFile.toUri();
    }

    private static byte[] downloadImageFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int n;

            while ((n = in.read(buffer)) != -1) {
                baos.write(buffer, 0, n);
            }

            return baos.toByteArray();
        }
    }

    private static byte[] downloadFileFromStorage(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            if (Files.exists(file) && Files.isReadable(file)) {
                return Files.readAllBytes(file);
            } else {
                throw new RuntimeException("Archivo no encontrado o no legible: " + filename);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + filename, e);
        }
    }

}
