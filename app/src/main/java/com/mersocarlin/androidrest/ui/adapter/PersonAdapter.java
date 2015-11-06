package com.mersocarlin.androidrest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.inject.Inject;
import com.mersocarlin.androidrest.R;
import com.mersocarlin.androidrest.domain.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends BaseAdapter {

    @Inject
    private Context context;

    private LayoutInflater mInflater;

    private List<Person> data = new ArrayList<>();

    @Inject
    public PersonAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void refreshPersons(List<Person> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PersonViewHolder personViewHolder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_view_item_person, null);

            personViewHolder = new PersonViewHolder();
            personViewHolder.txtPersonName = (TextView) convertView.findViewById(R.id.txtPersonName);
            personViewHolder.txtPersonEmail = (TextView) convertView.findViewById(R.id.txtPersonEmail);

            convertView.setTag(personViewHolder);
        }
        else {
            personViewHolder = (PersonViewHolder) convertView.getTag();
        }

        Person person = this.data.get(position);

        personViewHolder.txtPersonName.setText(person.name);
        personViewHolder.txtPersonEmail.setText(person.email);

        return convertView;
    }

    public static class PersonViewHolder {
        public TextView txtPersonName;
        public TextView txtPersonEmail;
    }
}
