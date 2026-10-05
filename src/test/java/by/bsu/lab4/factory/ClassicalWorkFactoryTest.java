package by.bsu.lab4.factory;

import by.bsu.lab4.entity.ClassicalWork;
import by.bsu.lab4.entity.MusicComposition;
import by.bsu.lab4.entity.MusicStyle;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClassicalWorkFactoryTest {

    @Test
    public void testCreate() {
        String[] fields = "CLASSICAL;Moonlight Sonata;Daniel Barenboim;360;CLASSICAL;Beethoven;27"
                .split(";", -1);

        MusicComposition result = new ClassicalWorkFactory().create(fields);

        Assert.assertTrue(result instanceof ClassicalWork);
        ClassicalWork work = (ClassicalWork) result;
        Assert.assertEquals(work.getTitle(), "Moonlight Sonata");
        Assert.assertEquals(work.getArtist(), "Daniel Barenboim");
        Assert.assertEquals(work.getDurationSeconds(), 360);
        Assert.assertEquals(work.getStyle(), MusicStyle.CLASSICAL);
        Assert.assertEquals(work.getComposer(), "Beethoven");
        Assert.assertEquals(work.getOpusNumber(), 27);
    }
}
