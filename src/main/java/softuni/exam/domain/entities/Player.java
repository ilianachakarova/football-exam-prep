package softuni.exam.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "players")
public class Player extends BaseEntity{
    private java.lang.String firstName;
    private java.lang.String lastName;
    private Integer number;
    private BigDecimal salary;
    private Position position;
    private Picture picture;
    private Team team;

    public Player() {
    }
    @Column(name = "first_name", nullable = false)
    public java.lang.String getFirstName() {
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name", nullable = false)
    public java.lang.String getLastName() {
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "number", nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    @Column(name = "salary", nullable = false)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Enumerated(EnumType.STRING)
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    @ManyToOne(targetEntity = Picture.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_id",referencedColumnName = "id")
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id",referencedColumnName = "id")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
