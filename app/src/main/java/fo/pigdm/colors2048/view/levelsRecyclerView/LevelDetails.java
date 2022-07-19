package fo.pigdm.colors2048.view.levelsRecyclerView;

public class LevelDetails {

    private String levelName;
    private String levelDescription;
    private int levelImage;

    public LevelDetails(String levelName, String levelDescription, int levelImage) {
        this.levelName = levelName;
        this.levelDescription = levelDescription;
        this.levelImage = levelImage;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
    }

    public int getLevelImage() {
        return levelImage;
    }

    public void setLevelImage(int levelImage) {
        this.levelImage = levelImage;
    }
}
