package com.example.movieapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.movieapp.R;
import com.example.movieapp.databinding.FragmentReadUserBinding;
import com.google.android.ads.mediationtestsuite.activities.HomeActivity;
import com.google.firebase.firestore.auth.User;

public class ReadUserFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentReadUserBinding fragmentReadUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_read_user, container, false);

        List<User> users = HomeActivity.myAppDatabase.myDao().getUsers();

        String info = "";

        for(User user: users){

            int id = user.getId();

            String name = user.getName();

            String email = user.getEmail();

            info = info +"id : "+ id+"\n"+ "name : " + name+" \n";
        }

        fragmentReadUserBinding.userDetails.setText(info);

        return fragmentReadUserBinding.getRoot();
    }
}