package com.flaviotps.cinq.interfaces;

import com.flaviotps.cinq.model.AlbumModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAlbumsService {

    @GET("photos")
    Call<List<AlbumModel>> getAlbums();


}