package glowsomecomputingsolutions.com.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

import glowsomecomputingsolutions.com.R;
import glowsomecomputingsolutions.com.Activities.StudentsListActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrimaryFragment extends Fragment {

    Spinner spinner_class;
    Spinner spinner_subjects;

    Button btnListStudents;

    public PrimaryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_primary, container, false);

        spinner_class = view.findViewById(R.id.spinner_class_primary);
        spinner_subjects = view.findViewById(R.id.spinner_subjects_primary);
        btnListStudents = view.findViewById(R.id.btn_list_students_primary);

//spinner and listener
        class_spinner();
        subjects_spinner();
        btnpress();
        return view;

    }
    private void class_spinner(){
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()),
                R.array.primary_classes,
                android.R.layout.simple_spinner_dropdown_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_class.setAdapter(classAdapter);

        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //do this
                // toast to show what has been selected
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void subjects_spinner(){
        ArrayAdapter<CharSequence> subjectsAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()),
                R.array.primary_subjects,
                android.R.layout.simple_spinner_dropdown_item);
        subjectsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_subjects.setAdapter(subjectsAdapter);

        spinner_subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //do this
                // toast to show what has been selected
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void btnpress(){
        btnListStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),StudentsListActivity.class);
                startActivity(intent);
                // Toast.makeText(PrimaryFragment.this,getString(R.string.primary_btn_press),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
