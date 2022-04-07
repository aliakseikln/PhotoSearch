package com.example.retrofitagain2;

import static androidx.core.app.ActivityCompat.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<Photo> photoList;
    Context context;

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
        requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        holder.tvTitle.setText(photoList.get(position).getTitle());
        Glide.with(context).load(photoList.get(position).getUrlS()).into(holder.url_s);
        holder.buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setMessage("Желаете загрузить картинку?")
                        .setCancelable(false)
                        .setPositiveButton("Нет", null)
                        .setNegativeButton("Да", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                try{
                                    DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                                    Uri downloadUri = Uri.parse(photoList.get(position).getUrlO());
                                    DownloadManager.Request request = new DownloadManager.Request((Uri)downloadUri);
                                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                                            .setAllowedOverRoaming(false)
                                            .setTitle("Photo")
                                            .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,File.separator + photoList.get(position).getTitle() + ".jpg");
                                    dm.enqueue(request);
                                    Toast.makeText(context, "Image download started.", Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    Log.e("TAG", "onFailedDownload: " + e.getMessage());
                                    Toast.makeText(context, "Image download failed.", Toast.LENGTH_SHORT).show();
                                }



//                                BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.url_s.getDrawable();
//                                Bitmap bitmap = bitmapDrawable.getBitmap();
//
//                                FileOutputStream outputStream = null;
//                                try {
//                                    String file = Environment.getExternalStoragePublicDirectory(
//                                            Environment.DIRECTORY_PICTURES).toString();
//                                    File dir = new File(file + "/ImagesFromFlickr");
//
//                                    if (!dir.exists()) {
//                                        dir.mkdirs();
//                                    }
//
//                                    String filename = String.format("%d.png", System.currentTimeMillis());     //    new Date().toString() + ".png";
//                                    File outFile = new File(dir, filename);
//                                    try {
//                                        outputStream = new FileOutputStream(outFile);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//
//                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//                                    try {
//                                        outputStream.flush();
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//
//                                    try {
//                                        outputStream.close();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }

///////////////////////////////////////////////////////////////////////////
//                                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//                                Uri uriFile = Uri.parse(photoList.get(position).getUrlS());
//                                DownloadManager.Request request = new DownloadManager.Request(uriFile);
//                                request.allowScanningByMediaScanner();
//                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                                request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, photoList.get(position).getTitle() + ".jpg");
//                                downloadManager.enqueue(request);
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