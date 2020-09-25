package com.zsmarter.moresettings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.peake.draggridview.DragSortDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startDialog = findViewById(R.id.button);
        startDialog.setOnClickListener(v -> {
            showDialog();
        });
    }

    //点击弹出对话框
    public void showDialog() {
        DragSortDialog dialog = new DragSortDialog(this);
        dialog.setTopItemViews("ABCDEFGHIJKLMN".split("\\B"));
        dialog.setBottomItemViews("OPQRSTUVWXYZ".split("\\B"));
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                List<String> list = ((DragSortDialog) dialog).getTopDefaultItemViews();
                for (String s : list) {
                    Log.d(TAG, s);
                }
            }
        });
        dialog.show();
    }
}