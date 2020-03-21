package softuni.exam.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{
    private String url;
    private List<Player>players;
    private List<Team>teams;


    public Picture() {
    }
    @Column(name = "url", nullable = false, unique = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @OneToMany(targetEntity = Player.class, mappedBy = "picture")
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    @OneToMany(targetEntity = Team.class, mappedBy = "picture",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
