package com.example.baitap06.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baitap06.api.ApiService;
import com.example.baitap06.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private ApiService apiService;
    private MutableLiveData<List<Category>> categoryList;
    private MutableLiveData<String> errorMessage;


    public MainViewModel(@NonNull Application application) {
        super(application);
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        categoryList = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public LiveData<List<Category>> getCategoryList() {
        return categoryList;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchCategories() {
        Call<List<Category>> call = apiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList.setValue(response.body());
                    Log.d("MainViewModel", "Categories loaded successfully: " + response.body().size() + " items");
                } else {
                    errorMessage.setValue("Failed to load categories!");
                    Log.e("MainViewModel", "Response failed: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
                Log.e("MainViewModel", "API call failed: " + t.getMessage(), t);
            }
        });
    }

}
