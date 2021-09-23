package com.example.movieapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.movieapp.R;
import com.example.movieapp.databinding.FragmentUpdateUserBinding;
import com.google.android.ads.mediationtestsuite.activities.HomeActivity;
import com.google.firebase.firestore.auth.User;

public class UpdateUserFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final FragmentUpdateUserBinding fragmentUpdateUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_user,container,false);

        fragmentUpdateUserBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(fragmentUpdateUserBinding.id.getText().toString());
                String name = fragmentUpdateUserBinding.name.getText().toString();
                String email = fragmentUpdateUserBinding.email.getText().toString();

                User user = new User();

                user.setId(id);
                user.setName(name);
                user.setEmail(email);

                HomeActivity.myAppDatabase.myDao().updateUser(user);

                Toast.makeText(getContext(), "User updated successfully", Toast.LENGTH_SHORT).show();

            }
        });


        return fragmentUpdateUserBinding.getRoot();

    }
}