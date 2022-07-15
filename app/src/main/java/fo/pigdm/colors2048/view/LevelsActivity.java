package fo.pigdm.colors2048.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import fo.pigdm.colors2048.R;

public class LevelsActivity extends AppCompatActivity {

    private RecyclerView levelsRecyclerView;

    private ArrayList<LevelDetails> levelDetailsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        levelsRecyclerView = findViewById(R.id.levels_rec_view);

        levelDetailsArrayList = new ArrayList<>();

        int[] numColorsLevels = getResources().getIntArray(R.array.numColorsLevels);

        String[] levelNames = getResources().getStringArray(R.array.levels_entries);
        String[] levelDesc = getResources().getStringArray(R.array.levels_descriptions);

        for (int i = 0; i < numColorsLevels.length; i++) {
            levelDetailsArrayList.add(
                    new LevelDetails(levelNames[i], levelDesc[i], R.color.level_test_color5)
            );
        }

        LevelAdapter levelAdapter = new LevelAdapter(this, levelDetailsArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        levelsRecyclerView.setLayoutManager(linearLayoutManager);
        levelsRecyclerView.setAdapter(levelAdapter);
    }
}