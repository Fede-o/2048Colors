package fo.pigdm.colors2048.logic;

import android.graphics.Color;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

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
        Slot availableSlot = this.board.getRandomAvailableSlot();

        Tile tileToInsert= new Tile(availableSlot.getX(), availableSlot.getY(), Color.RED);
        board.insertTile(tileToInsert);
    }

    @Override
    public void moveTile(Tile tile, Slot slot) {
        board.removeTile(tile);

        tile.updatePosition(slot);

        board.insertTile(tile);
    }

    public void playerMove(int direction) {
        //trovare la posizione più lontana libera nella direzione
        //direction 0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT
        if (direction == 2){
            for(int x = 0; x < NUM_COLUMNS; x++) {

                for(int y = (NUM_ROWS-1); y >= 0; y--) {

                    if(board.getSlotContent(x,y) != null){
                        Tile tileToMove = board.getSlotContent(x,y);
                        //moveTile(tileToMove, getFinalSlotToMove(tileToMove, 2));
                        Slot previousSlot;
                        Slot nextSlot = new Slot(tileToMove.getX(), tileToMove.getY());
                        //for direction DOWN = 2
                        for(int i = y; i < NUM_ROWS; i++) {
                            if (board.isSlotAvailable(x, i)) {
                                previousSlot = nextSlot;
                                nextSlot = new Slot(previousSlot.getX(), (previousSlot.getY() + 1));
                            }
                        }
                        moveTile(tileToMove, nextSlot);
                    }
                }

            }
        }
    }


    /*public Slot getFinalSlotToMove(Tile tile, int direction) {
        //slot to analyze
        Slot currentSlot = new Slot(tile.getX(), tile.getY());
        //final slot to move the tile
        Slot finalSlot = currentSlot;
        //direction UP
        if (direction == 0 ) {
            if (currentSlot.getY() == 0) {

                finalSlot = currentSlot;
            }
            else {
                currentSlot.setY(currentSlot.getY() - 1);
                while (board.isSlotAvailable(currentSlot.getX(), currentSlot.getY()) && currentSlot.getY() > 0) {
                    finalSlot = currentSlot;
                    currentSlot.setY(currentSlot.getY() - 1);

                }

            }
        }
        return finalSlot;
    }
    */

    private Slot getFinalSlotToMove(Tile tile, int direction) {
        Slot previousSlot;
        Slot nextSlot = new Slot(tile.getX(), tile.getY());
        //for direction DOWN = 2
        if (direction == 2) {

            while (nextSlot.getX() < NUM_COLUMNS && nextSlot.getY() < NUM_ROWS && board.isSlotAvailable(nextSlot.getX(), nextSlot.getY())) {
                previousSlot = nextSlot;
                nextSlot = new Slot(previousSlot.getX(), (previousSlot.getY() + 1));
            }

        }
        return nextSlot;
    }



    //sostituire il colore salvato nella tile con il codice colore e spostare i colori in un xml
    public int getTileColor(int x, int y) {
        //todo
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
