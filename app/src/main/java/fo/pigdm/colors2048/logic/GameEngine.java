package fo.pigdm.colors2048.logic;

import android.content.Context;

import fo.pigdm.colors2048.view.GameView;

public class GameEngine implements ILogic {

    public final static int NUM_BOARD_X = 4;
    public final static int NUM_BOARD_Y = 4;

    private final Context context;
    private final GameView view;


    Board board = null;

    public GameEngine(Context context, GameView view) {
        this.context = context;
        this.view = view;
        newGame();
    }

    public void newGame() {
        board = new Board(NUM_BOARD_X, NUM_BOARD_Y);

    }
}
