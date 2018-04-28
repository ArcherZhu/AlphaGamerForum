package com.shiro.issolution.gamerforum.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model.Plate;

import java.util.List;

public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.ViewHolder> {
    private List<Plate> mPlateList;
    private OnPlateListener onPlateListener;

    public PlateAdapter(List<Plate> mPlateList) {
        this.mPlateList = mPlateList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_plate_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Plate plate = mPlateList.get(position);
        final String plateId = plate.getPlateId();
        final String plateName = plate.getPlateName();
        //holder.imageIcon.setImageResource(plate.getImageId());
        holder.plateName.setText(plateName);
        if (onPlateListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPlateListener.onPlateClick(position, plateId, plateName);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPlateList.size();
    }

    public void setOnPlateListener(OnPlateListener onPlateListener) {
        this.onPlateListener = onPlateListener;
    }

    public interface OnPlateListener {
        void onPlateClick(int position, String plateId,String plateName);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageIcon;
        TextView plateName;


        public ViewHolder(View view) {
            super(view);
            imageIcon = (ImageView) view.findViewById(R.id.img_icon);
            plateName = (TextView) view.findViewById(R.id.txt_plate_name);
        }
    }
}
