package com.example.btl_mnm.ui.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.btl_mnm.common.Debounce;
import com.example.btl_mnm.managers.APICaller;
import com.example.btl_mnm.model.Movie;
import com.example.btl_mnm.model.MoviesResponse;
import com.example.btl_mnm.model.Sections;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {

    MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    MutableLiveData<List<Movie>> searchResults = new MutableLiveData<>();
    MutableLiveData<Boolean> isSearching = new MutableLiveData<>(false);
    String query = new String();
    Debounce debounce = new Debounce(1000, new Runnable() {
        @Override
        public void run() {
            APICaller.getINSTANCE().search(query).enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    searchResults.setValue(response.body().results);
                }
                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                }
            });
        }
    });
    void fetchData() {
        APICaller.getINSTANCE().getMovies(Sections.TRENDING).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movies.setValue(response.body().results);
            }
            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
            }
        });
    }
    void search(String query) {
        this.query = query;
        debounce.execute();
    }
}