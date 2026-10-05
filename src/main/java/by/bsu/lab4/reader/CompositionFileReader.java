package by.bsu.lab4.reader;

import by.bsu.lab4.exception.CompositionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CompositionFileReader {

    public List<String> readLines(String path) throws CompositionException {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new CompositionException("Cannot read file: " + path, e);
        }
    }
}