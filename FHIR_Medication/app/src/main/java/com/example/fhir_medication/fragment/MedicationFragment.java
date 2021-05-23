package com.example.fhir_medication.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fhir_medication.R;
import com.example.fhir_medication.activity.AddMedicationActivity;
import com.example.fhir_medication.activity.EditMedicationActivity;
import com.example.fhir_medication.activity.MedicationActivity;
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

    private static Context mContext;

    public MedicationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medication, container, false);

        floatingActionButton = view.findViewById(R.id.addMedicationButton);
        floatingActionButton.setOnClickListener(v -> {
            addItem();
        });

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

    private static void initializeData() {
        MedicationModel item1 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0301", "medication0301")), "Vancomycin Hydrochloride",
                "active", "Pfizer Laboratories Div Pfizer Inc", "Injection Solution",
                null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Vancomycin Hydrochloride", true, 500))),
                new BatchModel("9494788",
                        new GregorianCalendar(2017, Calendar.MAY, 22).getTime()),
                R.mipmap.ic_01);
        mItems.add(item1);

        MedicationModel item2 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0302", "medication0302")), "Zosyn (piperacillin/tazobactam) 4.5gm injection",
                null, "Wyeth Pharmaceuticals Inc", "Injection solution",
                null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Piperacillin Sodium", null, 400),
                        new IngredientModel("Tazobactam Sodium", null, 500))),
                new BatchModel(null,null),
                R.mipmap.ic_02);
        mItems.add(item2);

        MedicationModel item3 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0303", "medication0303")), "Alemtuzumab 10mg/ml (Lemtrada)",
                null, "Genzyme", "Injection solution",
                null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Alemtuzamab (substance)", null, 12))),
                new BatchModel("9494788",
                        new GregorianCalendar(2017, Calendar.MAY, 22).getTime()),
                R.mipmap.ic_03);
        mItems.add(item3);

        MedicationModel item4 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0304", "medication0304")), "Myleran 2mg tablet, film coated",
                null, "Aspen Global Inc", "Film-coated tablet",
                null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Busulfan (substance)", null, 2))),
                new BatchModel("9494788",
                        new GregorianCalendar(2017, Calendar.MAY, 22).getTime()),
                R.mipmap.ic_04);
        mItems.add(item4);

        MedicationModel item5 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0305", "medication0305")), "Timoptic 5mg/ml solution",
                null, "Aton Pharma Inc", "Opthalmic Solution",
                null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Timolol Maleate (substance)", null, 5))),
                new BatchModel("9494788",
                        new GregorianCalendar(2017, Calendar.MAY, 22).getTime()),
                R.mipmap.ic_05);
        mItems.add(item5);

        MedicationModel item6 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0306", "medication0606")), "Adcetris",
                null, "Seattle Genetics Inc", "Lyophilized powder for injectable solution",
                null,
                new ArrayList<>(Arrays.asList()),
                new BatchModel("12345",
                        new GregorianCalendar(2019, Calendar.OCTOBER, 31).getTime()),
                R.mipmap.ic_06);
        mItems.add(item6);

        MedicationModel item7 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0307", "medication0307")), "Novolog 100u/ml",
                null, "Novo Nordisk", "Injection Solution",
                null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Insulin Aspart (substance)", null, 100))),
                new BatchModel("12345",
                        new GregorianCalendar(2019, Calendar.OCTOBER, 31).getTime()),
                R.mipmap.ic_07);
        mItems.add(item7);

        MedicationModel item8 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0308", "medication0308")), "Percocet tablet",
                null, "Stat Rx USA LLC", "Tablet dose form",
                null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Oxycodone HCl", null, 5),
                        new IngredientModel("Acetaminophen", null, 325))),
                new BatchModel("658484",
                        new GregorianCalendar(2020, Calendar.JULY, 21).getTime()),
                R.mipmap.ic_08);
        mItems.add(item8);

        MedicationModel item9 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0309", "medication0309")), "Tylenol PM",
                null, "Johnson and Johnson Consume Inc, McNeil Consumer Healthcare Division",
                "Film-coated tablet",null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Acetaminophen 500 MG", null, 500),
                        new IngredientModel("Diphenhydramine Hydrochloride 25 mg", null, 25))),
                new BatchModel("9494788",
                        new GregorianCalendar(2017, Calendar.MAY, 22).getTime()),
                R.mipmap.ic_09);
        mItems.add(item9);

        MedicationModel item10 = new MedicationModel(new ArrayList<>(
                Arrays.asList("med0310", "medication0310")), "Capecitabine 500mg oral tablet (Xeloda)",
                null, "Gene Inc", "Tablet dose form",
                null,
                new ArrayList<>(Arrays.asList(
                        new IngredientModel("Capecitabine (substance)", null, 500))),
                new BatchModel("9494788",
                        new GregorianCalendar(2017, Calendar.MAY, 22).getTime()),
                R.mipmap.ic_10);
        mItems.add(item10);
    }

    private static void queryData() {
        mItemsData.clear();
        mItems.orderBy("code", Query.Direction.ASCENDING).limit(20).get()
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
                    Toast.makeText(mContext,
                            "Item deleted successfully!", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(fail -> {
                    Log.d(LOG_TAG, "Item cannot be deleted: " + item._getId());
                    Toast.makeText(mContext,
                            "Item cannot be deleted!", Toast.LENGTH_LONG).show();
                });
        queryData();
    }

    public static void editItem(MedicationModel item) {
        Log.d(LOG_TAG, "Edit item called: " + item._getId());
        editActivityStart(item);
    }

    public void addItem() {
        Log.d(LOG_TAG, "Adding medication");
        addActivityStart();
    }

    private static void editActivityStart(MedicationModel item) {
        Intent intent = new Intent(mContext, EditMedicationActivity.class);
        intent.putExtra("item", item);
        mContext.startActivity(intent);
    }

    private void addActivityStart() {
        Intent intent = new Intent(getContext(), AddMedicationActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        queryData();
    }
}