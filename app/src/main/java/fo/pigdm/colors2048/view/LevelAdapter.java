package fo.pigdm.colors2048.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fo.pigdm.colors2048.R;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {

    private Context context;
    private ArrayList<LevelDetails> levelDetailsArrayList;

    private int checkedPosition = 0;

    private OnLevelSelectedListener listener;

    public LevelAdapter(Context context, ArrayList<LevelDetails> levelDetailsArrayList) {
        this.context = context;
        this.levelDetailsArrayList = levelDetailsArrayList;
        readSavedSettings();
    }

    public void readSavedSettings() {
        SharedPreferences gameSettings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        checkedPosition = Integer.parseInt(gameSettings.getString("level", "0"));
    }

    @NonNull
    @Override
    public LevelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_card_layout, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull LevelAdapter.ViewHolder holder, int position) {
        LevelDetails levelDetails = levelDetailsArrayList.get(position);
        holder.levelImageView.setImageResource(levelDetails.getLevel_image());
        holder.levNameTextView.setText(levelDetails.getLevel_name());
        holder.levDescTextView.setText(levelDetails.getLevel_description());

        if(checkedPosition == position) {
            holder.changeToSelect(true);
        }
        else {
            holder.changeToSelect(false);
        }
    }

    @Override
    public int getItemCount() {
        return levelDetailsArrayList.size();
    }

    public void setOnLevelSelectedListener(OnLevelSelectedListener listener){
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView levelImageView;
        private TextView levNameTextView;
        private TextView levDescTextView;
        private TextView levelSelected;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            levelImageView = itemView.findViewById(R.id.level_image);
            levNameTextView = itemView.findViewById(R.id.level_name);
            levDescTextView = itemView.findViewById(R.id.level_description);
            levelSelected = itemView.findViewById(R.id.level_selected);

            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (listener != null) {
                                int position = getAbsoluteAdapterPosition();
                                if(position != RecyclerView.NO_POSITION) {
                                    listener.onLevelClick(itemView, position);
                                    notifyItemChanged(checkedPosition);
                                    checkedPosition = getAbsoluteAdapterPosition();
                                    notifyItemChanged(checkedPosition);
                                }
                            }
                        }


                    }


            );
        }

        public void changeToSelect(boolean selected){
            if(selected)
                levelSelected.setVisibility(View.VISIBLE);
            else
                levelSelected.setVisibility(View.GONE);
        }




    }
}


