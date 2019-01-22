package com.flaviotps.cinq.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flaviotps.cinq.R;
import com.flaviotps.cinq.adapters.AlbumAdapter;
import com.flaviotps.cinq.interfaces.IAlbumsPresenter;
import com.flaviotps.cinq.model.AlbumModel;
import com.flaviotps.cinq.presenter.AlbumsPresenter;

import java.util.List;

public class AlbumsFragment extends Fragment implements IAlbumsPresenter.View {

    private View fragmentView;
    private AlbumAdapter albumAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AlbumsPresenter albumsPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_albums, container, false);
        albumsPresenter = new AlbumsPresenter(this, getContext());

        ViewBind();

        albumsPresenter.LoadAlbums();
        return fragmentView;
    }


    private void ViewBind() {
        recyclerView = fragmentView.findViewById(R.id.albumsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void OnAlbumsLoaded(List<AlbumModel> albums) {
        albumAdapter = new AlbumAdapter(albums, getContext(), albumsPresenter);
        recyclerView.setAdapter(albumAdapter);
    }

    @Override
    public void OnFail() {
        Toast.makeText(getContext(), "Failed to load", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (AlbumModel.getAlbumModels() != null && AlbumModel.getAlbumModels().size() > 0) {
            albumAdapter = new AlbumAdapter(AlbumModel.getAlbumModels(), getContext(), albumsPresenter);
            recyclerView.setAdapter(albumAdapter);
        }

    }
}


