package com.flaviotps.cinq.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flaviotps.cinq.R;
import com.flaviotps.cinq.model.UserModel;
import com.flaviotps.cinq.presenter.HomePresenter;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private List<UserModel> users;
    private Context context;
    private HomePresenter homePresenter;

    public UserAdapter(List<UserModel> users, Context context, HomePresenter homePresenter) {
        this.users = users;
        this.context = context;
        this.homePresenter = homePresenter;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View userView = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setUser(users.get(i));
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;
        private ImageButton imageButtonDelete;
        private ImageButton imageButtonEdit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            email = itemView.findViewById(R.id.txtEmail);
            imageButtonDelete = itemView.findViewById(R.id.imageButtonDelete);
            imageButtonEdit = itemView.findViewById(R.id.imageButtonEdit);


        }

        public void setUser(final UserModel user) {
            this.name.setText(user.getName());
            this.email.setText(user.getEmail());
            this.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homePresenter.RemoveUser(user);
                }
            });

            this.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homePresenter.EditUser(user);
                }
            });

        }

    }
}
