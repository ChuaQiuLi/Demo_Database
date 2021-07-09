package sg.edu.rp.c346.id20007649.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    EditText etTask;
    EditText etDate;
    ListView lv;

    ArrayList<String> alTasks;
    ArrayAdapter<String>  aaTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        etTask = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);

        alTasks = new ArrayList<String>();
        aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTasks);
        lv.setAdapter(aaTasks);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tasks = etTask.getText().toString();
                String dates = etDate.getText().toString();

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(tasks, dates);
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context

                alTasks.clear();
                DBHelper db = new DBHelper(MainActivity.this);

//                // Insert a task
//                ArrayList<String> data = db.getTaskContent();
//                db.close();
//
//                String txt = "";
//                for (int i = 0; i < data.size(); i++) {
//                    Log.d("Database Content", i + ". " + data.get(i));
//                    txt += i + ". " + data.get(i) + "\n";
//
//
//                }
//
//                tvResults.setText(txt);


                ArrayList<Task> data = db.getTasks();
                db.close();


                for (int i =0; i < data.size(); i++) {
                    alTasks.add(data.get(i).toString());
                }

                aaTasks.notifyDataSetChanged();

            }
        });
    }



}