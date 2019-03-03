package snor.test.Utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OpenCsvUtils {

    public static void CSVWriter(String path, List<String[]> list) throws Exception{
        File csv = new File(path);
        if (!csv.exists()) csv.createNewFile();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csv),"UTF-8"),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);

        writer.writeAll(list);
        writer.flush();
        writer.close();
    }

    public static Iterator CSVRead(String path) throws Exception {
        String srcPath = path;
        String charset = "utf-8";
        CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(new File(srcPath)), charset))).build();
        Iterator<String[]> iterator = csvReader.iterator();
        while (iterator.hasNext()) {
            Arrays.stream(iterator.next()).forEach(System.out::print);
            System.out.println();
        }
        return iterator;
    }

}
