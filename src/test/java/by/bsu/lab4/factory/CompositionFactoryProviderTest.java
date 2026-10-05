package by.bsu.lab4.factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;

public class CompositionFactoryProviderTest {

    @Test
    public void testGetSongFactory() {
        Assert.assertTrue(CompositionFactoryProvider.getFactory("SONG") instanceof SongFactory);
    }

    @Test
    public void testGetInstrumentalFactory() {
        Assert.assertTrue(CompositionFactoryProvider.getFactory("INSTRUMENTAL")
                instanceof InstrumentalPieceFactory);
    }

    @Test
    public void testGetClassicalFactory() {
        Assert.assertTrue(CompositionFactoryProvider.getFactory("CLASSICAL")
                instanceof ClassicalWorkFactory);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUnknownTypeThrowsException() {
        CompositionFactoryProvider.getFactory("JUNK");
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<CompositionFactoryProvider> constructor =
                CompositionFactoryProvider.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Assert.assertNotNull(constructor.newInstance());
    }
}
