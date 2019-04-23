package com.example.digitalportrait.ImageEditor.Principal;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.digitalportrait.ImageEditor.Adapter.ColorAdapter;
import com.example.digitalportrait.ImageEditor.Interface.AddTextFragmentListener;
import com.example.digitalportrait.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTextFragment extends BottomSheetDialogFragment implements ColorAdapter.ColorAdapterListener {

    int colorSelected = Color.parseColor("#000000");
    AddTextFragmentListener listener;

    EditText edtAddText;
    RecyclerView recyclerColor;
    Button btnAddText;

    static AddTextFragment instance;

    static AddTextFragment getInstance(){
        if(instance == null)
            instance = new AddTextFragment();
        return instance;
    }

    public void setListener(AddTextFragmentListener listener){
        this.listener = listener;
    }
    public AddTextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_add_text, container, false);
        edtAddText = itemView.findViewById(R.id.edt_add_text);
        btnAddText = itemView.findViewById(R.id.btn_add_text);
        recyclerColor = itemView.findViewById(R.id.recycler_color);
        recyclerColor.setHasFixedSize(true);
        recyclerColor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ColorAdapter colorAdapter = new ColorAdapter(getContext(), this);
        recyclerColor.setAdapter(colorAdapter);
        //Event
        btnAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddTextButtonClick(edtAddText.getText().toString(), colorSelected);
            }
        });
        return itemView;
    }

    @Override
    public void onColorSelected(int color) {
        colorSelected = color;
    }
}
