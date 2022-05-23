package fo.pigdm.colors2048.logic;

public class BoardTile extends BoardSpace {

    private int color = 0;

    public BoardTile(int x, int y, int color) {
        super(x,y);
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public void updatePosition(BoardSpace space) {
        this.setX(space.getX());
        this.setY(space.getY());
    }

}