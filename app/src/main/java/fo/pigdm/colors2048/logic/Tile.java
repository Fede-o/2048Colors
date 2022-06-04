package fo.pigdm.colors2048.logic;

public class Tile extends Slot {

    private int color = 0;

    public Tile(int x, int y, int color) {
        super(x,y);
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public void updatePosition(Slot slot) {
        this.setX(slot.getX());
        this.setY(slot.getY());
    }

}