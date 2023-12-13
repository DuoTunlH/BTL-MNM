package com.example.btl_mnm.managers;

import com.example.btl_mnm.model.Movie;
import com.example.btl_mnm.model.MoviesResponse;
import com.example.btl_mnm.model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MovieService {
    @GET
    Call<MoviesResponse> getMovies(@Url String url);
    @GET
    Call<Movie> getMovieById(@Url String url);
    @GET
    Call<TrailersResponse> getTrailers(@Url String url);
    @GET
    Call<MoviesResponse> search(@Url String url);
}
