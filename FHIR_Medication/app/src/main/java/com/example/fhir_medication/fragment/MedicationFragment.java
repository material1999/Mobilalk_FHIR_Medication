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
import android.widget.Toast;

import com.example.fhir_medication.R;
import com.example.fhir_medication.adapter.MedicationModelAdapter;
import com.example.fhir_medication.model.BatchModel;
import com.example.fhir_medication.model.IngredientModel;
import com.example.fhir_medication.model.MedicationModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
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
    private static ArrayList<MedicationModel> mItemsData;
    private static MedicationModelAdapter mAdapter;
    private int gridNumber = 2;

    private FirebaseFirestore mFirestore;
    private static CollectionReference mItems;

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

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Medication");
        queryData();

        return view;
    }

    private static void initializeData() {
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
                Arrays.asList("medication02", "medication002")), "code02",
                "status02", "manufacturer02", "form02",
                20,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("ingredient02", true, 20),
                        new IngredientModel("ingredient03", false, 5))),
                new BatchModel("batch02",
                        new GregorianCalendar(2022, Calendar.FEBRUARY, 02).getTime()),
                R.drawable.ic_baseline_person_24);
        mItems.add(item2);

        MedicationModel item3 = new MedicationModel(new ArrayList<>(
                Arrays.asList("medication03", "medication003")), "code03",
                "status03", "manufacturer03", "form03",
                30,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("ingredient03", true, 30),
                        new IngredientModel("ingredient04", false, 5))),
                new BatchModel("batch03",
                        new GregorianCalendar(2023, Calendar.MARCH, 03).getTime()),
                R.drawable.ic_baseline_person_24);
        mItems.add(item3);

        MedicationModel item4 = new MedicationModel(new ArrayList<>(
                Arrays.asList("medication04", "medication004")), "code04",
                "status04", "manufacturer04", "form04",
                40,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("ingredient04", true, 40),
                        new IngredientModel("ingredient05", false, 5))),
                new BatchModel("batch04",
                        new GregorianCalendar(2024, Calendar.APRIL, 04).getTime()),
                R.drawable.ic_baseline_person_24);
        mItems.add(item4);

        MedicationModel item5 = new MedicationModel(new ArrayList<>(
                Arrays.asList("medication05", "medication005")), "code05",
                "status05", "manufacturer05", "form05",
                50,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("ingredient05", true, 50),
                        new IngredientModel("ingredient06", false, 5))),
                new BatchModel("batch05",
                        new GregorianCalendar(2025, Calendar.MAY, 05).getTime()),
                R.drawable.ic_baseline_person_24);
        mItems.add(item5);

        MedicationModel item6 = new MedicationModel(new ArrayList<>(
                Arrays.asList("medication06", "medication006")), "code06",
                "status06", "manufacturer06", "form06",
                60,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("ingredient06", true, 60),
                        new IngredientModel("ingredient07", false, 5))),
                new BatchModel("batch06",
                        new GregorianCalendar(2026, Calendar.JUNE, 06).getTime()),
                R.drawable.ic_baseline_person_24);
        mItems.add(item6);
    }

    private static void queryData() {
        mItemsData.clear();
        mItems.orderBy("code", Query.Direction.ASCENDING).limit(10).get()
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

    public static void deleteItem(MedicationModel item) {
        DocumentReference ref = mItems.document(item._getId());
        ref.delete()
                .addOnSuccessListener(success -> {
                    Log.d(LOG_TAG, "Item is successfully deleted: " + item._getId());
                })
                .addOnFailureListener(fail -> {
                    Log.d(LOG_TAG, "Item cannot be deleted: " + item._getId());
                });
        queryData();
    }

    public static void editItem(MedicationModel item) {
        //TODO: edit item method
        Log.d(LOG_TAG, "Edit item called: " + item._getId());
    }

}