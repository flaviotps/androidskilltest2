package com.flaviotps.cinq.interfaces;

import com.flaviotps.cinq.model.AlbumModel;

import java.util.List;

public interface IAlbumsPresenter {

    interface Presenter {
        void LoadAlbums();
    }

    interface View {
        void OnAlbumsLoaded(List<AlbumModel> albums);

        void OnFail();
    }
}
