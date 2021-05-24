package com.example.fhir_medication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fhir_medication.R;
import com.example.fhir_medication.adapter.MedicationModelAdapter;
import com.example.fhir_medication.model.MedicationModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class VaccinesFragment extends Fragment {

    private final String LOG_TAG = VaccinesFragment.class.getName();

    private RecyclerView mRecyclerView;
    private ArrayList<MedicationModel> mItemsData;
    private MedicationModelAdapter mAdapter;
    private int gridNumber = 1;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

    public VaccinesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_vaccines, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(
                getContext(), gridNumber));
        mItemsData = new ArrayList<>();
        mAdapter = new MedicationModelAdapter(getContext(), mItemsData);
        mRecyclerView.setAdapter(mAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Medication");

        return view;
    }

    private void queryData() {
        Log.d(LOG_TAG, "query");
        mItemsData.clear();
        mItems.whereIn("form",
                        new ArrayList<String>(Arrays.asList("injection", "Injection",
                                "injection solution", "Injection solution",
                                "injection Solution", "Injection Solution")))
                .orderBy("code", Query.Direction.ASCENDING).limit(5).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        MedicationModel item = document.toObject(MedicationModel.class);
                        item.setId(document.getId());
                        mItemsData.add(item);
                    }
                    mAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        queryData();
    }
}