package fo.pigdm.colors2048.logic;

import fo.pigdm.colors2048.view.IView;

public interface ILogic {


    public void newGame();

    public void initializeBoard();

    public void generateTile();

    public void randomTile();

    public void moveTile();

    public void getRandomPosition();

}
