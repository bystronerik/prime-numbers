package org.example.primenumbers.command;

import org.apache.poi.ss.usermodel.*;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import com.github.pjfanning.xlsx.StreamingReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

@Command
public class RunCommand implements Callable<Integer> {

    @Option(names = {"-f", "--file"}, required = true)
    private String file;

    public Integer call() {
        try {
            InputStream is = new FileInputStream(file);
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(10)
                    .bufferSize(1024)
                    .open(is);

            for (Row r : workbook.getSheetAt(0)) {
                final Cell c = r.getCell(1);
                if (!c.getCellType().equals(CellType.BLANK)) {
                    try {
                        final double baseValue = Double.parseDouble(c.getStringCellValue());
                        if (1 < baseValue && baseValue % 1 == 0) {
                            final Integer value = (int) baseValue;

                            if (value == 2) {
                                System.out.println(value);
                            }

                            if ((value % 2) != 0 && (value % 3) != 0 && (value % 5) != 0) {
                                boolean prime = true;
                                for (int i=7;i<value;i++) {
                                    if (value % i == 0) {
                                        prime = false;
                                        break;
                                    }
                                }

                                if (prime) {
                                    System.out.println(value);
                                }
                            }
                        }
                    } catch (NumberFormatException ignored) { }
                }
            }

            return 0;
        } catch (IOException exception) {
            return 1;
        }
    }

}
