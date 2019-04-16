package com.example.digitalportrait.ImageEditor.Principal;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digitalportrait.ImageEditor.Adapter.ThumbnailAdapter;
import com.example.digitalportrait.ImageEditor.Interface.FilterListFragmentListener;
import com.example.digitalportrait.ImageEditor.Utils.BitmapUtils;
import com.example.digitalportrait.ImageEditor.Utils.SpacesItemDecoration;
import com.example.digitalportrait.R;
import com.zomato.photofilters.FilterPack;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.utils.ThumbnailItem;
import com.zomato.photofilters.utils.ThumbnailsManager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FiltersListFragment extends Fragment implements FilterListFragmentListener{

    RecyclerView recyclerView;
    ThumbnailAdapter adapter;
    List<ThumbnailItem> thumbnailItems;
    FilterListFragmentListener listener;

    public void setListener(FilterListFragmentListener listener) {
        this.listener = listener;
    }

    public FiltersListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_fragment_filters_list, container, false);

        thumbnailItems = new ArrayList<>();
        adapter = new ThumbnailAdapter(thumbnailItems, this, getActivity());
        recyclerView = itemView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        recyclerView.setAdapter(adapter);
        displayThumbnail(null);
        return itemView;
    }

    public void displayThumbnail(final Bitmap bitmap){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bitmap thumbImage;
                if(bitmap == null)
                    thumbImage = BitmapUtils.getBitmapFromAssets(getActivity(), Main.pictureName, 100, 100);
                else
                    thumbImage = Bitmap.createScaledBitmap(bitmap, 100,100, false);

                if(thumbImage == null)
                    return;

                ThumbnailsManager.clearThumbs();
                thumbnailItems.clear();

                ThumbnailItem thumbnailItem = new ThumbnailItem();
                thumbnailItem.image = thumbImage;
                thumbnailItem.filterName = "Normal";
                ThumbnailsManager.addThumb(thumbnailItem);
                List<Filter> filters = FilterPack.getFilterPack(getActivity());
                for(Filter filter : filters){
                    ThumbnailItem TI = new ThumbnailItem();
                    TI.image = thumbImage;
                    TI.filter = filter;
                    TI.filterName = filter.getName();
                    ThumbnailsManager.addThumb(TI);

                }
                thumbnailItems.addAll(ThumbnailsManager.processThumbs(getActivity()));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        };
        new Thread(runnable).start();

    }

    @Override
    public void onFilteredSelected(Filter filter) {
        if(listener != null)
            listener.onFilteredSelected(filter);
    }


}
