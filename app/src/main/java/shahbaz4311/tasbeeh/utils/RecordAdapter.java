package shahbaz4311.tasbeeh.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import shahbaz4311.tasbeeh.R;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordVH> {

    List<Record> recordList;
    public RecordAdapter(List<Record> recordList){
        this.recordList=recordList;
    }
    @NonNull
    @Override
    public RecordVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recordView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recordinput,parent,false);
        return new RecordVH(recordView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordVH holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecordVH extends RecyclerView.ViewHolder{
        TextView nameInput;
        CheckBox reciteInput;
        EditText countInput;


        public RecordVH(@NonNull View itemView) {
            super(itemView);
            nameInput=itemView.findViewById(R.id.nameInput);
            reciteInput=itemView.findViewById(R.id.reciteInput);
            countInput=itemView.findViewById(R.id.countInput);


        }
    }
}
