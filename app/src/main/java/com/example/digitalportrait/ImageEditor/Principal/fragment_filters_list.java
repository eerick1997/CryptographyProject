package com.example.digitalportrait.ImageEditor.Principal;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digitalportrait.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_filters_list extends Fragment {


    public fragment_filters_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_filters_list, container, false);
    }

}
