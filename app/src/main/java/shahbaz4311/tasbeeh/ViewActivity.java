package shahbaz4311.tasbeeh;

import androidx.appcompat.app.AppCompatActivity;

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
        adapter = new ArrayAdapter < String > (ViewActivity.this, android.R.layout.simple_list_item_1, tasbeehNames);
        //setting adapter
        listView.setAdapter(adapter);

        //setting on ItemClickListener
        listView.setOnItemClickListener((parent, view, position, id) -> {

        });
    }
}