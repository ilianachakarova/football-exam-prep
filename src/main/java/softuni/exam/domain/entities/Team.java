package softuni.exam.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
    private String name;
    private Picture picture;
    private List<Player>players;

    public Team() {
    }
    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToOne(targetEntity = Picture.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    @OneToMany(targetEntity = Player.class, mappedBy = "team",cascade = CascadeType.ALL)
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
