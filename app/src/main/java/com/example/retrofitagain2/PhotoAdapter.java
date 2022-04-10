package com.example.retrofitagain2;

import static androidx.core.app.ActivityCompat.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import retrofit2.http.Url;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<Photo> photoList;
    Context context;
    Presenter presenter;
    String urlo;
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

                urlo = photoList.get(position).getUrlO();
                photoTitle = photoList.get(position).getTitle();
                presenter = new Presenter();

                new AlertDialog.Builder(context)
                        .setMessage("Желаете загрузить картинку?")
                        .setCancelable(false)
                        .setPositiveButton("Нет", null)
                        .setNegativeButton("Да", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                presenter.buttonDownloadHasClicked(context, urlo, photoTitle);

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
        ImageView url_o;
        Button buttonDownload;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            url_s = itemView.findViewById(R.id.imageView);
            url_o = itemView.findViewById(R.id.imageView2);
            buttonDownload = itemView.findViewById(R.id.button);

        }
    }
}