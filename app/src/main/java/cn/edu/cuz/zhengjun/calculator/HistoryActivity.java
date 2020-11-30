package cn.edu.cuz.zhengjun.calculator;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private TextView mTvCount;
    private RecyclerView mRvHistory;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();


    }

    public List<HistoryBean> getHistoryData() {
        List<HistoryBean> queryll = new HistoryDao(this).queryll();
        return queryll;
    }

    private void initView() {
        mTvCount = (TextView) findViewById(R.id.tv_count);
        mRvHistory = (RecyclerView) findViewById(R.id.rv_history);
        mRvHistory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter();
        List<HistoryBean> historyData = getHistoryData();
        int rightNum = 0;//正确的数量
        //循环保存的数据 筛选出正确的和错误的数量
        for (int i = 0; i < historyData.size(); i++) {
            if (historyData.get(i).getResult().equals("正确")) {
                rightNum++;
            }
        }
        mTvCount.setText("共"+historyData.size()+"题，答对"+rightNum+"题");
        adapter.setHistoryBeans(historyData);
        mRvHistory.setAdapter(adapter);
    }
}