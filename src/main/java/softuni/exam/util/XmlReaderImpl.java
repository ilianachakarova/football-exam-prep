package softuni.exam.util;

import java.io.*;

public class XmlReaderImpl implements XmlReader {
    @Override
    public String readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

        String line;
        while((line = reader.readLine())!=null){
            content.append(line).append(System.lineSeparator());
        }
        return content.toString().trim();
    }
}
