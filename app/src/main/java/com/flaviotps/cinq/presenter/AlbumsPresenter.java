package com.flaviotps.cinq.presenter;

import android.content.Context;

import com.flaviotps.cinq.interfaces.IAlbumsPresenter;
import com.flaviotps.cinq.model.AlbumDAO;
import com.flaviotps.cinq.model.AlbumModel;

public class AlbumsPresenter implements IAlbumsPresenter.Presenter {


    private IAlbumsPresenter.View view;
    private Context context;
    private AlbumDAO albumDAO;

    public AlbumsPresenter(IAlbumsPresenter.View view, Context context) {
        this.view = view;
        this.context = context;
        this.albumDAO = new AlbumDAO(context);
    }

    @Override
    public void LoadAlbums() {
        if (AlbumModel.getAlbumModels() == null || AlbumModel.getAlbumModels().size() == 0) {
            albumDAO.loadAlbums(view);
        }
    }

}
