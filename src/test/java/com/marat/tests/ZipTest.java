package com.marat.tests;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTest {

    @Test
    public void zipTest() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String entryAsString = null;
        try (ZipInputStream stream = new ZipInputStream(classLoader.getResourceAsStream("zip/sample-zip-file.zip"))) {
            ZipEntry entry;
            while((entry = stream.getNextEntry()) != null) {
                entryAsString = IOUtils.toString(stream, StandardCharsets.UTF_8);
            }
           Assertions.assertTrue(entryAsString.contains("Can you please add audio samples with text you've converted? I'd love to see the end results."));
        }
    }
}
