package softuni.exam.service;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.players_dtos.PlayerImportDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlReader;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final XmlReader xmlReader;
    private final ValidatorUtil validatorUtil;
    private final Gson gson;
    private final PictureRepository pictureRepository;
    private final TeamRepository teamRepository;

    private static final String PLAYERS_JSON_FILE =
            "C:\\Users\\user l\\Desktop\\exam-prep\\src\\main\\resources\\files\\json\\players.json";
@Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, XmlReader xmlReader, ValidatorUtil validatorUtil, Gson gson, PictureRepository pictureRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
    this.modelMapper = modelMapper;
    this.xmlReader = xmlReader;
    this.validatorUtil = validatorUtil;
    this.gson = gson;
    this.pictureRepository = pictureRepository;
    this.teamRepository = teamRepository;
}

    @Override
    public String importPlayers() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PlayerImportDto[] playerImportDtos = this.gson
                .fromJson(new FileReader(PLAYERS_JSON_FILE), PlayerImportDto[].class);

        Arrays.stream(playerImportDtos)
                .forEach(dto -> {
                    if (this.validatorUtil.isValid(dto)) {
                            Player player =
                                    this.playerRepository.findByFirstNameAndLastName(dto.getFirstName(),dto.getLastName()).orElse(null);
                            if(player == null){
                             player = this.modelMapper.map(dto, Player.class);

                            Team team = this.teamRepository.findByName(dto.getTeam().getName()).orElse(null);
                            Picture picture = this.pictureRepository
                                    .findByUrl(dto.getPicture().getUrl()).orElse(null);

                            player.setTeam(team);
                            player.setPicture(picture);

                            this.playerRepository.saveAndFlush(player);

                            sb.append(String.format("Successfully imported player: %s %s",
                                    dto.getFirstName(), dto.getLastName()))
                                    .append(System.lineSeparator());

                        } else {
                            sb.append("Already in DB")
                                    .append(System.lineSeparator());
                        }
                    } else {
                        sb.append("Invalid player")
                                .append(System.lineSeparator());
                    }
                });


        return sb.toString();
    }

    @Override
    public boolean areImported() {

    return this.playerRepository.count()>0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return this.xmlReader.readFromFile(PLAYERS_JSON_FILE);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        StringBuilder exportResult = new StringBuilder();
        List<Player>players = this.playerRepository.findAllBySalaryAfterOrderBySalaryDesc(BigDecimal.valueOf(100000));
        for (Player player : players) {
            exportResult.append("Player name: ").
                    append(player.getFirstName()).append(" ").append(player.getLastName()).append(System.lineSeparator());
            exportResult.append("\tNumber: ").append(player.getNumber()).append(System.lineSeparator());
            exportResult.append("\tSalary: ").append(player.getSalary()).append(System.lineSeparator());
            exportResult.append("\tTeam: ").append(player.getTeam().getName()).append(System.lineSeparator());
        }
       return exportResult.toString().trim();
    }

    @Override
    public String exportPlayersInATeam() {
    StringBuilder exportResult = new StringBuilder();
    exportResult.append("Team: North Hub").append(System.lineSeparator());
        List<Player>players = this.playerRepository.findPlayersByTeamName("North Hub");
        for (Player player : players) {
            exportResult.append("\tPlayer name: ").append(player.getFirstName()).append(" ").append(player.getLastName())
                    .append(" - ").append(player.getPosition().name()).append(System.lineSeparator());
            exportResult.append("\tNumber: ").append(player.getNumber()).append(System.lineSeparator());
        }
        return exportResult.toString().trim();
    }


}
