package pamtech.com.zooapp;

/**
 * class to define each animal in the database
 */
public class Animal {
    //variable declarations
    String category;
    String name;
    String description;
    String soundPath;
    int img_path;

    /**
     * Constructor to make new animal object
     * @param category animal category
     * @param name animal name
     * @param description animal description
     * @param soundPath path to animal sound
     * @param img_path path to animal image
     */
    public Animal(String category, String name, String description, String soundPath, int img_path){
        this.category = category;
        this.name = name;
        this.description = description;
        this.soundPath = soundPath;
        this.img_path = img_path;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }

    public int getImg_path() {
        return img_path;
    }

    public void setImg_path(int img_path) {
        this.img_path = img_path;
    }
}
