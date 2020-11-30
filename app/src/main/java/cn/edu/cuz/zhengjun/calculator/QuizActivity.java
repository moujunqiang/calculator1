package cn.edu.cuz.zhengjun.calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    int firstNum, secondNum, answer;
    TextView mFirstNumTextView;
    TextView mSecondNumTextView;
    ImageButton mCalButton;
    EditText mResultEditText;
    Random mRandom = new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initView();
        updateQuestion();
    }

    void initView() {
        mFirstNumTextView = findViewById(R.id.first_num);
        mSecondNumTextView = findViewById(R.id.second_num);
        mCalButton = findViewById(R.id.cal_btu);
        mResultEditText = findViewById(R.id.result);

        mCalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mResultEditText.getText().length() == 0) {
                    Toast.makeText(QuizActivity.this, R.string.answer_is_blank, Toast.LENGTH_SHORT).show();
                } else {
                    answer = Integer.parseInt(mResultEditText.getText().toString());
                    if (firstNum + secondNum == answer) {
                        Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                        StringBuilder builder = new StringBuilder();
                        builder.append(firstNum + "").append("+").append(secondNum + "").append("=").append(answer + "");
                        insertHistory(builder.toString(), "正确");

                    } else {
                        StringBuilder builder = new StringBuilder();
                        builder.append(firstNum + "").append("+").append(secondNum + "").append("=").append(answer + "");
                        insertHistory(builder.toString(), "错误");
                        Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                    }
                }
                updateQuestion();

            }
        });
        findViewById(R.id.btn_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizActivity.this, HistoryActivity.class));
            }
        });
    }

    /**
     * 插入数据到 数据库种
     *
     * @param content 计算内容 如 1+1=2
     * @param result  计算结果  正确 错误
     */
    public void insertHistory(String content, String result) {
        HistoryDao historyDao = new HistoryDao(this);
        HistoryBean bean = new HistoryBean();
        bean.setResult(result);
        bean.setContent(content);
        historyDao.insertHistory(bean);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void updateQuestion() {
        firstNum = mRandom.nextInt(10);
        secondNum = mRandom.nextInt(10);
        mFirstNumTextView.setText(firstNum + "");
        mSecondNumTextView.setText(secondNum + "");
        mResultEditText.setText("");
    }

}
