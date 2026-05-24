package utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class FileUtils {

    private FileUtils() {}

    public static String getTestResourcePath(String fileName) {
        try {
            URL resource = FileUtils.class.getClassLoader().getResource(fileName); //ClassLoader przeszukuje automatycznie folder resources
            if (resource == null) {
                throw new IllegalArgumentException("Nie znaleziono pliku w zasobach: " + fileName);
            }
            File file = Paths.get(resource.toURI()).toFile(); //Zamienia URL zasobu na prawidłową ścieżkę systemową (Path) i plik (File)
            return file.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas pobierania ścieżki pliku: " + fileName, e);
        }
    }
}
