package softuni.exam.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.teams_dtos.TeamImportDto;
import softuni.exam.domain.dtos.teams_dtos.TeamImportRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;
import softuni.exam.util.XmlReader;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlReader xmlReader;

    private final static String TEAMS_FILE_PATH = "C:\\Users\\user l\\Desktop\\exam-prep\\src\\main\\resources\\files\\xml\\teams.xml";
@Autowired
    public TeamServiceImpl(TeamRepository teamRepository, PictureRepository pictureRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlReader xmlReader) {
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    this.xmlReader = xmlReader;
}

    @Override
    
    public String importTeams() throws JAXBException, FileNotFoundException {
    StringBuilder importResult = new StringBuilder();
        TeamImportRootDto teamImportRootDto = this.xmlParser.unmarshalFromFile(TEAMS_FILE_PATH,TeamImportRootDto.class);
        List<TeamImportDto>teamImportDtos = teamImportRootDto.getTeamImportDtos();

        for (TeamImportDto teamImportDto : teamImportDtos) {
            Team team = this.teamRepository.findByName(teamImportDto.getName()).orElse(null);
            if(team != null){
                importResult.append("Invalid team - already exists in Database").append(System.lineSeparator());
                continue;
            }
            Picture picture = this.pictureRepository.findByUrl(teamImportDto.getTeamImportPictureDto().getUrl()).orElse(null);
            if(!this.validatorUtil.isValid(teamImportDto) || picture == null){
                importResult.append("Invalid team").append(System.lineSeparator());
                continue;
            }
            team = this.modelMapper.map(teamImportDto, Team.class);
            team.setPicture(picture);

            this.teamRepository.saveAndFlush(team);
            importResult.append("Succesfully imported team ").append(team.getName()).append(System.lineSeparator());
        }

       return importResult.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count()>0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return this.xmlReader.readFromFile(TEAMS_FILE_PATH);
    }

}
