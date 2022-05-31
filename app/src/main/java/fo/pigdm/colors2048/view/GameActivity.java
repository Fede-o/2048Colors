package fo.pigdm.colors2048.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.view.testView.TestCustomDrawableView;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}