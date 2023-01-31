package shahbaz4311.tasbeeh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shahbaz4311.tasbeeh.utils.DBHandler;
import shahbaz4311.tasbeeh.utils.Record;
import shahbaz4311.tasbeeh.utils.RecordAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView inputView;
    List<Record> records;
    EditText tasbeehName;
    RecordAdapter recordAdapter;

    Button saveBtn,addBtn,viewBtn;
    DBHandler dbHandler;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler=new DBHandler(MainActivity.this);

        //initializing
        records=new ArrayList<>();
        saveBtn=findViewById(R.id.saveBtn);
        addBtn=findViewById(R.id.addBtn);
        viewBtn=findViewById(R.id.viewBtn);

        //adding listeners
        saveBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);


        //getting tasbeeh names from db
        records=dbHandler.getAllTasbeehNames();
        inputView=findViewById(R.id.inputView);
        inputView.setHasFixedSize(true);
        inputView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        recordAdapter=new RecordAdapter(records);
        recordAdapter.notifyDataSetChanged();

        //setting inputRecycleView adapter
        inputView.setAdapter(recordAdapter);
    }

    //on back pressed
    @Override
    public void onBackPressed() {
        //if records are empty
        if(records.size()==0){
            finish();
            return;
        }
        //creating alert dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Do you want to save the tasbeeh records?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            saveRecords();
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            finish();
        });
        builder.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveBtn:
                if(records.size()==0){
                    Toast.makeText(MainActivity.this,"No tasbeeh to save", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveRecords();
                break;
            case R.id.addBtn:
                //creating custom dialog to add tasbeeh
                customDialog();
                break;
            case R.id.viewBtn:
                loadViewActivity();
                break;
        }

    }

    private void loadViewActivity() {
        //creating arraylist of tasbeeh names
        ArrayList<String> tasbeehNames=new ArrayList<>();
        for(int i=0;i<records.size();i++){
            tasbeehNames.add(records.get(i).getName());
        }
        //creating intent to load view activity
        Intent intent=new Intent(MainActivity.this,ViewActivity.class);
        intent.putStringArrayListExtra("tasbeehNames",tasbeehNames);
        startActivity(intent);
    }

    private void saveRecords() {
        //displaying all buttons
        saveBtn.setEnabled(false);
        addBtn.setEnabled(false);
        viewBtn.setEnabled(false);

        //saving records
        for(int i=0;i<records.size();i++){
            //adding new tasbeeh
            int id=dbHandler.insertRecord(records.get(i));
            records.get(i).setId(id);
            if(id==-1){
                //toast error while saving tasbeeh
                Toast.makeText(MainActivity.this,"Error while saving tasbeeh: "+records.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        }
        Toast.makeText(MainActivity.this,"Records updated successfully", Toast.LENGTH_SHORT).show();


        //enable buttons
        saveBtn.setEnabled(true);
        addBtn.setEnabled(true);
        viewBtn.setEnabled(true);
    }

    @SuppressLint("SimpleDateFormat")
    private void customDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_tasbeeh);
        builder.setIcon(R.drawable.add_icon);
        builder.setCancelable(false);
        tasbeehName=new EditText(this);
        tasbeehName.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        tasbeehName.setHint(R.string.tasbeeh_name);
        tasbeehName.setPadding(50,50,50,50);
        tasbeehName.setTextSize(28);
        builder.setView(tasbeehName);
        builder.setPositiveButton(R.string.add, (dialog, which) -> {
            //adding tasbeeh to arraylist if not empty
            if(!tasbeehName.getText().toString().trim().isEmpty()){
                records.add(new Record(tasbeehName.getText().toString().trim(),false,0,new SimpleDateFormat("MMMM dd, yyyy").format(new Date())));
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