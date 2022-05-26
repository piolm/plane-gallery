package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    private ArrayList<Plane> planes = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://project1-aea35-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference reference = database.getReference();
        planes.clear();
        PlaneAdapter adapter = new PlaneAdapter(getContext(), planes);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("Firebase", "Error while getting data from database", task.getException());
                } else {
                    DataSnapshot s = task.getResult();
                    for (DataSnapshot snap:s.getChildren()) {
                        Log.d("SnapShotLoader", snap.toString());
                        planes.add(snap.getValue(Plane.class));
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(adapter);
        return v;
    }
}
class PlaneAdapter extends RecyclerView.Adapter<PlaneAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Plane> planes;

    public PlaneAdapter(Context context, List<Plane> planes) {
        this.planes = planes;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.plane_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plane p = planes.get(position);
        holder.name.setText(p.getName());
        Fragment a = new PlaneSpecFragment();
        Bundle b = new Bundle();
        b.putString("name", p.getName());
        b.putString("description", p.getDescription());
        b.putInt("enginesAmount", p.getEnginesAmount());
        b.putInt("planeLength", p.getPlaneLength());
        b.putInt("crew", p.getCrew());
        b.putFloat("wingSize", p.getWingSize());
        b.putFloat("wingSize1", p.getWingSize1());
        b.putString("image_src", p.getImage_src());
        b.putString("video_link", p.getVideo_link());
        a.setArguments(b);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), p.getName(), Toast.LENGTH_LONG).show();
                AppCompatActivity act = (AppCompatActivity) (v.getContext());
                FragmentTransaction ft = act.getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("a");
                ft.replace(R.id.main_fragment, a, p.getName());
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return planes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final MaterialCardView layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.plane_layout);
            name = itemView.findViewById(R.id.plane_name);
        }
    }
}
