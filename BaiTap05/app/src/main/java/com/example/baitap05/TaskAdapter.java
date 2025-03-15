package com.example.baitap05;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baitap05.databinding.TaskItemBinding;
import java.util.List;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final List<Task> tasks;
    private final TaskClickListener taskClickListener;
    public interface TaskClickListener {
        void onEditTask(int position);
        void onDeleteTask(int position);
    }

    public TaskAdapter(List<Task> tasks, TaskClickListener taskClickListener) {
        this.tasks = tasks;
        this.taskClickListener = taskClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TaskItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.task_item, parent, false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(tasks.get(position), position);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TaskItemBinding binding;

        public TaskViewHolder(TaskItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Task task, int position) {
            binding.setTaskName(task.getName());
            binding.setPosition(position);
            binding.setTaskClickListener(taskClickListener);
            binding.executePendingBindings();
        }
    }
}
