package core.rybina.database.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.image.bucket:src/main/java/core/rybina/images}")
    private final String bucket;

    @SneakyThrows
    public void upload(String imagePath, InputStream content) {
        Path path = Path.of(bucket, imagePath);

        try (content) {
            Files.createDirectories(path.getParent());
            Files.write(path, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
}
