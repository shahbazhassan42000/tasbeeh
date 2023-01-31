package shahbaz4311.tasbeeh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    ArrayList<String> tasbeehNames;
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //initializing
        tasbeehNames = new ArrayList<>();
        listView = findViewById(R.id.listView);
        //getting tasbeeh names from intent
        tasbeehNames = getIntent().getStringArrayListExtra("tasbeehNames");

        //initializing adapter
        adapter = new ArrayAdapter<>(ViewActivity.this, android.R.layout.simple_list_item_1, tasbeehNames);
        //setting adapter
        listView.setAdapter(adapter);

        //setting on ItemClickListener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            //getting tasbeeh name
            String tasbeehName = tasbeehNames.get(position);
            //creating intent
            Intent intent = new Intent(ViewActivity.this, HistoryActivity.class);
            //putting tasbeeh name in intent
            intent.putExtra("tasbeehName", tasbeehName);
            //starting activity
            startActivity(intent);
        });
    }
}