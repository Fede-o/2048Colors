package fo.pigdm.colors2048.view.levelsRecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fo.pigdm.colors2048.R;
import fo.pigdm.colors2048.view.listeners.OnLevelSelectedListener;

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

    private void readSavedSettings() {
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
        holder.levImageView.setImageResource(levelDetails.getLevelImage());
        holder.levNameTextView.setText(levelDetails.getLevelName());
        holder.levDescTextView.setText(levelDetails.getLevelDescription());

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


    protected class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView levImageView;
        private TextView levNameTextView;
        private TextView levDescTextView;
        private TextView levSelected;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            levImageView = itemView.findViewById(R.id.level_image);
            levNameTextView = itemView.findViewById(R.id.level_name);
            levDescTextView = itemView.findViewById(R.id.level_description);
            levSelected = itemView.findViewById(R.id.level_selected);

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
                levSelected.setVisibility(View.VISIBLE);
            else
                levSelected.setVisibility(View.GONE);
        }




    }
}


