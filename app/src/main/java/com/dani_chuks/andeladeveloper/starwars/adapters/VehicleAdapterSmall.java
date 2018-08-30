package com.dani_chuks.andeladeveloper.starwars.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dani_chuks.andeladeveloper.starwars.R;
import com.dani_chuks.andeladeveloper.starwars.data.models.entities.Vehicle;

import java.util.List;

public class VehicleAdapterSmall extends RecyclerView.Adapter<VehicleAdapterSmall.MyViewHolder> {

    List<Vehicle> vehicles;

    public VehicleAdapterSmall(final List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.small_vehicle_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        Vehicle vehicle = vehicles.get(position);
        String name = myViewHolder.context.getString(R.string.card_title, vehicle.getName());
        String crew = myViewHolder.context.getString(R.string.card_crew, vehicle.getCrew());
        String passenger = myViewHolder.context.getString(R.string.card_passenger, vehicle.getPassengers());
        String vehicleClass = myViewHolder.context.getString(R.string.card_vehicle_class, vehicle.getVehicleClass());
        myViewHolder.name.setText(name);
        myViewHolder.crew.setText(crew);
        myViewHolder.passenger.setText(passenger);
        myViewHolder.vehicleClass.setText(vehicleClass);
    }

    public void setVehicles(List<Vehicle> vehicles){
        this.vehicles = vehicles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView crew;
        TextView passenger;
        TextView vehicleClass;
        Context context;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            crew = itemView.findViewById(R.id.crew);
            passenger = itemView.findViewById(R.id.passenger);
            vehicleClass = itemView.findViewById(R.id.vehicle_class);
            context = itemView.getContext();
        }


    }
}
