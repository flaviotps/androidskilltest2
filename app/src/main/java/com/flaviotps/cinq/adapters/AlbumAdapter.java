package com.flaviotps.cinq.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviotps.cinq.R;
import com.flaviotps.cinq.model.AlbumModel;
import com.flaviotps.cinq.presenter.AlbumsPresenter;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {


    private List<AlbumModel> albums;
    private Context context;
    private AlbumsPresenter albumsPresenter;

    public AlbumAdapter(List<AlbumModel> albums, Context context, AlbumsPresenter albumsPresenter) {
        this.albums = albums;
        this.context = context;
        this.albumsPresenter = albumsPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View albumView = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_album, parent, false);

        AlbumAdapter.ViewHolder viewHolder = new AlbumAdapter.ViewHolder(albumView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.i("ALBUM", albums.get(i).toString());
        viewHolder.setAlbum(albums.get(i));
    }

    @Override
    public int getItemCount() {
        if (albums != null) {
            return albums.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView albumImage;
        private TextView albumTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumImage = itemView.findViewById(R.id.albumImage);
            albumTitle = itemView.findViewById(R.id.albumTitle);
        }

        public void setAlbum(AlbumModel album) {
            albumTitle.setText(album.getTitle());
            Glide.with(context).load(album.getThumbnailUrl()).into(albumImage);
        }
    }
}
