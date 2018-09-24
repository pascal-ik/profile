package pamtech.com.splashapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class DialogueFragment extends DialogFragment {

    private ImageView mImageView;
    private Button mOkButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogue_layout, container, false);

        mImageView = view.findViewById(R.id.imageView);
        mOkButton = view.findViewById(R.id.btnDismiss);

        //set title
        getDialog().setTitle("Dialog Fragment");

        mImageView.setImageResource(R.drawable.app_icon);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;

    }
}
