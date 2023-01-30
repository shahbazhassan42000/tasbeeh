package shahbaz4311.tasbeeh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import shahbaz4311.tasbeeh.utils.DBHandler;
import shahbaz4311.tasbeeh.utils.MyCustomDialog;
import shahbaz4311.tasbeeh.utils.RecordAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView inputView;
    List<String> tasbeehNames;
    EditText tasbeehName;
    RecyclerView.Adapter recordAdapter;
    RecyclerView.LayoutManager layoutManager;

    Button saveBtn,addBtn,viewBtn;
    DBHandler dbHandler;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler=new DBHandler(MainActivity.this);

        //initializing
        tasbeehNames=new ArrayList<>();
        saveBtn=findViewById(R.id.saveBtn);
        addBtn=findViewById(R.id.addBtn);
        viewBtn=findViewById(R.id.viewBtn);

        //adding listeners
        saveBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);


        //getting tasbeeh names from db
        tasbeehNames=dbHandler.getAllTasbeehNames();
        inputView=findViewById(R.id.inputView);
        layoutManager=new LinearLayoutManager(MainActivity.this);
        inputView.setHasFixedSize(true);
        inputView.setLayoutManager(layoutManager);


        recordAdapter=new RecordAdapter(tasbeehNames);
        recordAdapter.notifyDataSetChanged();

        //setting inputRecycleView adapter
        inputView.setAdapter(recordAdapter);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveBtn:
                //saving data to db
                break;
            case R.id.addBtn:
                //creating custom dialog to add tasbeeh
                customDialog();
                break;
            case R.id.viewBtn:
                //viewing data
                break;
        }

    }

    private void customDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_tasbeeh);
        builder.setIcon(R.drawable.add_icon);
        builder.setCancelable(false);
        View view=builder.create().getLayoutInflater().inflate(R.layout.tasbeeh_name,null);
        builder.setView(view);
        tasbeehName=view.findViewById(R.id.tasbeeh_name_input);
        builder.setPositiveButton(R.string.add, (dialog, which) -> {
            //adding tasbeeh to arraylist if not empty
            if(!tasbeehName.getText().toString().trim().isEmpty()){
                tasbeehNames.add(tasbeehName.getText().toString());
            }

        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
            //canceling dialog
            dialog.cancel();
        });
        builder.create();
        builder.show();
    }
}