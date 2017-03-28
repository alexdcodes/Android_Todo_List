/**
 * Alex Diker 100746284
 * George Brown College
 */

package com.example.alexdiker.assignment3;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.EditText;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mLayout;
    private EditText editText;
    private Button button;
    private LinearLayout layout;
    private SQLiteDatabase dbase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button_add);
        button.setOnClickListener(onClickAdd());
        layout =(LinearLayout) findViewById(R.id.linearLayout);
        editText = (EditText) findViewById(R.id.editText);

    }

    private OnClickListener onClickAdd() {

        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                RelativeLayout lay = createLayout();
                layout.addView(lay);
                lay.addView(createNewTextView(editText.getText().toString()));
                lay.addView(createNewButton(editText.getText().toString()));
                insert(editText.getText().toString());
                editText.setText("");
            }
        };
    }

private void insert(String text){
    dbase = databaseAccess();
    DatabaseSetting.addNew(dbase,text);
    //DatabaseSetting.addNew(dbase,data);
}

    private void delete(String data){

        dbase = databaseAccess();
        DatabaseSetting.delete(dbase,data);

    }


    private SQLiteDatabase databaseAccess(){
        try {

            SQLiteOpenHelper databaseHelper = new DatabaseSetting(this);

            dbase = databaseHelper.getWritableDatabase();

            Toast toast = Toast.makeText(this, "(200) Database accessed successfully.", Toast.LENGTH_SHORT);
            //toast toast = Toast.makeToast(this, "Database", Toast.WIDTH_SHORT);
            toast.show();

        } catch(SQLiteException e) {

            dbase=null;

            Toast toast = Toast.makeText(this, "Database cannot be found (ERROR)", Toast.LENGTH_SHORT);
            //toast toast = Toast.makeToast(this, "Database", Toast.WIDTH_SHORT);
            toast.show();

        }
        return dbase;
    }
    private RelativeLayout createLayout(){

        mLayout = new RelativeLayout(this);

        return mLayout;
    }

// create new button
    private Button createNewButton(String data){

        RelativeLayout.LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        final Button btn = new Button(this);
        param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        btn.setLayoutParams(param);
        btn.setText("Finished");
        btn.setTextSize(11);
        btn.setContentDescription(data);
        btn.setOnClickListener(onClick());
        return btn;

    }

// textview

    private TextView createNewTextView(String text) {

        final LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);

        textView.setLayoutParams(param);
        textView.setTextSize(19);
        textView.setTextColor(Color.BLACK);
        textView.setText(text);

        return textView;
    }

    private OnClickListener onClick() {

        return new OnClickListener() {

            @Override
            public void onClick(View v) {

                String information = (String)v.getContentDescription();
                //remove information
                delete(information);

                ((View)v.getParent()).setVisibility(View.GONE);

            }
        };
    }

}
