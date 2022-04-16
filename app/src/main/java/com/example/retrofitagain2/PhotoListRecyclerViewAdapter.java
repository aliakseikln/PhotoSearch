package com.example.retrofitagain2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitagain2.interfaces.PhotoListPresenter;

import java.util.List;

public class PhotoListRecyclerViewAdapter extends RecyclerView.Adapter<PhotoListRecyclerViewAdapter.ViewHolder> {

    private final List<Photo> photoList;
    Context context;
    PhotoListPresenter presenter;
    String originalSizeOfPhotoUrl;
    String photoTitle;

    public PhotoListRecyclerViewAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoListRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        context = holder.itemView.getContext();
        holder.tvTitle.setText(photoList.get(position).getTitle());
        Glide.with(context).load(photoList.get(position).getUrlS()).into(holder.imageViewSizeS);

        holder.buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                originalSizeOfPhotoUrl = photoList.get(position).getUrlO();
                photoTitle = photoList.get(position).getTitle();

                new AlertDialog.Builder(context)
                        .setMessage("Желаете загрузить картинку?")
                        .setCancelable(false)
                        .setPositiveButton("Нет", null)
                        .setNegativeButton("Да", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                presenter.handleDownloadButtonClick(context, originalSizeOfPhotoUrl, photoTitle);
                            }
                        })
                        .show();
            }
        });

        if (holder.imageViewSizeS != null) {
            holder.imageViewSizeS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.imageViewSizeS.setDrawingCacheEnabled(true);
                    BitmapDrawable drawable = (BitmapDrawable) holder.imageViewSizeS.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    presenter.handleImageButtonClick(bitmap);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView imageViewSizeS;
        Button buttonDownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            imageViewSizeS = itemView.findViewById(R.id.photoImageView);
            buttonDownload = itemView.findViewById(R.id.buttonDownload);
        }
    }
}