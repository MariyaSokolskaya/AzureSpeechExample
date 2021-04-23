package com.samsung.itschool.azurespeechexample;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface AzureSpeechAPI {
    String tokenURI = "https://westeurope.api.cognitive.microsoft.com";
    String speechURI = "https://westeurope.tts.speech.microsoft.com";
    String apiKey = "8c085e691b2940e091e3a4bdf2804a60";


    @POST("/sts/v1.0/issueToken")
    @Headers({
            "Content-type: application/x-www-form-urlencoded",
            "Content-Length: 0",
            "Ocp-Apim-Subscription-Key: " + apiKey
    })
    Call<String> getToken();

    @GET("cognitiveservices/voices/list")
    //@Headers(value = {
    //        "Authorization: Bearer " + MainActivity.token
    //})
    Call<ArrayList<Dictor>> getListDictors(@Header("Authorization: Bearer ")
                                           String token);

    @POST("/cognitiveservices/v1")
    @Headers({
            "X-Microsoft-OutputFormat: audio-16khz-32kbitrate-mono-mp3",
            "Content-Type: application/ssml+xml",
            "User-Agent: com.samsung.itschool.azurespeechexample"
    })
    Call<SpeechFile> getVoice(@Header("Authorization: Bearer ")
                                      String token,
                              @Body VoiceSettings voiceSettings);
}
