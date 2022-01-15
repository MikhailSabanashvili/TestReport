package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.FileInputStream;
import java.io.IOException;

public class XlsReader {
    public static HSSFWorkbook loadBook(String path) {
        POIFSFileSystem fs;
        HSSFWorkbook hssfWorkbook = null;
        try {
            fs = new POIFSFileSystem(new FileInputStream(path));
            hssfWorkbook = new HSSFWorkbook(fs);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return hssfWorkbook;
    }
}
