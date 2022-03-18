package com.example.marvel_arnau;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {
    private Context mContext;
    private List<Film> mFilms;

    public FilmAdapter(Context mContext, List<Film> mFilms) {
        this.mContext = mContext;
        this.mFilms = mFilms;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(mFilms.get(position).getUrlToImage())
                .fit()
                .centerCrop()
                .into(holder.urlToImage);
        holder.title.setText(mFilms.get(position).getTitle());
        holder.description.setText(mFilms.get(position).getDescription());
        holder.publishedAt.setText(mFilms.get(position).getPublishedAt());

        holder.filmLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);

                intent.putExtra("title", mFilms.get(position).getTitle());
                intent.putExtra("description", mFilms.get(position).getDescription());
                intent.putExtra("publishedAt", mFilms.get(position).getPublishedAt());
                intent.putExtra("urlToImage2", mFilms.get(position).getUrlToImage());
                intent.putExtra("content", mFilms.get(position).getContent());
                intent.putExtra("authorname", mFilms.get(position).getAuthorname());
                intent.putExtra("url", mFilms.get(position).getUrl());
                intent.putExtra("authorlink", mFilms.get(position).getAuthorlink());
                intent.putExtra("contact", mFilms.get(position).getContact());
                intent.putExtra("urlvideo", mFilms.get(position).getUrlvideo());
                intent.putExtra("media", mFilms.get(position).getMedia());
                intent.putExtra("comments", mFilms.get(position).getComments());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView urlToImage;
        TextView title;
        TextView description;
        TextView publishedAt;

        ConstraintLayout filmLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            urlToImage = itemView.findViewById(R.id.urlToImage);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            publishedAt = itemView.findViewById(R.id.publishedAt);

            filmLayout = itemView.findViewById(R.id.filmLayout);
        }
    }
}
