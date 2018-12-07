package com.stackflow.stackoverflow.service.net;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.stackflow.stackoverflow.service.model.Question;
import com.stackflow.stackoverflow.service.model.ResponseList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private DataService dataService;
    private static DataRepository dataRepository;

    private DataRepository() {
        dataService = RetrofitBuilder.getInstance().create(DataService.class);
    }

    public static DataRepository instance() {
        if (dataRepository == null) {
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    public LiveData<ResponseList<Question>> trendingQuestions(Map<String,String> map) {

        MutableLiveData<ResponseList<Question>> data = new MutableLiveData<>();
        Call<ResponseList<Question>> call = dataService.trendingQuestion(map);
        call.enqueue(new Callback<ResponseList<Question>>() {
            @Override
            public void onResponse(Call<ResponseList<Question>> call, Response<ResponseList<Question>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseList<Question>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
