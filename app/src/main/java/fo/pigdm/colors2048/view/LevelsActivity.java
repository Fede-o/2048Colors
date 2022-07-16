package fo.pigdm.colors2048.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();


        int[] numColorsLevels = getResources().getIntArray(R.array.numColorsLevels);

        String[] levelNames = getResources().getStringArray(R.array.levels_entries);
        String[] levelDesc = getResources().getStringArray(R.array.levels_descriptions);

        for (int i = 0; i < numColorsLevels.length; i++) {
            levelDetailsArrayList.add(
                    new LevelDetails(levelNames[i], levelDesc[i], R.color.level_test_color5)
            );
        }

        LevelAdapter levelAdapter = new LevelAdapter(this, levelDetailsArrayList);
        levelAdapter.setOnLevelSelectedListener(
                new OnLevelSelectedListener() {
                    @Override
                    public void onLevelClick(View itemView, int position) {
                        editor.putString("level", String.valueOf(position));
                        editor.apply();
                    }
                }
        );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        levelsRecyclerView.setLayoutManager(linearLayoutManager);
        levelsRecyclerView.setAdapter(levelAdapter);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}