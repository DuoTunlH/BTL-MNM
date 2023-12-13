package com.example.btl_mnm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.btl_mnm.managers.APICaller;
import com.example.btl_mnm.model.Movie;
import com.example.btl_mnm.model.MoviesResponse;
import com.example.btl_mnm.model.Sections;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends ViewModel {
    MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    Sections section;
    Boolean isLoading = false;
    int page = 0;
    void fetchData() {
        if(!isLoading) {
            page += 1;
            isLoading = true;
            APICaller.getINSTANCE().getMovies(section, page).enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    if (page == 1) {
                        movies.setValue(response.body().results);
                    } else  {
                        movies.getValue().addAll(response.body().results);
                        movies.setValue(movies.getValue());
                    }
                    isLoading = false;
                }
                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {

                }
            });
        }
    }
}
