package by.bsu.lab4.factory;

public class CompositionFactoryProvider {

    private CompositionFactoryProvider() {
    }

    public static CompositionFactory getFactory(String type) {
        return switch (type) {
            case "SONG" -> new SongFactory();
            case "INSTRUMENTAL" -> new InstrumentalPieceFactory();
            case "CLASSICAL" -> new ClassicalWorkFactory();
            default -> throw new IllegalArgumentException("Unknown composition type: " + type);
        };
    }
}