package softuni.exam.domain.dtos.teams_dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamImportDto {
    @XmlElement(name = "name")
    @NotNull(message = "Invalid team")
    @Size(min = 3,max = 20, message = "Invalid team")
    private String name;
    @XmlElement(name = "picture")
    @NotNull(message = "Invalid team")
    private TeamImportPictureDto teamImportPictureDto;

    public TeamImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamImportPictureDto getTeamImportPictureDto() {
        return teamImportPictureDto;
    }

    public void setTeamImportPictureDto(TeamImportPictureDto teamImportPictureDto) {
        this.teamImportPictureDto = teamImportPictureDto;
    }
}
