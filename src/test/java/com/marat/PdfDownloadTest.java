package com.marat;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PdfDownloadTest {

    @Test
    public void junitPdfTest() throws IOException {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownloadedFile = $(byText("PDF download")).download();
        PDF parsedPdf = new PDF(pdfDownloadedFile);
        assertThat(parsedPdf.text).contains("Declarative Extension Registration");
    }
}
