package com.wenmingkeji.peiliao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.GridDetailsActivity;
import com.wenmingkeji.peiliao.adaper.GridHomeAdapter;
import com.wenmingkeji.peiliao.model.NameWithIcon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/6/27.
 */
public class CategoryFragment extends Fragment {

    private static final String NAMES = "names";
    private static final String ICONS = "icons";
    @BindView(R.id.grid_view_category)
    GridView mGridViewCategory;
    GridHomeAdapter mGridHomeAdapter;
    
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CategoryFragment newInstance(NameWithIcon nameWithIcon) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(NAMES, nameWithIcon.getNames());
        bundle.putIntArray(ICONS, nameWithIcon.getIcons());
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }
    
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        
        view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        NameWithIcon nameWithIcon = new NameWithIcon(getArguments().getStringArray(NAMES),getArguments().getIntArray(ICONS));
        
        mGridHomeAdapter = new GridHomeAdapter(getContext(), nameWithIcon);
        mGridViewCategory.setAdapter(mGridHomeAdapter);

        mGridViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), GridDetailsActivity.class));
            }
        });
        
        return view;
    }


    
}
