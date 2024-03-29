package fo.pigdm.colors2048.view.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.view.levelsRecyclerView.LevelAdapter;
import fo.pigdm.colors2048.view.levelsRecyclerView.LevelDetails;
import fo.pigdm.colors2048.view.listeners.OnLevelSelectedListener;

public class LevelsActivity extends AppCompatActivity {

    private RecyclerView levelsRecyclerView;

    private ArrayList<LevelDetails> levelDetailsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        levelsRecyclerView = findViewById(R.id.levels_rec_view);

        levelDetailsArrayList = new ArrayList<>();

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();


        int[] numColorsLevels = getResources().getIntArray(R.array.numColorsLevels);

        String[] levelNames = getResources().getStringArray(R.array.levels_entries);
        String[] levelDesc = getResources().getStringArray(R.array.levels_descriptions);
        String[] levelPalettePreview = getResources().getStringArray(R.array.level_preview_image);

        for (int i = 0; i < numColorsLevels.length; i++) {
            int src = getResources().getIdentifier(levelPalettePreview[i], "drawable", getPackageName());
            levelDetailsArrayList.add(
                    new LevelDetails(levelNames[i], levelDesc[i], src)
            );
        }

        LevelAdapter levelAdapter = new LevelAdapter(this, levelDetailsArrayList);
        OnLevelSelectedListener selectedListener = new OnLevelSelectedListener() {
            @Override
            public void onLevelClick(View itemView, int position) {
                editor.putString("level", String.valueOf(position));
                editor.apply();
            }
        };
        levelAdapter.setOnLevelSelectedListener(selectedListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        levelsRecyclerView.setLayoutManager(linearLayoutManager);
        levelsRecyclerView.setAdapter(levelAdapter);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}