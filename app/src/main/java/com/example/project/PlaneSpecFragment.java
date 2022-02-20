package com.example.project;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaneSpecFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaneSpecFragment extends Fragment {

    public PlaneSpecFragment() {
        // Required empty public constructor
        //todo make a button
    }

    public static PlaneSpecFragment newInstance() {
        PlaneSpecFragment fragment = new PlaneSpecFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(((AppCompatActivity)
                requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        assert bundle != null;
        String name = bundle.getString("name");
        String description = bundle.getString("description");
        int crew = bundle.getInt("crew");
        int planeLength = bundle.getInt("planeLength");
        int enginesAmount = bundle.getInt("enginesAmount");
        float wingSize = bundle.getFloat("wingSize");
        float wingSize1 = bundle.getFloat("wingSize1");
        String image_src = bundle.getString("image_src");
        View view = inflater.inflate(R.layout.fragment_plane_spec, container, false);
        TextView planeName = view.findViewById(R.id.plane_name);
        TextView planeDescription = view.findViewById(R.id.description_plane);
        TextView plane_crew = view.findViewById(R.id.crew);
        TextView plane_planeLength = view.findViewById(R.id.plane_length);
        TextView plane_enginesAmount = view.findViewById(R.id.engines_amount);
        TextView plane_wingSize = view.findViewById(R.id.wing_size);
        TextView plane_wingSize1 = view.findViewById(R.id.wing_size_1);
        ImageView planeImg = view.findViewById(R.id.plane_image);
        planeDescription.setText("description: "+description);
        plane_crew.setText("crew amount: "+String.valueOf(crew));
        plane_enginesAmount.setText("engines amount: "+String.valueOf(enginesAmount));
        plane_planeLength.setText("length: "+String.valueOf(planeLength));
        plane_wingSize.setText("wing's size: "+String.valueOf(wingSize));
        plane_wingSize1.setText("wing's size too lol: "+String.valueOf(wingSize1));
        planeName.setText(name);
        Glide.with(getContext()).load(image_src).into(planeImg);
        Log.d("imageDebug", image_src);
        return view;
    }
}