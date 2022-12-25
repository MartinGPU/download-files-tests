package com.marat;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selenide.$;

public class SimpleFileDownloadTests {
    static final String PATH_TO_DWD = "download";

    @AfterAll
    static void releaseFiles() throws IOException {
        FileUtils.cleanDirectory(new File(PATH_TO_DWD));
    }

    @Test
    public void simpleDownload() throws Exception {
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = PROXY;
        Configuration.downloadsFolder = PATH_TO_DWD;

        Selenide.open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();

        String s1 = FileUtils.readFileToString(downloadedFile, "UTF-8");
        Assertions.assertTrue(s1.contains("This repository is the home of the next generation of JUnit"));
    }
}
