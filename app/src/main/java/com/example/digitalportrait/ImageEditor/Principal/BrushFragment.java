package com.example.digitalportrait.ImageEditor.Principal;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.example.digitalportrait.ImageEditor.Adapter.ColorAdapter;
import com.example.digitalportrait.ImageEditor.Interface.BrushFragmentListener;
import com.example.digitalportrait.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrushFragment extends BottomSheetDialogFragment implements ColorAdapter.ColorAdapterListener {

    SeekBar seekBarBrushSize, seekBarOpacity;
    RecyclerView recyclerColor;
    ToggleButton btnBrushState;
    ColorAdapter colorAdapter;

    BrushFragmentListener listener;

    static BrushFragment instance;

    public static BrushFragment getInstance(){
        if (instance == null)
            instance = new BrushFragment();
        return instance;
    }

    public void setListener(BrushFragmentListener listener) {
        this.listener = listener;
    }

    public BrushFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_brush, container, false);
        seekBarBrushSize = itemView.findViewById(R.id.seekbar_brush_size);
        seekBarOpacity = itemView.findViewById(R.id.seekbar_brush_opacity);
        btnBrushState = itemView.findViewById(R.id.btn_brush_state);
        recyclerColor = itemView.findViewById(R.id.recycler_color);
        recyclerColor.setHasFixedSize(true);
        recyclerColor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        colorAdapter = new ColorAdapter(getContext(), genColorList(), this);
        recyclerColor.setAdapter(colorAdapter);
        //Event
        seekBarOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                listener.onBrushOpacityChangedListener(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarBrushSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                listener.onBrushSizeChangedListener(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnBrushState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onBrushStateChangedListener(isChecked);
            }
        });
        return itemView;
    }

    private List<Integer> genColorList(){
        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#5359af"));
        colorList.add(Color.parseColor("#ffccd5"));
        colorList.add(Color.parseColor("#896d66"));
        colorList.add(Color.parseColor("#b819f0"));
        colorList.add(Color.parseColor("#a10000"));
        colorList.add(Color.parseColor("#a15000"));
        colorList.add(Color.parseColor("#1f3f3c"));
        colorList.add(Color.parseColor("#416600"));
        colorList.add(Color.parseColor("#121c25"));
        colorList.add(Color.parseColor("#e6f6ff"));
        return  colorList;
    }

    @Override
    public void onColorSelected(int color) {
        listener.onBrushColorChangedListener(color);
    }
}
