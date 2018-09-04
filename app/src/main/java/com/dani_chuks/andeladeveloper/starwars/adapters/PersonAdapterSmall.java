package com.dani_chuks.andeladeveloper.starwars.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Person;

import java.util.List;

public class PersonAdapterSmall extends RecyclerView.Adapter<PersonAdapterSmall.MyViewHolder> {

    List<Person> people;

    public PersonAdapterSmall(final List<Person> person) {
        this.people = person;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.small_person_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        bindPerson(myViewHolder, position);
    }

    private void bindPerson(final @NonNull MyViewHolder myViewHolder, final int position) {
        Person person = people.get(position);
        String name = myViewHolder.context.getString(R.string.card_name, person.getName());
        String gender = myViewHolder.context.getString(R.string.card_gender, person.getGender());
        String birthYear = myViewHolder.context.getString(R.string.card_birth_year, person.getBirthYear());
        String skinColor = myViewHolder.context.getString(R.string.card_skin_color, person.getSkinColor());
        myViewHolder.name.setText(name);
        myViewHolder.gender.setText(gender);
        myViewHolder.birthYear.setText(birthYear);
        myViewHolder.skinColor.setText(skinColor);
    }

    public void setPeope(List<Person> people){
        this.people = people;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView gender;
        TextView birthYear;
        TextView skinColor;
        Context context;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.gender);
            birthYear = itemView.findViewById(R.id.birth_year);
            skinColor = itemView.findViewById(R.id.skin_color);
            context = itemView.getContext();
        }
    }
}
