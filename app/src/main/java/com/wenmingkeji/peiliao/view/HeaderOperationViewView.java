package com.wenmingkeji.peiliao.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.HeaderOperationAdapter;
import com.wenmingkeji.peiliao.model.OperationEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class HeaderOperationViewView extends HeaderViewInterface<List<OperationEntity>> {

    @BindView(R.id.gv_operation)
    FixedGridView gvOperation;

    public HeaderOperationViewView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(List<OperationEntity> list, ListView listView) {
        View view = mInflate.inflate(R.layout.header_operation_layout, listView, false);
        ButterKnife.bind(this, view);

        dealWithTheView(list);
        listView.addHeaderView(view);
    }

    private void dealWithTheView(List<OperationEntity> list) {
        HeaderOperationAdapter adapter = new HeaderOperationAdapter(mContext, list);
        gvOperation.setAdapter(adapter);
    }

}
