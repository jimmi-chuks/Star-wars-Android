package com.dani_chuks.andeladeveloper.starwars.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Film;

import java.util.List;

public class FilmAdapterSmall extends RecyclerView.Adapter<FilmAdapterSmall.MyViewHolder> {

    List<Film> films;

    public FilmAdapterSmall(final List<Film> films) {
        this.films = films;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.small_film_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        bindFilm(myViewHolder, position);
    }

    private void bindFilm(final @NonNull MyViewHolder myViewHolder, final int position) {
        Film film = films.get(position);
        String title = myViewHolder.context.getString(R.string.card_title, film.getTitle());
        String openingCrawl = myViewHolder.context.getString(R.string.card_opening_crawl, film.getOpeningCrawl());
        String releaseDate = myViewHolder.context.getString(R.string.card_release_date, film.getReleaseDate());
        myViewHolder.title.setText(title);
        myViewHolder.openingCrawl.setText(openingCrawl);
        myViewHolder.releaseDate.setText(releaseDate);
    }

    public void setFilms(List<Film> films){
        this.films = films;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(final int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    interface UpdateViewHolde{
        void bindView();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView openingCrawl;
        TextView releaseDate;
        Context context;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            openingCrawl = itemView.findViewById(R.id.opening_crawl);
            releaseDate = itemView.findViewById(R.id.release_date);
            context = itemView.getContext();
        }
    }
}
