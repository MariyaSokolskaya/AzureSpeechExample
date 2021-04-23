package com.samsung.itschool.azurespeechexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    String s = "";
    EditText userText;
    Button azureButton;
    String token = "";

    Retrofit tokenRetrofit = new Retrofit.Builder()
            .baseUrl(AzureSpeechAPI.tokenURI)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    Retrofit voiceRetrofit = new Retrofit.Builder()
            .baseUrl(AzureSpeechAPI.speechURI)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    AzureSpeechAPI tokenSpeechAPI = tokenRetrofit.create(AzureSpeechAPI.class);
    AzureSpeechAPI voiceSpeechAPI = voiceRetrofit.create(AzureSpeechAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText    =   findViewById(R.id.userText);
        azureButton =   findViewById(R.id.azureButton);
    }

    public void speechText(View view) {
        s = userText.getText().toString();
        Call<String> tokenCall = tokenSpeechAPI.getToken();
        tokenCall.enqueue(new SpeechCallback());
    }


    private class SpeechCallback implements retrofit2.Callback<String> {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            if(response.isSuccessful()){
                token = response.body();
            }
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

        }
    }
}
