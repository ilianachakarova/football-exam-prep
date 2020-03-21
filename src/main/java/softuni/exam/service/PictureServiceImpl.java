package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.picture_dtos.PictureImportDto;
import softuni.exam.domain.dtos.picture_dtos.PictureImportRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;
import softuni.exam.util.XmlReader;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;


@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final XmlReader xmlReader;

    private final static String PICTURES_XML_PATH = "C:\\Users\\user l\\Desktop\\exam-prep\\src\\main\\resources\\files\\xml\\pictures.xml";
    //private final static String PICTURES_XML_PATH = System.getProperty("user.dir") + "/src/main/resources/files/xml/pictures.xml";

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlParser xmlParser, XmlReader xmlReader) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.xmlReader = xmlReader;
    }

    @Override
    public String importPictures() throws IOException, JAXBException {
        StringBuilder importResult = new StringBuilder();
        PictureImportRootDto pictureImportRootDto = this.xmlParser.unmarshalFromFile(PICTURES_XML_PATH, PictureImportRootDto.class);
        List<PictureImportDto> pictureImportDtos = pictureImportRootDto.getPictureImportDtoList();

        for (PictureImportDto pictureImportDto : pictureImportDtos) {
            Picture picture = this.pictureRepository.findByUrl(pictureImportDto.getUrl()).orElse(null);
            if(picture!=null){
                importResult.append("Picture invalid - it exists in the Database").append(System.lineSeparator());
                continue;
            }
            if (!this.validatorUtil.isValid(pictureImportDto)){
                importResult.append("Picture invalid").append(System.lineSeparator());
                continue;
            }
            picture = this.modelMapper.map(pictureImportDto, Picture.class);
            this.pictureRepository.saveAndFlush(picture);
            importResult.append("Successfully imported picture ").append(pictureImportDto.getUrl()).append(System.lineSeparator());
        }
       return importResult.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count()>0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return this.xmlReader.readFromFile(PICTURES_XML_PATH);
    }


}
