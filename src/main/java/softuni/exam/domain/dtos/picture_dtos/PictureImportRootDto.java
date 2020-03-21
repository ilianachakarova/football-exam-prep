package softuni.exam.domain.dtos.picture_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureImportRootDto {
    @XmlElement(name = "picture")
    private List<PictureImportDto> pictureImportDtoList;

    public PictureImportRootDto() {
    }

    public List<PictureImportDto> getPictureImportDtoList() {
        return pictureImportDtoList;
    }

    public void setPictureImportDtoList(List<PictureImportDto> pictureImportDtoList) {
        this.pictureImportDtoList = pictureImportDtoList;
    }
}
