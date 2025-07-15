package com.godspeed.gameschhalaang.community.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.godspeed.gameschhalaang.community.model.Person;
import com.godspeed.gameskraftchhalaang.R;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> personList;

    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.personNameTextView.setText(person.getName());
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged(); // Notify adapter of the data change
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView personNameTextView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            personNameTextView = itemView.findViewById(R.id.personNameTextView);
        }
    }
}

