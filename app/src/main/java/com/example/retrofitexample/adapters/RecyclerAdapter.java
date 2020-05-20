package com.example.retrofitexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrofitexample.R;
import com.example.retrofitexample.RetrofitClass;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomAdapter> {

    private List<RetrofitClass> dataList;
    private Context context;

    public RecyclerAdapter(Context context, List<RetrofitClass> dataList) {
        this.dataList = dataList;
        this.context = context;
    }


    class CustomAdapter extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtTitle;
        ImageView coverImage;

        public CustomAdapter(View itemView) {
            super(itemView);
            this.mView = itemView;

            txtTitle = mView.findViewById(R.id.imageName);
            coverImage = mView.findViewById(R.id.imageplaceholder);
        }
    }
    @NonNull
    @Override
    public CustomAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerviewlayout, parent, false);
        return new CustomAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter holder, int position) {

        holder.txtTitle.setText(dataList.get(position).getTitle()); // setting Image text.

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



}
