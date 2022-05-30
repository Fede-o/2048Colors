package fo.pigdm.colors2048.view.testView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class TestCustomDrawableView extends View {

    //private TestCustomDrawable testCustomDrawable;
    private MyRedCircle myRedCircle;

    public TestCustomDrawableView(Context context) {
        super(context);

        myRedCircle = new MyRedCircle();
    }

    public TestCustomDrawableView(Context context, AttributeSet attributes) {
        super(context, attributes);

        myRedCircle = new MyRedCircle();
    }

    public TestCustomDrawableView(Context context, AttributeSet attributes, int defStyle) {
        super(context, attributes, defStyle);

        myRedCircle = new MyRedCircle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        myRedCircle.draw(canvas);
    }
}
