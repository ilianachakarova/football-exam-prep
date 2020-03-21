package softuni.exam.domain.dtos.players_dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class PictureJsonImportDto {
    @Expose
    @NotNull
    private String url;

    public PictureJsonImportDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
