package fo.pigdm.colors2048.logic;

public class GameEngineHelper {

    GameEngine gameEngine;


    public GameEngineHelper(GameEngine gameEng) {
        this.gameEngine = gameEng;
    }

    public Tile getNextTile(Tile tile, int direction) {

        switch(direction) {
            //for direction UP = 0
            case 0:
                for(int i = (tile.getY() - 1); i >= 0; i--) {
                    if (gameEngine.board.getSlotContent(tile.getX(), i) != null) {
                        return gameEngine.board.getSlotContent(tile.getX(), i);
                    }
                }
                break;

            //for direction RIGHT = 1
            case 1:
                for(int i = (tile.getX() + 1); i < gameEngine.NUM_COLUMNS; i++) {
                    if (gameEngine.board.getSlotContent(i, tile.getY()) != null) {
                        return gameEngine.board.getSlotContent(i, tile.getY());
                    }
                }
                break;

            //for direction DOWN = 2
            case 2:

                for(int i = (tile.getY() + 1); i < gameEngine.NUM_ROWS; i++) {
                    if (gameEngine.board.getSlotContent(tile.getX(), i) != null) {
                        return gameEngine.board.getSlotContent(tile.getX(), i);
                    }
                }
                break;


            //for direction LEFT = 3
            case 3:
                for(int i = (tile.getX() - 1); i >= 0; i--) {
                    if (gameEngine.board.getSlotContent(i, tile.getY()) != null) {
                        return gameEngine.board.getSlotContent(i, tile.getY());
                    }
                }
                break;
        }

        return null;
    }

    public Slot getFinalSlotToMove(Tile tile, int direction) {
        Slot previousSlot;
        Slot nextSlot = new Slot(tile.getX(), tile.getY());

        switch(direction) {
            //for direction UP = 0
            case 0:

                for(int i = tile.getY(); i >= 0; i--) {
                    if (gameEngine.board.isSlotAvailable(tile.getX(), i)) {
                        previousSlot = nextSlot;
                        nextSlot = new Slot(previousSlot.getX(), (previousSlot.getY() - 1));
                    }
                }
                break;

            //for direction RIGHT = 1
            case 1:

                for(int i = tile.getX(); i < gameEngine.NUM_COLUMNS; i++) {
                    if (gameEngine.board.isSlotAvailable(i, tile.getY())) {
                        previousSlot = nextSlot;
                        nextSlot = new Slot((previousSlot.getX() + 1), previousSlot.getY());
                    }
                }
                break;


            //for direction DOWN = 2
            case 2:

                for(int i = tile.getY(); i < gameEngine.NUM_ROWS; i++) {
                    if (gameEngine.board.isSlotAvailable(tile.getX(), i)) {
                        previousSlot = nextSlot;
                        nextSlot = new Slot(previousSlot.getX(), (previousSlot.getY() + 1));
                    }
                }
                break;

            //for direction LEFT = 3
            case 3:

                for(int i = tile.getX(); i >= 0; i--) {
                    if (gameEngine.board.isSlotAvailable(i, tile.getY())) {
                        previousSlot = nextSlot;
                        nextSlot = new Slot((previousSlot.getX() - 1), previousSlot.getY());
                    }
                }
                break;
        }

        return nextSlot;
    }


}
