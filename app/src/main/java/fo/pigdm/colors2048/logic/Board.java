package fo.pigdm.colors2048.logic;

public class Board {

    BoardTile[][] gameBoard;

    public Board(int sizeX, int sizeY) {
        gameBoard = new BoardTile[sizeX][sizeY];
        clearBoard();
    }

    public void clearBoard() {
        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard[0].length; y++) {
                gameBoard[x][y] = null;
            }
        }
    }
}