package fo.pigdm.colors2048.view;

public class LevelDetails {

    private String level_name;
    private String level_description;
    private int level_image;

    public LevelDetails(String level_name, String level_description, int level_image) {
        this.level_name = level_name;
        this.level_description = level_description;
        this.level_image = level_image;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getLevel_description() {
        return level_description;
    }

    public void setLevel_description(String level_description) {
        this.level_description = level_description;
    }

    public int getLevel_image() {
        return level_image;
    }

    public void setLevel_image(int level_image) {
        this.level_image = level_image;
    }
}
