package shahbaz4311.tasbeeh.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import shahbaz4311.tasbeeh.R;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordVH> {

    List<String> tasbeehNames;

    public RecordAdapter(List<String> tasbeehNames) {
        this.tasbeehNames = tasbeehNames;
    }

    @NonNull
    @Override
    public RecordVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recordinput, parent, false);
        return new RecordVH(recordView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordVH holder, int position) {
        holder.tasbeehName = tasbeehNames.get(position);
        holder.nameInput.setText(holder.tasbeehName);
        holder.countInput.setText("");
    }

    @Override
    public int getItemCount() {
        return tasbeehNames.size();
    }

    public class RecordVH extends RecyclerView.ViewHolder {
        TextView nameInput;
        CheckBox reciteInput;
        EditText countInput;

        String tasbeehName;

        public RecordVH(@NonNull View itemView) {
            super(itemView);
            nameInput = itemView.findViewById(R.id.nameInput);
            reciteInput = itemView.findViewById(R.id.reciteInput);
            countInput = itemView.findViewById(R.id.countInput);

            //setting checked listener on reciteInput to enable/disable counter
            reciteInput.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked) countInput.setEnabled(true);
                else countInput.setEnabled(false);
            });


        }
    }
}
