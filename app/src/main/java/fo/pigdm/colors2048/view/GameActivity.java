package fo.pigdm.colors2048.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.view.testView.TestCustomDrawableView;

public class GameActivity extends AppCompatActivity {

    TestCustomDrawableView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testView = new TestCustomDrawableView(this);
        setContentView(R.layout.activity_game);
    }
}