package com.example.rent.accoutputreadingappwithfragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    FileManager fileManager;
    ListView listView;


    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView)root.findViewById(R.id.listView);
        verifyStoragePermissions();

        return root;
    }


    public  void verifyStoragePermissions() {

        boolean permission = getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        if (!permission) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1
            );
        }

        else setEverything();
    }

    public void setEverything(){

        fileManager = FileManager.instance;

        List<File> fileList = fileManager.getFilesFromFolder();

        ArrayList<String> filePathsList = new ArrayList<>();
        for(File file:fileList){
            filePathsList.add(file.getAbsolutePath());
        }

        final ArrayAdapter<String> filepathAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, filePathsList);

        listView.setAdapter(filepathAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AccOutputFragmentHolder fragmentHolder = (AccOutputFragmentHolder) getActivity();
                fragmentHolder.switchToDetailFragment(filepathAdapter.getItem(i));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    setEverything();

                } else {
                }
                return;
            }

        }
    }

}
