package fo.pigdm.colors2048.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fo.pigdm.colors2048.R;

public class GameWinDialogFragment extends DialogFragment {

    public GameWinDialogFragment() {

    }

    public void startMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle result = new Bundle();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.game_win_message).setTitle(R.string.game_win_title);
        builder.setPositiveButton(
                R.string.next_option,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked next level button
                        //GameView.logic.setCurrentLevel(GameView.logic.getCurrentLevel() + 1);
                        //GameView.logic.newGame();
                        result.putString("response", "NEXT");
                        getParentFragmentManager().setFragmentResult("getUserAction", result);
                    }
                });

        builder.setNegativeButton(
                R.string.menu_option,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startMainActivity();
                    }
                });

        return builder.create();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_win_dialog, container, false);
    }
}