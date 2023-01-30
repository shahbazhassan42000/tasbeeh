package shahbaz4311.tasbeeh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import shahbaz4311.tasbeeh.utils.RecordAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView inputView;
    List<String> tasbeehNames;
    RecyclerView.Adapter recordAdapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing
        tasbeehNames=new ArrayList<>();

        //getting tasbeeh names from db
        tasbeehNames.add("abc");
        tasbeehNames.add("def");
        tasbeehNames.add("ghi");



        inputView=findViewById(R.id.inputView);
        layoutManager=new LinearLayoutManager(MainActivity.this);
        inputView.setHasFixedSize(true);
        inputView.setLayoutManager(layoutManager);


        recordAdapter=new RecordAdapter(tasbeehNames);

        //setting inputRecycleView adapter
        inputView.setAdapter(recordAdapter);


    }
}