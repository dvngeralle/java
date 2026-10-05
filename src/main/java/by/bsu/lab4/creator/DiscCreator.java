package by.bsu.lab4.creator;

import by.bsu.lab4.entity.Disc;
import by.bsu.lab4.entity.MusicComposition;
import by.bsu.lab4.exception.CompositionException;
import by.bsu.lab4.factory.CompositionFactory;
import by.bsu.lab4.factory.CompositionFactoryProvider;
import by.bsu.lab4.reader.CompositionFileReader;
import by.bsu.lab4.validator.CompositionValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiscCreator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SEPARATOR = ";";

    private final CompositionFileReader reader = new CompositionFileReader();
    private final CompositionValidator validator = new CompositionValidator();

    public Disc create(String discName, String filePath) throws CompositionException {
        Disc disc = new Disc(discName);
        for (String line : reader.readLines(filePath)) {
            String[] fields = line.split(SEPARATOR, -1);
            if (!validator.isValid(fields)) {
                LOGGER.warn("Invalid line skipped: {}", line);
                continue;
            }
            CompositionFactory factory = CompositionFactoryProvider.getFactory(fields[0]);
            MusicComposition composition = factory.create(fields);
            disc.addComposition(composition);
            LOGGER.debug("Composition created: {}", composition);
        }
        LOGGER.info("Disc '{}' created, compositions: {}", discName, disc.getCompositions().size());
        return disc;
    }
}