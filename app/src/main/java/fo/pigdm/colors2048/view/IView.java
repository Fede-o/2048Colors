package fo.pigdm.colors2048.view;

import fo.pigdm.colors2048.logic.ILogic;
import fo.pigdm.colors2048.view.gameDialogs.OnGameOverListener;
import fo.pigdm.colors2048.view.gameDialogs.OnGameWonListener;
import fo.pigdm.colors2048.view.gameDialogs.OnTileMergeListener;
import fo.pigdm.colors2048.view.gameDialogs.OnTileMoveListener;

public interface IView {

    public int getNumColors();

    public int[] getColorPalette();

    public void updateView();

    public void setOnGameWonListener(OnGameWonListener listener);

    public void setOnGameOverListener(OnGameOverListener listener);

    public void setOnTileMoveListener(OnTileMoveListener listener);

    public void setOnTileMergeListener(OnTileMergeListener listener);

    public void gameWon();

    public void gameOver();

    public void tileMove();

    public void tileMerge();

    public void setLogic(ILogic logic);

}
