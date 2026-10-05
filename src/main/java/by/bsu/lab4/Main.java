package by.bsu.lab4;

import by.bsu.lab4.creator.DiscCreator;
import by.bsu.lab4.entity.Disc;
import by.bsu.lab4.exception.CompositionException;
import by.bsu.lab4.service.DiscService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FILE_PATH = "src/main/resources/compositions.txt";

    public static void main(String[] args) {
        try {
            Disc disc = new DiscCreator().create("Mixed collection", FILE_PATH);
            DiscService service = new DiscService();
            LOGGER.info("Total duration: {} s", service.calculateTotalDuration(disc));

            service.sortByStyle(disc);
            LOGGER.info("After sorting by style: {}", disc.getCompositions());

            LOGGER.info("Compositions 200-400 s: {}", service.findByDuration(disc, 200, 400));
        } catch (CompositionException e) {
            LOGGER.error("Failed to create disc", e);
        }
    }
}