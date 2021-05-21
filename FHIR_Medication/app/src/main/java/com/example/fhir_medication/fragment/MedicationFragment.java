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
import com.example.fhir_medication.model.BatchModel;
import com.example.fhir_medication.model.IngredientModel;
import com.example.fhir_medication.model.MedicationModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MedicationFragment extends Fragment {

    private static final String LOG_TAG = MedicationFragment.class.getName();

    private FloatingActionButton floatingActionButton;

    private RecyclerView mRecyclerView;
    private ArrayList<MedicationModel> mItemsData;
    private MedicationModelAdapter mAdapter;
    private int gridNumber = 1;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

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
        mAdapter = new MedicationModelAdapter(this.getContext(), mItemsData);
        mRecyclerView.setAdapter(mAdapter);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Medication");
        queryData();

        return view;
    }

    private void initializeData() {
        MedicationModel item1 = new MedicationModel(new ArrayList<>(
                Arrays.asList("medication01", "medication001")), "code01",
                "status01", "manufacturer01", "form01",
                10,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("ingredient01", true, 10),
                        new IngredientModel("ingredient02", false, 5))),
                new BatchModel("batch01",
                        new GregorianCalendar(2021, Calendar.JANUARY, 01).getTime()),
                R.drawable.ic_baseline_person_24);
        mItems.add(item1);
        MedicationModel item2 = new MedicationModel(new ArrayList<>(
                Arrays.asList("medication01", "medication001")), "code01",
                "status01", "manufacturer01", "form01",
                10,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("ingredient01", true, 10),
                        new IngredientModel("ingredient02", false, 5))),
                new BatchModel("batch01",
                        new GregorianCalendar(2021, Calendar.JANUARY, 01).getTime()),
                R.drawable.ic_baseline_person_24);
        mItems.add(item2);

        Log.d(LOG_TAG, "added");
    }

    private void queryData() {
        Log.d(LOG_TAG, "in querydata");
        mItemsData.clear();
        mItems.orderBy("code", Query.Direction.DESCENDING).limit(10).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        MedicationModel item = document.toObject(MedicationModel.class);
                        item.setId(document.getId());
                        mItemsData.add(item);
                    }
                    if (mItemsData.size() == 0) {
                        initializeData();
                        Log.d(LOG_TAG, "Run initialize");
                        queryData();
                    }
                    mAdapter.notifyDataSetChanged();
                });
    }

}