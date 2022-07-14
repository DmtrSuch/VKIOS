package com.example.appvk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private Context mContext;
    private ArrayList<AppItem> mAppList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public Adapter(Context context, ArrayList<AppItem> applist){
        mContext = context;
        mAppList = applist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.ex_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppItem currentItem = mAppList.get(position);

        String imageUrl = currentItem.getmImageUrl();
        String Name = currentItem.getName();
        String Descr = currentItem.getDescription();

        holder.mTextName.setText(Name);
        holder.mTextDescr.setText(Descr);
        Picasso.with(mContext)
                .load(imageUrl)
                .fit()
                .centerInside()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextName;
        public TextView mTextDescr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextName = itemView.findViewById(R.id.text_vie);
            mTextDescr = itemView.findViewById(R.id.text_vie_descr);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }

            });

        }
    }
}
