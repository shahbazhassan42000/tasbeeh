package shahbaz4311.tasbeeh.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import shahbaz4311.tasbeeh.R;

public class RecordOutputAdapter extends RecyclerView.Adapter<RecordOutputAdapter.RecordOutputVH> {

    public RecordOutputAdapter(List<Record> records) {
        this.records = records;
    }
    List<Record> records;
    @NonNull
    @Override
    public RecordOutputAdapter.RecordOutputVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordoutput , parent, false);
        return new RecordOutputVH(recordView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordOutputAdapter.RecordOutputVH holder, int position) {
        holder.record = records.get(position);
        holder.dateOutput.setText(holder.record.getDate());
        holder.reciteOutput.setText(holder.record.isRecited()?"Yes":"No");
        holder.counterOutput.setText(String.valueOf(holder.record.getCount()));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public static class RecordOutputVH extends RecyclerView.ViewHolder {
        Record record;
        TextView dateOutput,reciteOutput,counterOutput;

        public RecordOutputVH(@NonNull View itemView) {
            super(itemView);
            dateOutput=itemView.findViewById(R.id.dateOutput);
            reciteOutput=itemView.findViewById(R.id.reciteOutput);
            counterOutput=itemView.findViewById(R.id.counterOutput);
        }
    }
}
