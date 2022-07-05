package fo.pigdm.colors2048.logic;

import android.graphics.Color;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import fo.pigdm.colors2048.view.IView;

public class GameEngine implements ILogic {

    public final static int NUM_ROWS = 4;
    public final static int NUM_COLUMNS = 4;

    private static IView gameView;

    Board board = null;
    int currentLevel = 0;
    int colorPalette;
    long score = 0;
    boolean isPlaying = false;


    public GameEngine() {
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
    public int getNumRows() {
        return NUM_ROWS;
    }

    @Override
    public int getNumColumns() {
        return NUM_COLUMNS;
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
        boolean moved = false;

        //for direction UP = 0
        if (direction == 0) {
            for(int x = 0; x < NUM_COLUMNS; x++) {
                for(int y = 0; y < NUM_ROWS; y++) {

                    if(board.getSlotContent(x,y) != null) {
                        Tile tileToMove = board.getSlotContent(x,y);
                        Slot finalSlot = getFinalSlotToMove(tileToMove, 0);
                        if(finalSlot.getY() != tileToMove.getY()) {
                            moved = true;
                        }
                        moveTile(tileToMove, finalSlot);
                    }
                }
            }
        }

        //for direction RIGHT = 1
        if (direction == 1) {
            for (int y = 0; y < NUM_ROWS; y++) {
                for (int x = (NUM_COLUMNS - 1); x >= 0; x--) {

                    if (board.getSlotContent(x, y) != null) {
                        Tile tileToMove = board.getSlotContent(x, y);
                        Slot finalSlot = getFinalSlotToMove(tileToMove, 1);
                        if (finalSlot.getX() != tileToMove.getX()) {
                            moved = true;
                        }
                        moveTile(tileToMove, finalSlot);
                    }
                }
            }
        }


        //for direction DOWN = 2
        if (direction == 2) {
            for (int x = 0; x < NUM_COLUMNS; x++) {
                for (int y = (NUM_ROWS - 1); y >= 0; y--) {

                    if (board.getSlotContent(x, y) != null) {
                        Tile tileToMove = board.getSlotContent(x, y);
                        Slot finalSlot = getFinalSlotToMove(tileToMove, 2);

                        if (finalSlot.getY() != tileToMove.getY()) {
                            moved = true;
                        }
                        moveTile(tileToMove, finalSlot);
                    }

                }
            }
        }

        //for direction LEFT = 3
        if (direction == 3) {
            for(int y = 0; y < NUM_ROWS; y++) {
                for(int x = 0; x < NUM_COLUMNS; x++) {

                    if(board.getSlotContent(x,y) != null) {
                        Tile tileToMove = board.getSlotContent(x,y);
                        Slot finalSlot = getFinalSlotToMove(tileToMove, 3);
                        if(finalSlot.getX() != tileToMove.getX()) {
                            moved = true;
                        }
                        moveTile(tileToMove, finalSlot);

                    }
                }
            }
        }

        if(moved){
            this.generateTile();
        }
    }


    private Slot getFinalSlotToMove(Tile tile, int direction) {
        Slot previousSlot;
        Slot nextSlot = new Slot(tile.getX(), tile.getY());

        //for direction UP = 0
        if (direction == 0) {

            for(int i = tile.getY(); i >= 0; i--) {
                if (board.isSlotAvailable(tile.getX(), i)) {
                    previousSlot = nextSlot;
                    nextSlot = new Slot(previousSlot.getX(), (previousSlot.getY() - 1));
                }
            }

        }

        //for direction RIGHT = 1
        if (direction == 1) {

            for(int i = tile.getX(); i < NUM_COLUMNS; i++) {
                if (board.isSlotAvailable(i, tile.getY())) {
                    previousSlot = nextSlot;
                    nextSlot = new Slot((previousSlot.getX() + 1), previousSlot.getY());
                }
            }

        }

        //for direction DOWN = 2
        if (direction == 2) {

            for(int i = tile.getY(); i < NUM_ROWS; i++) {
                if (board.isSlotAvailable(tile.getX(), i)) {
                    previousSlot = nextSlot;
                    nextSlot = new Slot(previousSlot.getX(), (previousSlot.getY() + 1));
                }
            }

        }

        //for direction LEFT = 3
        if (direction == 3) {

            for(int i = tile.getX(); i >= 0; i--) {
                if (board.isSlotAvailable(i, tile.getY())) {
                    previousSlot = nextSlot;
                    nextSlot = new Slot((previousSlot.getX() - 1), previousSlot.getY());
                }
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

    public void setView(IView view) {
        this.gameView = view;
    }

}
