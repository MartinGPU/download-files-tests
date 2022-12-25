package com.marat;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class XlsTest {

    @Test
    public void simpleXlsTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("xlsx_test.xlsx");
        XLS xlsFile = new XLS(stream);
        Assertions.assertEquals("гор.блюдо", xlsFile.excel.getSheetAt(1).getRow(3).getCell(1).getStringCellValue());
    }

    @Test
    public void csvTest() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("table.csv");
        List<String[]> result;
        try (CSVReader reader = new CSVReader(new InputStreamReader(stream))) {
            result = reader.readAll();
        }
        assertThat(result).contains(
                new String[]{"title", "author"},
                new String[]{"War and peace", "Tolstoy"}
        );
    }
}