package softuni.exam.domain.dtos.players_dtos;

import com.google.gson.annotations.Expose;
import softuni.exam.domain.entities.Position;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class PlayerImportDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer number;
    @Expose
    private BigDecimal salary;
    @Expose
    private Position position;
    @Expose
    private PictureJsonImportDto picture;
    @Expose
    private TeamJsonImportDto team;

    public PlayerImportDto() {
    }
    @NotNull(message = "Invalid player")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }
    @NotNull(message = "Invalid player")
    @Size(min = 3, max = 15,message = "Invalid player")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }
    @NotNull(message = "Invalid player")
    @Min(1)
    @Max(99)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    @NotNull(message = "Invalid player")
    @Min(value = 0)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    @NotNull
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    @NotNull
    public PictureJsonImportDto getPicture() {
        return picture;
    }

    public void setPicture(PictureJsonImportDto picture) {
        this.picture = picture;
    }
    @NotNull
    public TeamJsonImportDto getTeam() {
        return team;
    }

    public void setTeam(TeamJsonImportDto team) {
        this.team = team;
    }
}
