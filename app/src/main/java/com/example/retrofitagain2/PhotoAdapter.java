package com.example.retrofitagain2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private final List<Photo> photoList;
    Context context;
    Presenter presenter;
    String originalSizeOfPhotoUrl;
    String photoTitle;

    public PhotoAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        context = holder.itemView.getContext();
        holder.tvTitle.setText(photoList.get(position).getTitle());
        Glide.with(context).load(photoList.get(position).getUrlS()).into(holder.url_s);

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

                                presenter.buttonDownloadHasClicked(context, originalSizeOfPhotoUrl, photoTitle);

                            }
                        })
                        .show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView url_s;
        Button buttonDownload;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            url_s = itemView.findViewById(R.id.photoImageView);
            buttonDownload = itemView.findViewById(R.id.buttonDownload);

        }
    }
}