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

    public BoardSpace getRandomAvailableSpace() {
        //todo
    }

    public void insertTile(BoardTile tile) {
        //todo
        gameBoard[tile.getX()][tile.getY()] = tile;
    }

    public void removeTile(BoardTile tile) {
        //todo
        gameBoard[tile.getX()][tile.getY()] = null;
    }

    public void isPositionAvailable() {
        //todo
    }

    public BoardTile getPositionContent (BoardSpace space) {
        BoardTile content;
        if (space != null) {
            content = gameBoard[space.getX()][space.getY()];
        } else {
            content = null;
        }
        return content;
    }

}