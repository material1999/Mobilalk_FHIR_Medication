package com.example.fhir_medication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fhir_medication.R;
import com.example.fhir_medication.activity.MedicationActivity;
import com.example.fhir_medication.fragment.MedicationFragment;
import com.example.fhir_medication.model.MedicationModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MedicationModelAdapter extends RecyclerView.Adapter<MedicationModelAdapter.ViewHolder> {

    private ArrayList<MedicationModel> mMedicationData;
    private ArrayList<MedicationModel> mMedicationDataAll;
    private Context mContext;
    private int lastPosition = -1;

    public MedicationModelAdapter(Context context, ArrayList<MedicationModel>itemsData) {
        this.mMedicationData = itemsData;
        this.mMedicationDataAll = itemsData;
        this.mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.medication_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MedicationModelAdapter.ViewHolder holder, int position) {
        MedicationModel currentItem = mMedicationData.get(position);
        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return mMedicationData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleText;
        private TextView mSubTitleText;
        private TextView mDetailsText;
        private ImageView mItemImage;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.itemTitle);
            mSubTitleText = itemView.findViewById(R.id.subTitle);
            mDetailsText = itemView.findViewById(R.id.details);
            mItemImage = itemView.findViewById(R.id.itemImage);
        }

        public void bindTo(MedicationModel currentItem) {

            mTitleText.setText(currentItem.getCode());
            mSubTitleText.setText(currentItem.getManufacturer());
            mDetailsText.setText(currentItem.getForm());

            Glide.with(mContext).load(currentItem.getImageResource())
                    .into(mItemImage);

            itemView.findViewById(R.id.edit).setOnClickListener(view ->
                    ((MedicationActivity)mContext).editItem(currentItem));
            itemView.findViewById(R.id.delete).setOnClickListener(view ->
                    ((MedicationActivity)mContext).deleteItem(currentItem));
        }
    };
}

