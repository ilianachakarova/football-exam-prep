package softuni.exam.domain.dtos.players_dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TeamJsonImportDto {
    @Expose
    @NotNull
    private String name;
    @Expose
    private PictureJsonImportDto picture;

    public TeamJsonImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureJsonImportDto getPicture() {
        return picture;
    }

    public void setPicture(PictureJsonImportDto picture) {
        this.picture = picture;
    }
}
