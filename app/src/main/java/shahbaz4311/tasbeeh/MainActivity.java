package shahbaz4311.tasbeeh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import shahbaz4311.tasbeeh.utils.DBHandler;
import shahbaz4311.tasbeeh.utils.RecordAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView inputView;
    List<String> tasbeehNames;
    RecyclerView.Adapter recordAdapter;
    RecyclerView.LayoutManager layoutManager;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler=new DBHandler(MainActivity.this);

        //initializing
        tasbeehNames=new ArrayList<>();

        //getting tasbeeh names from db
        tasbeehNames=dbHandler.getAllTasbeehNames();
        inputView=findViewById(R.id.inputView);
        layoutManager=new LinearLayoutManager(MainActivity.this);
        inputView.setHasFixedSize(true);
        inputView.setLayoutManager(layoutManager);


        recordAdapter=new RecordAdapter(tasbeehNames);

        //setting inputRecycleView adapter
        inputView.setAdapter(recordAdapter);


    }
}