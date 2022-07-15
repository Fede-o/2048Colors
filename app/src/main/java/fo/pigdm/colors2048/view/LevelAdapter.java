package fo.pigdm.colors2048.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fo.pigdm.colors2048.R;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {

    private Context context;
    private ArrayList<LevelDetails> levelDetailsArrayList;


    public LevelAdapter(Context context, ArrayList<LevelDetails> levelDetailsArrayList) {
        this.context = context;
        this.levelDetailsArrayList = levelDetailsArrayList;
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
    }

    @Override
    public int getItemCount() {
        return levelDetailsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView levelImageView;
        private TextView levNameTextView, levDescTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            levelImageView = itemView.findViewById(R.id.level_image);
            levNameTextView = itemView.findViewById(R.id.level_name);
            levDescTextView = itemView.findViewById(R.id.level_description);
        }
    }
}
