package com.example.mirko.todo_liste;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

    ListView lvMain;
    Button btnDelete;
    Button btnAdd;
    EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvMain = (ListView)findViewById(R.id.lvMain);
        this.btnAdd = findViewById(R.id.btnAdd);
        this.btnDelete = findViewById(R.id.btnDelete);
        this.etInput = findViewById(R.id.etInput);
    }

    private void fillVisualComponents(){

    }

    private void initEvents(){
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
