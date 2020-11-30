package cn.edu.cuz.zhengjun.calculator;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    List<HistoryBean> historyBeans;

    public void setHistoryBeans(List<HistoryBean> historyBeans) {
        this.historyBeans = historyBeans;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, null, false);
        return new HistoryHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        HistoryBean historyBean = historyBeans.get(position);
        holder.tvResult.setText(historyBean.getResult());
        holder.tvContent.setText(historyBean.getContent());
    }

    @Override
    public int getItemCount() {
        return historyBeans == null ? 0 : historyBeans.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        private TextView tvContent, tvResult;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvResult = itemView.findViewById(R.id.tv_result);
        }
    }
}
