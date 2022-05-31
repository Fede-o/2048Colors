package fo.pigdm.colors2048.logic;

import android.graphics.Color;

public class GameEngine implements ILogic {

    public final static int NUM_ROWS = 4;
    public final static int NUM_COLUMNS = 4;

    private static GameEngine instance = null;

    Board board = null;
    int currentLevel = 0;
    int colorPalette;
    long score = 0;
    boolean isPlaying = false;


    private GameEngine() {
        newGame();
        isPlaying = true;
        score = 0;

    }

    public void newGame() {
        board = new Board(NUM_COLUMNS, NUM_ROWS);
        initializeBoard();

    }

    @Override
    public void initializeBoard() {
        board.clearBoard();
        generateTile();

    }

    @Override
    public void generateTile() {
        Tile tileToInsert = new Tile(1, 1, Color.YELLOW);
        board.insertTile(tileToInsert);
        tileToInsert.setX(0);
        tileToInsert.setY(0);
        board.insertTile(tileToInsert);
        tileToInsert.setX(2);
        tileToInsert.setY(2);
        board.insertTile(tileToInsert);
        tileToInsert.setX(3);
        tileToInsert.setY(3);
        board.insertTile(tileToInsert);
    }

    @Override
    public void randomTile() {
        //todo
    }

    @Override
    public void getRandomPosition() {
        //todo
    }

    @Override
    public void moveTile() {
        //todo
    }

    public int getTileColor(int x, int y) {
        Tile tile = this.board.getSlotContent(x, y);
        if(tile != null){
            return tile.getColor();
        }
        else {
            return -1;
        }
    }

    public static GameEngine getInstance() {
        if(instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

}
