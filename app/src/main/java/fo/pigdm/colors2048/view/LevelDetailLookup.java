package fo.pigdm.colors2048.view;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

final class LevelDetailLookup extends ItemDetailsLookup {

    private final RecyclerView mRecyclerView;

    LevelDetailLookup(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }


    @Nullable
    @Override
    public ItemDetails getItemDetails(@NonNull MotionEvent e) {

        View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
        if(view!= null) {
            RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(view);
            if(holder instanceof LevelAdapter.ViewHolder) {
                //return ((LevelAdapter.ViewHolder) holder).getItemDetails();
            }
        }
        return null;
    }
}
