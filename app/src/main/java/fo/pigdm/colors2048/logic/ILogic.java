package fo.pigdm.colors2048.logic;

import fo.pigdm.colors2048.logic.gameComponents.Slot;
import fo.pigdm.colors2048.logic.gameComponents.Tile;
import fo.pigdm.colors2048.view.IView;

public interface ILogic {

    public void newGame();

    public void initializeBoard();

    public int getNumRows();

    public int getNumColumns();

    public void generateTile();

    public void moveTile(Tile tile, Slot slot);

    public void playerMove(int direction);

    public int getTileColor(int x, int y);

    public void setCurrentLevel(int level);

    public int getCurrentLevel();

    public void setGridDim(int gridDim);

    public int getNextColor();

    public long getScore();

    public void setView(IView gameV, IView colorPaletteV);


}
