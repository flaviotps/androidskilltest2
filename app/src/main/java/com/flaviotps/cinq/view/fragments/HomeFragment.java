package com.flaviotps.cinq.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviotps.cinq.R;
import com.flaviotps.cinq.adapters.UserAdapter;
import com.flaviotps.cinq.interfaces.IHomePresenter;
import com.flaviotps.cinq.model.UserModel;
import com.flaviotps.cinq.presenter.HomePresenter;
import com.flaviotps.cinq.view.activities.LoginActivity;
import com.flaviotps.cinq.view.activities.RegisterActivity;

import java.util.List;

public class HomeFragment extends Fragment implements IHomePresenter.View {


    private View fragmentView;
    private UserAdapter userAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomePresenter homePresenter;

    private TextView textViewUserName;


    @Override
    public void onResume() {
        super.onResume();
        homePresenter.LoadUsers();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        homePresenter = new HomePresenter(this, getContext());
        ViewBind();

        return fragmentView;
    }

    private void ViewBind() {
        recyclerView = fragmentView.findViewById(R.id.usersRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        textViewUserName = fragmentView.findViewById(R.id.textViewUserName);

        if (UserModel.getCurrentUser() != null) {
            textViewUserName.setText(UserModel.getCurrentUser().getName());
        }
    }

    @Override
    public void ShowUsers(List<UserModel> users) {
        userAdapter = new UserAdapter(users, getContext(), homePresenter);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void OnUserDeleted() {
        Toast.makeText(getContext(), "Deleted successfully", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_add:
                startActivity(new Intent(this.getActivity(), RegisterActivity.class));
                return true;

            case R.id.app_bar_logout:
                UserModel.setCurrentUser(null);
                homePresenter.DestroySession();
                startActivity(new Intent(this.getActivity(), LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        MenuItem search = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) search.getActionView();

        EditText et = searchView.findViewById(searchView.getContext().getResources()
                .getIdentifier("android:id/search_src_text", null, null));
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                homePresenter.FilterUsers(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                homePresenter.FilterUsers(newText);
                return false;
            }
        });
    }
}
