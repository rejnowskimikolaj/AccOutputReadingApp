package com.example.rent.accoutputreadingappwithfragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    TextView output;
    Button goToListButton;
    String filePath;

    public DetailFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        output = (TextView)rootView.findViewById(R.id.acc_output_textView);
        goToListButton = (Button) rootView.findViewById(R.id.goToList_button);
        goToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccOutputFragmentHolder fragmentHolder = (AccOutputFragmentHolder) getActivity();
                fragmentHolder.switchToListFragment();
            }
        });

        try {
            String fileOutput = FileManager.instance.readBinFile(filePath);
            output.setText(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    public void setFilePath(String filePath){
        this.filePath= filePath;
    }



}
