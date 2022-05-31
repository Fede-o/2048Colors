package fo.pigdm.colors2048.logic;

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

    /*
    public Slot getRandomAvailableSpace() {
        //todo
    }
    */

    public void insertTile(Tile tile) {
        //todo
        gameBoard[tile.getX()][tile.getY()] = tile;
    }

    public void removeTile(Tile tile) {
        //todo
        gameBoard[tile.getX()][tile.getY()] = null;
    }

    public void isPositionAvailable() {
        //todo
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

}