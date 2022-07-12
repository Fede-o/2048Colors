package fo.pigdm.colors2048.view;

import fo.pigdm.colors2048.logic.ILogic;

public interface IView {

    public int getNumColors();

    public void updateView();

    public void setLogic(ILogic logic);

}
