package com.example.retrofit_example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_example.R;
import com.example.retrofit_example.response.ArticleData;
import com.example.retrofit_example.response.Titles;
import com.squareup.picasso.Picasso;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ArticleViewHolder> {

    private Context context;
    private ArticleData articleData;

    public ArticleRecyclerViewAdapter(Context context, ArticleData articleData) {
        this.context = context;
        this.articleData = articleData;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_articles, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.articleTitle.setText(getTitle(articleData.getData().get(position).getAttributes().getTitles()));
        Picasso.get().load(articleData.getData().get(position).getAttributes().getPosterImage().getSmall()).into(holder.articleImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.articleTitle.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleData.getData().size();
    }

    private String getTitle(Titles titles) {
        String title = "No title found";
        if (titles.getEnUs() != null) {
            title = titles.getEnUs();
        } else if (titles.getEn() != null) {
            title = titles.getEn();
        } else if (titles.getEnJp() != null) {
            title = titles.getEnJp();
        }
        if (title.isEmpty()) {
            title = "No title found";
        }
        return title;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView articleImage;
        private TextView articleTitle;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.item_article_image);
            articleTitle = itemView.findViewById(R.id.item_article_title);
            cardView =itemView.findViewById(R.id.item_card_view);
        }
    }
}
