package fo.pigdm.colors2048.logic;

import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.preference.PreferenceManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.view.IView;

public class GameEngine implements ILogic {

    public final static int NUM_ROWS = 4;
    public final static int NUM_COLUMNS = 4;

    private static IView gameView;
    private static IView colorPaletteView;

    Board board = null;
    int currentLevel = 0;
    long score = 0;
    boolean isPlaying = false;
    int currentMaxColor = 0;
    int numColors = 0;

    //-1 gameover
    //0 gioco non attivo
    //1 gioco attivo
    //2 vittoria
    int gameState = 0;

    public GameEngine() {

    }

    public void newGame() {
        board = new Board(NUM_COLUMNS, NUM_ROWS);
        initializeBoard();
        isPlaying = true;
        gameState = 1;
        score = 0;
        currentMaxColor = 0;
        numColors = colorPaletteView.getNumColors();
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

        Tile tileToInsert= new Tile(availableSlot.getX(), availableSlot.getY(), 0);
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
        int currentTileColor = 0;

        switch (direction) {
            //for direction UP = 0
            case 0:
                for (int x = 0; x < NUM_COLUMNS; x++) {
                    for (int y = 0; y < NUM_ROWS; y++) {
                        if (board.getSlotContent(x, y) != null) {
                            Tile tileToMove = board.getSlotContent(x, y);
                            //find next tile in the same direction and verify if they merge
                            Tile next = getNextTile(tileToMove, 0);

                            if (next != null && next.getColor() == tileToMove.getColor()) {
                                tileToMove = new Tile(tileToMove.getX(), tileToMove.getY(), tileToMove.getColor() + 1);
                                board.removeTile(next);

                                currentTileColor = tileToMove.getColor();
                            }

                            Slot finalSlot = getFinalSlotToMove(tileToMove, 0);
                            if (finalSlot.getY() != tileToMove.getY()) {
                                moved = true;
                            }

                            moveTile(tileToMove, finalSlot);
                        }
                    }
                }
                break;

            //for direction RIGHT = 1
            case 1:
                for (int y = 0; y < NUM_ROWS; y++) {
                    for (int x = (NUM_COLUMNS - 1); x >= 0; x--) {
                        if (board.getSlotContent(x, y) != null) {
                            Tile tileToMove = board.getSlotContent(x, y);
                            //find next tile in the same direction and verify if they merge
                            Tile next = getNextTile(tileToMove, 1);

                            if (next != null && next.getColor() == tileToMove.getColor()) {
                                tileToMove = new Tile(tileToMove.getX(), tileToMove.getY(), tileToMove.getColor() + 1);
                                board.removeTile(next);

                                currentTileColor = tileToMove.getColor();

                            }

                            Slot finalSlot = getFinalSlotToMove(tileToMove, 1);
                            if (finalSlot.getX() != tileToMove.getX()) {
                                moved = true;
                            }

                            moveTile(tileToMove, finalSlot);
                        }
                    }
                }
                break;

            //for direction DOWN = 2
            case 2:
                for (int x = 0; x < NUM_COLUMNS; x++) {
                    for (int y = (NUM_ROWS - 1); y >= 0; y--) {
                        if (board.getSlotContent(x, y) != null) {
                            Tile tileToMove = board.getSlotContent(x, y);
                            //find next tile in the same direction and verify if they merge
                            Tile next = getNextTile(tileToMove, 2);

                            if (next != null && next.getColor() == tileToMove.getColor()) {
                                tileToMove = new Tile(tileToMove.getX(), tileToMove.getY(), tileToMove.getColor() + 1);
                                board.removeTile(next);

                                currentTileColor = tileToMove.getColor();
                            }

                            Slot finalSlot = getFinalSlotToMove(tileToMove, 2);

                            if (finalSlot.getY() != tileToMove.getY()) {
                                moved = true;
                            }

                            moveTile(tileToMove, finalSlot);

                        }
                    }
                }
                break;

            //for direction LEFT = 3
            case 3:
                for (int y = 0; y < NUM_ROWS; y++) {
                    for (int x = 0; x < NUM_COLUMNS; x++) {
                        if (board.getSlotContent(x, y) != null) {
                            Tile tileToMove = board.getSlotContent(x, y);
                            //find next tile in the same direction and verify if they merge
                            Tile next = getNextTile(tileToMove, 3);

                            if (next != null && next.getColor() == tileToMove.getColor()) {
                                tileToMove = new Tile(tileToMove.getX(), tileToMove.getY(), tileToMove.getColor() + 1);
                                board.removeTile(next);

                                currentTileColor = tileToMove.getColor();
                            }

                            Slot finalSlot = getFinalSlotToMove(tileToMove, 3);
                            if (finalSlot.getX() != tileToMove.getX()) {
                                moved = true;
                            }

                            moveTile(tileToMove, finalSlot);

                        }
                    }
                }
                break;
        }

        if (moved) {
            if (currentTileColor > currentMaxColor) {
                currentMaxColor = currentTileColor;
            }
            gameView.updateView();

            checkGameWon();

            if (gameState == 1) {
                this.generateTile();
                colorPaletteView.updateView();
            }
        }
    }

