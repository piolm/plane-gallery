package com.example.project;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
        final Bundle[] bundle = {this.getArguments()};
        assert bundle[0] != null;
        String link = bundle[0].getString("video_link");
        final Intent[] intent = {new Intent(Intent.ACTION_VIEW, Uri.parse(link))};
        String code = link.substring((link.lastIndexOf("=")+1));
        Log.d("code", code);
        final Intent[] appIntent = {new Intent(Intent.ACTION_VIEW,
                Uri.parse("vnd.youtube:"+code))};
        final String[] name = {bundle[0].getString("name")};
        String description = bundle[0].getString("description");
        int crew = bundle[0].getInt("crew");
        int planeLength = bundle[0].getInt("planeLength");
        int enginesAmount = bundle[0].getInt("enginesAmount");
        float wingSize = bundle[0].getFloat("wingSize");
        float wingSize1 = bundle[0].getFloat("wingSize1");
        String image_src = bundle[0].getString("image_src");
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
        planeName.setText(name[0]);
        Glide.with(getContext()).load(image_src).into(planeImg);
        planeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(appIntent[0]);
                } catch (ActivityNotFoundException e) {
                    startActivity(intent[0]);
                }

            }
        });
        Log.d("imageDebug", image_src);
        return view;
    }
}