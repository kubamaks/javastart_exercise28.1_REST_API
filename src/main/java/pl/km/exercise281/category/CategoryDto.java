package pl.km.exercise281.category;

import java.io.Serial;
import java.io.Serializable;

class CategoryDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 148545035059347034L;
    private String name;
    private String description;
    private Long offers;

    public CategoryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOffers() {
        return offers;
    }

    public void setOffers(Long offers) {
        this.offers = offers;
    }
}
