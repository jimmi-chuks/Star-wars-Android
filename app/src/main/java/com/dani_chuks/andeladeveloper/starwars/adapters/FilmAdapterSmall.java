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

public class FilmAdapterSmall extends RecyclerView.Adapter<FilmAdapterSmall.FilmViewHolder> {

    List<Film> films;

    public FilmAdapterSmall(final List<Film> films) {
        this.films = films;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.small_film_item, viewGroup, false);
        return new FilmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilmViewHolder filmViewHolder, final int position) {
        bindFilm(filmViewHolder, position);
    }

    private void bindFilm(final @NonNull FilmViewHolder filmViewHolder, final int position) {
        Film film = films.get(position);
        String title = filmViewHolder.context.getString(R.string.card_title, film.getTitle());
        String openingCrawl = filmViewHolder.context.getString(R.string.card_opening_crawl, film.getOpeningCrawl());
        String releaseDate = filmViewHolder.context.getString(R.string.card_release_date, film.getReleaseDate());
        filmViewHolder.title.setText(title);
        filmViewHolder.openingCrawl.setText(openingCrawl);
        filmViewHolder.releaseDate.setText(releaseDate);
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

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView openingCrawl;
        TextView releaseDate;
        Context context;

        public FilmViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            openingCrawl = itemView.findViewById(R.id.opening_crawl);
            releaseDate = itemView.findViewById(R.id.release_date);
            context = itemView.getContext();
        }
    }
}
