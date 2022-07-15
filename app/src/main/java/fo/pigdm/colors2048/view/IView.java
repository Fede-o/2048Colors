package fo.pigdm.colors2048.view;

import fo.pigdm.colors2048.logic.ILogic;
import fo.pigdm.colors2048.view.gameDialogs.OnGameOverListener;
import fo.pigdm.colors2048.view.gameDialogs.OnGameWonListener;

public interface IView {

    public int getNumColors();

    public int[] getColorPalette();

    public void updateView();

    public void gameWon();

    public void gameOver();

    public void setOnGameWonListener(OnGameWonListener listener);

    public void setOnGameOverListener(OnGameOverListener listener);

    public void setLogic(ILogic logic);

    public void setView(IView view);

}
