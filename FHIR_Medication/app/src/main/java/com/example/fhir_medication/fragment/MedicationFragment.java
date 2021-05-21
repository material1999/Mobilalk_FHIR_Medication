package com.example.fhir_medication.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fhir_medication.R;
import com.example.fhir_medication.adapter.MedicationModelAdapter;
import com.example.fhir_medication.model.MedicationModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MedicationFragment extends Fragment {

    private static final String LOG_TAG = MedicationFragment.class.getName();

    private FloatingActionButton floatingActionButton;

    private RecyclerView mRecyclerView;
    private ArrayList<MedicationModel> mItemsData;
    private MedicationModelAdapter mAdapter;
    private int gridNumber = 1;

    public MedicationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medication, container, false);

        floatingActionButton = view.findViewById(R.id.addMedicationButton);
        floatingActionButton.setOnClickListener(v -> {
            // TODO: implement medication add
            Log.d(LOG_TAG, "Adding medication");
        });

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(
                getContext(), gridNumber));
        mItemsData = new ArrayList<>();
        mAdapter = new MedicationModelAdapter(getContext(), mItemsData);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}