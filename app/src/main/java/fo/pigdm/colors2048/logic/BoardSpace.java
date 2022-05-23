package fo.pigdm.colors2048.logic;

public class BoardSpace {
    private int spaceX;
    private int spaceY;

    public BoardSpace(int x, int y) {
        this.spaceX = x;
        this.spaceY = y;
    }

    public int getX() {
        return spaceX;
    }

    public int getY() {
        return spaceY;
    }

    public void setX(int x) {
        this.spaceX = x;
    }

    public void setY(int y) {
        this.spaceY = y;
    }
}