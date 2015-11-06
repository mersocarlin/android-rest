package com.mersocarlin.androidrest.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.mersocarlin.androidrest.R;
import com.mersocarlin.androidrest.data.repository.PersonRepository;
import com.mersocarlin.androidrest.domain.model.Course;
import com.mersocarlin.androidrest.domain.model.Person;
import com.mersocarlin.androidrest.network.request.CourseRequest;
import com.mersocarlin.androidrest.network.request.PersonRequest;
import com.mersocarlin.androidrest.network.response.PersonResponse;
import com.mersocarlin.androidrest.ui.adapter.PersonAdapter;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Inject
    private PersonRepository personRepository;

    @InjectView(R.id.txtSearch) private EditText txtSearch;
    @InjectView(R.id.listViewPersons) private ListView listViewPersons;

    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        final CourseRequest courseRequest = new CourseRequest();
//
//        getManager().execute(courseRequest, new RequestListener<Course.List>() {
//            @Override
//            public void onRequestFailure(SpiceException spiceException) { }
//
//            @Override
//            public void onRequestSuccess(Course.List courses) {
//                String x = "";
//            }
//        });
//
//        Person person = new Person();
//        person.id = 0;
//        person.name = "TEST PERSON";
//        person.email = "test@test.com";
//        person.homePhone = "0123456789";
//        person.mobilePhone = "9876543210";
//        person.workPhone = "0000000000";

        //this.personRepository.save(person);

        this.txtSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    performSearch();
                    return true;
                }

                return false;
            }
        });

        this.personAdapter = new PersonAdapter(this);
        this.listViewPersons.setAdapter(this.personAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void performSearch() {
        List<Person> personList = this.personRepository.get();

        String str = "";
        for (Person person : personList) {
            str += person.toString();
        }

        final PersonRequest personRequest = new PersonRequest(this.txtSearch.getText().toString(), -1, -1, 1);

        getManager().execute(personRequest, new RequestListener<PersonResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {

            }

            @Override
            public void onRequestSuccess(PersonResponse personResponse) {
                for (Person person : personResponse.data) {
                    //personRepository.save(person);
                }

                personAdapter.refreshPersons(personResponse.data);
            }
        });
    }
}
