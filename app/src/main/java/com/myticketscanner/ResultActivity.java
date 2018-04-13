package com.myticketscanner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    TextView codeTextView;
    TextView resultTextView;
    MaterialProgressBar progressBar;
    ImageView sadCharacterImageView;
    ImageView happyCharacterImageView;
    Button scanDoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        codeTextView = (TextView) findViewById(R.id.codeTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        progressBar = (MaterialProgressBar) findViewById(R.id.progressBar);
        sadCharacterImageView = (ImageView) findViewById(R.id.sadCharacterImageView);
        happyCharacterImageView = (ImageView) findViewById(R.id.happyCharacterImageView);
        scanDoneButton = (Button) findViewById(R.id.scanDoneButton);
        scanDoneButton.setOnClickListener(this);

        String code = getIntent().getStringExtra("code");
        codeTextView.setText(getString(R.string.scanned_code, code));

        checkResult(code);
    }

    private void checkResult(String code){
        Requests req = RetrofitClient.getClient().create(Requests.class);
        Call<ResponseBody> call = req.checkCode(code);
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.code() == 200){
                    try {
                        if(response.body().string().contains("true")){
                            updateUI(View.VISIBLE, View.GONE, getString(R.string.ticket_valid));
                        } else {
                            updateUI(View.GONE, View.VISIBLE, getString(R.string.ticket_invalid));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    updateUI(View.GONE, View.VISIBLE, getString(R.string.ticket_invalid));
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                updateUI(View.GONE, View.VISIBLE, getString(R.string.error_server));
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == scanDoneButton) {
            finish();
        }
    }

    private void updateUI(int happy, int sad, String message){
        progressBar.setVisibility(View.GONE);
        scanDoneButton.setVisibility(View.VISIBLE);
        happyCharacterImageView.setVisibility(happy);
        sadCharacterImageView.setVisibility(sad);
        resultTextView.setText(message);
    }
}
