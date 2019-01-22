package com.flaviotps.cinq.model;

import android.content.Context;

import com.flaviotps.cinq.interfaces.IAlbumsPresenter;
import com.flaviotps.cinq.interfaces.IAlbumsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumDAO {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private Context context;

    public AlbumDAO(Context context) {
        this.context = context;
    }

    public void loadAlbums(final IAlbumsPresenter.View view) {


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAlbumsService AlbumsService = retrofit.create(IAlbumsService.class);

        final Call<List<AlbumModel>> albums = AlbumsService.getAlbums();

        albums.enqueue(new Callback<List<AlbumModel>>() {
            @Override
            public void onResponse(Call<List<AlbumModel>> call, Response<List<AlbumModel>> response) {
                if (response.isSuccessful()) {
                    AlbumModel.setAlbumModels(response.body());
                    view.OnAlbumsLoaded(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<AlbumModel>> call, Throwable t) {
                view.OnFail();
            }
        });
    }

}
