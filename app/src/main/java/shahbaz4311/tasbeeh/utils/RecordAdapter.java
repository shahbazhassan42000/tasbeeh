package shahbaz4311.tasbeeh.utils;

import android.text.Editable;
import android.text.TextWatcher;
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

    List<Record> records;

    public RecordAdapter(List<Record> records) {
        this.records = records;
    }

    @NonNull
    @Override
    public RecordVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordinput, parent, false);
        return new RecordVH(recordView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordVH holder, int position) {
        holder.record = records.get(position);
        holder.nameInput.setText(holder.record.getName());
        holder.reciteInput.setChecked(holder.record.isRecited());
        holder.countInput.setText(String.valueOf(holder.record.getCount()));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public static class RecordVH extends RecyclerView.ViewHolder {
        TextView nameInput;
        CheckBox reciteInput;
        EditText countInput;

        Record record;

        public RecordVH(@NonNull View itemView) {
            super(itemView);
            nameInput = itemView.findViewById(R.id.nameInput);
            reciteInput = itemView.findViewById(R.id.reciteInput);
            countInput = itemView.findViewById(R.id.countInput);

            //setting checked listener on reciteInput to enable/disable counter
            reciteInput.setOnCheckedChangeListener((buttonView, isChecked) -> {
                countInput.setEnabled(isChecked);
                record.setRecited(isChecked);
                if(!isChecked){
                    record.setCount(0);
                    countInput.setText("0");
                }
            });

            //setting text changed listener on countInput to update count
            countInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().isEmpty()) {
                        record.setCount(0);
                    } else {
                        record.setCount(Integer.parseInt(s.toString()));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }
    }
}
