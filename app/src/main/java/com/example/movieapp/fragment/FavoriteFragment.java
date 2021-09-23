package com.example.movieapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.movieapp.R;
import com.example.movieapp.adapter.MovieAdapter;
import com.example.movieapp.data.Movieapp;
import com.example.movieapp.fragment.FavoriteFragment;
import com.example.movieapp.model.Result;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    import java.util.List;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public class FavoriteFragment extends Fragment{
        RecyclerView rcv;
        Movieapp movieapp;
        List<Result> result;
        MovieAdapter mvAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

        public FavoriteFragment() {

            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment FavoriteFragment.
             */
            // TODO: Rename and change types and number of parameters
            public static FavoriteFragment newInstance (String ARG_PARAM1, String param2){
                FavoriteFragment fragment = new FavoriteFragment();
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, ARG_PARAM1);
                args.putString(ARG_PARAM2, param2);
                fragment.setArguments(args);
                return fragment;
            }
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite1, container, false);
        View view = inflater.inflate(R.layout.fragment_favorite1,container,false);
        Context context = view.getContext();
        rcv = view.findViewById(R.id.rc_view_fragment_favorite);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        movieapp = Room.databaseBuilder(context, Movieapp.class,"movieDB").allowMainThreadQueries().build();
        result = movieapp.movieDao().getMovieData();
        rcv.setAdapter(new MovieAdapter(result,R.layout.movie_list_item,getActivity()));

        return view;
    }


    }
}
}