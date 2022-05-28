package fo.pigdm.colors2048.logic;

import android.content.Context;

import fo.pigdm.colors2048.view.IView;

public class GameEngine implements ILogic {

    public final static int NUM_ROWS = 4;
    public final static int NUM_COLUMNS = 4;

    private final Context context;
    private IView view;


    Board board = null;
    int currentLevel = 0;
    int colorPalette;
    long score = 0;
    boolean isPlaying = false;


    public GameEngine(Context context, IView view) {
        this.context = context;
        this.view = view;
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
        BoardTile tileToInsert = new BoardTile(1, 1, 1);
        board.insertTile(tileToInsert);
    }

    @Override
    public void getRandomPosition() {

    }

    @Override
    public void moveTile() {

    }


}
