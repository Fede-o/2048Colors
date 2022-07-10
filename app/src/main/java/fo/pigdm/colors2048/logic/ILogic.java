package fo.pigdm.colors2048.logic;

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

    public int getCurrentLevel();

    public void setView(IView view);


}
