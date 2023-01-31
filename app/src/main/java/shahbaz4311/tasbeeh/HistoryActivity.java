package shahbaz4311.tasbeeh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import shahbaz4311.tasbeeh.utils.DBHandler;
import shahbaz4311.tasbeeh.utils.Record;
import shahbaz4311.tasbeeh.utils.RecordOutputAdapter;

public class HistoryActivity extends AppCompatActivity {

    String tasbeehName;
    TextView tasbeeh_name;
    RecyclerView outputView;

    List<Record> records;
    DBHandler dbHandler;
    RecordOutputAdapter recordOutputAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //getting tasbeeh name from intent
        tasbeehName=getIntent().getStringExtra("tasbeehName");

        //initializing
        dbHandler=new DBHandler(HistoryActivity.this);
        tasbeeh_name=findViewById(R.id.tasbeeh_name);
        tasbeeh_name.setText(tasbeehName);
        outputView=findViewById(R.id.outputView);
        outputView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        outputView.setHasFixedSize(true);

        //get records from db
        records=dbHandler.getAllTasbeehRecords(tasbeehName);

        //initializing adapter
        recordOutputAdapter=new RecordOutputAdapter(records);

        //setting adapter
        outputView.setAdapter(recordOutputAdapter);
    }
}