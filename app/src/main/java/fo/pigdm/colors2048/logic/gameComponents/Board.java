package fo.pigdm.colors2048.logic.gameComponents;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    Tile[][] gameBoard;

    public Board(int sizeX, int sizeY) {
        gameBoard = new Tile[sizeX][sizeY];
        clearBoard();
    }

    public void clearBoard() {
        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard[0].length; y++) {
                gameBoard[x][y] = null;
            }
        }
    }


    public void insertTile(Tile tile) {

        gameBoard[tile.getX()][tile.getY()] = tile;
    }

    public void removeTile(Tile tile) {

        gameBoard[tile.getX()][tile.getY()] = null;
    }

    public Slot getRandomAvailableSlot() {
        ArrayList<Slot> availableSlots = getAvailableSlots();
        if(!availableSlots.isEmpty()){
            Random random = new Random();
            Slot randomSlot = availableSlots.get(random.nextInt(availableSlots.size()));
            return randomSlot;
        }
        else {
            return null;
        }
    }

    public ArrayList<Slot> getAvailableSlots() {
        ArrayList<Slot> availableSlots = new ArrayList<>();
        for (int x = 0; x < this.gameBoard.length; x++) {
            for (int y = 0; y < this.gameBoard[0].length; y++) {
                if (this.gameBoard[x][y] == null) {
                    Slot slot = new Slot(x, y);
                    availableSlots.add(slot);
                }
            }
        }
        return availableSlots;
    }

    public Tile getSlotContent (int x, int y) {
        Tile content;
        if (x < gameBoard.length & y < gameBoard[0].length) {
            content = gameBoard[x][y];
        } else {
            content = null;
        }
        return content;
    }

    public boolean isSlotAvailable(int x, int y) {
        if(gameBoard[x][y] == null){
            return true;
        }
        else {
            return false;
        }
    }

    public int getCurrentMaxColor() {
        int currentMaxColor = 0;
        for (int x = 0; x < this.gameBoard.length; x++) {
            for (int y = 0; y < this.gameBoard[0].length; y++) {
                if(this.gameBoard[x][y] != null) {
                    int currentTileColor = this.gameBoard[x][y].getColor();
                    if (currentTileColor > currentMaxColor) {
                        currentMaxColor = currentTileColor;
                    }
                }
            }
        }
        return currentMaxColor;
    }


}