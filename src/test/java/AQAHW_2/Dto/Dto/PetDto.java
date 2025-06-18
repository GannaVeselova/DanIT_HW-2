package AQAHW_2.Dto.Dto;

import java.util.List;

public class PetDto {
    private int id;
    private CategotyDto category;
    private String name;
    private List<String> photoUrls;
    private List<TadsDto> tags;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategotyDto getCategory() {
        return category;
    }

    public void setCategory(CategotyDto category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<TadsDto> getTags() {
        return tags;
    }

    public void setTags(List<TadsDto> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PetDto{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}
