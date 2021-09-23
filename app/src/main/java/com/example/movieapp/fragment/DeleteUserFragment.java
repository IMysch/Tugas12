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
import com.example.movieapp.databinding.FragmentDeleteUserBinding;
import com.google.android.ads.mediationtestsuite.activities.HomeActivity;
import com.google.firebase.firestore.auth.User;

public class DeleteUserFragment<LayoutInflater> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final FragmentDeleteUserBinding deleteUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delete_user,container,false);


        deleteUserBinding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();

                if(deleteUserBinding.deleteId.getText().toString().length()>0)
                    user.setId(Integer.parseInt(deleteUserBinding.deleteId.getText().toString()));

                HomeActivity.myAppDatabase.myDao().deletUser(user);
            }
        });

        return deleteUserBinding.getRoot();
    }
}