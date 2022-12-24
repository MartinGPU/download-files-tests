package com.marat;

import com.codeborne.selenide.Selenide;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

public class SimpleFileDownloadTests {

    @Test
    public void simpleDownload() throws Exception {
        Selenide.open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();
//        String s;
//
//        try (InputStream is = new FileInputStream(downloadedFile)) {
//            s = new String (is.readAllBytes(), "UTF-8");
//        }

        String s1 = FileUtils.readFileToString(downloadedFile, "UTF-8");
        Assertions.assertTrue(s1.contains("This repository is the home of the next generation of JUnit"));
    }
}
