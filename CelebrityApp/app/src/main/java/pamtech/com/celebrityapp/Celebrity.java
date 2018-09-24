package pamtech.com.celebrityapp;

public class Celebrity {
    private int id;
    private String name;
    private String description;
    private int imageResId;

    public Celebrity(int id, String name, String description, int imageResId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

    public Celebrity(String description, int imageResId) {
        this.description = description;
        this.imageResId = imageResId;
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

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
