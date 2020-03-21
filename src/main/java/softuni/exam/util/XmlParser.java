package softuni.exam.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {
    public <T> T unmarshalFromFile(String filePath, Class<T>tClass) throws JAXBException, FileNotFoundException;
}
