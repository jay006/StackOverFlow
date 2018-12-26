package com.stackflow.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.stackflow.app.service.database.DatabaseCreator;
import com.stackflow.app.service.database.LocalDataRepository;
import com.stackflow.app.service.model.PopularTag;
import com.stackflow.app.service.model.Question;
import com.stackflow.app.service.model.ResponseList;
import com.stackflow.app.service.model.UserInterest;
import com.stackflow.app.service.net.DataRepository;

import java.util.List;
import java.util.Map;

public class HomeViewModel extends ViewModel {

    private final DataRepository dataRepository;
    private final LocalDataRepository localDataRepository;
    private final MutableLiveData<String> selectedTag = new MutableLiveData<>();


    public HomeViewModel() {
        dataRepository = DataRepository.instance();
        localDataRepository = LocalDataRepository.instance();
    }

    public void setTag(String tag){
        selectedTag.setValue(tag);
    }

    public LiveData<String> getSelectedTag(){
        return selectedTag;
    }

    public LiveData<ResponseList<Question>> trendingQuestions(Map<String,String> map) {
        MutableLiveData<ResponseList<Question>> data = new MutableLiveData<>();
        dataRepository.trendingQuestions(map).observeForever(data::setValue);
        return data;
    }

    public LiveData<List<UserInterest>> getUserInterest(){
        return localDataRepository.getUserInterest();
    }

    public LiveData<ResponseList<PopularTag>> getPopularTag(Map<String, String> map) {
        MutableLiveData<ResponseList<PopularTag>> data = new MutableLiveData<>();
        dataRepository.getPopularTag(map).observeForever(data::setValue);
        return data;
    }

}