    private Tile getNextTile(Tile tile, int direction) {

        switch(direction) {
            //for direction UP = 0
            case 0:
                for(int i = (tile.getY() - 1); i >= 0; i--) {
                    if (board.getSlotContent(tile.getX(), i) != null) {
                        return board.getSlotContent(tile.getX(), i);
                    }
                }
                break;

            //for direction RIGHT = 1
            case 1:
                for(int i = (tile.getX() + 1); i < NUM_COLUMNS; i++) {
                    if (board.getSlotContent(i, tile.getY()) != null) {
                        return board.getSlotContent(i, tile.getY());
                    }
                }
                break;

            //for direction DOWN = 2
            case 2:

                for(int i = (tile.getY() + 1); i < NUM_ROWS; i++) {
                    if (board.getSlotContent(tile.getX(), i) != null) {
                        return board.getSlotContent(tile.getX(), i);
                    }
                }
                break;


            //for direction LEFT = 3
            case 3:
                for(int i = (tile.getX() - 1); i >= 0; i--) {
                    if (board.getSlotContent(i, tile.getY()) != null) {
                        return board.getSlotContent(i, tile.getY());
                    }
                }
                break;
        }

        return null;
    }


    private Slot getFinalSlotToMove(Tile tile, int direction) {
        Slot previousSlot;
        Slot nextSlot = new Slot(tile.getX(), tile.getY());

        switch(direction) {
        //for direction UP = 0
            case 0:

            for(int i = tile.getY(); i >= 0; i--) {
                if (board.isSlotAvailable(tile.getX(), i)) {
                    previousSlot = nextSlot;
                    nextSlot = new Slot(previousSlot.getX(), (previousSlot.getY() - 1));
                }
            }
            break;

        //for direction RIGHT = 1
            case 1:

            for(int i = tile.getX(); i < NUM_COLUMNS; i++) {
                if (board.isSlotAvailable(i, tile.getY())) {
                    previousSlot = nextSlot;
                    nextSlot = new Slot((previousSlot.getX() + 1), previousSlot.getY());
                }
            }
            break;


        //for direction DOWN = 2
            case 2:

            for(int i = tile.getY(); i < NUM_ROWS; i++) {
                if (board.isSlotAvailable(tile.getX(), i)) {
                    previousSlot = nextSlot;
                    nextSlot = new Slot(previousSlot.getX(), (previousSlot.getY() + 1));
                }
            }
            break;

        //for direction LEFT = 3
            case 3:

            for(int i = tile.getX(); i >= 0; i--) {
                if (board.isSlotAvailable(i, tile.getY())) {
                    previousSlot = nextSlot;
                    nextSlot = new Slot((previousSlot.getX() - 1), previousSlot.getY());
                }
            }
            break;
        }

        return nextSlot;
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


    public void setCurrentLevel(int level) {
        currentLevel = level;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public int getNextColor(){
        if(currentMaxColor < numColors)
            return currentMaxColor + 1;
        else
            return currentMaxColor;
    }

    public int getGameState() {
        return gameState;
    }

    private void checkGameWon() {
        if(currentMaxColor >= (numColors-1)){
            isPlaying = false;
            gameState = 2;
            gameView.gameWon();
        }
    }

    public void setView(IView gameV, IView colorPaletteV) {
        this.gameView = gameV;
        this.colorPaletteView = colorPaletteV;
    }

}
