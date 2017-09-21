package com.wenmingkeji.peiliao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.MessageAdapter;
import com.wenmingkeji.peiliao.model.ItemType;
import com.wenmingkeji.peiliao.model.MyMessage;
import com.wenmingkeji.peiliao.model.MyNotification;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/13.
 */
public class NotificationFragment extends Fragment {


    @BindView(R.id.rv_notification)
    RecyclerView mRvNotification;
    List<MyMessage> mMyMessages = new ArrayList<MyMessage>();
    private MessageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_notification, container, false);
        ButterKnife.bind(this, view);

        getMsgPreviews();
        mAdapter = new MessageAdapter(R.layout.item_list_message_notification, mMyMessages);

        mRvNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvNotification.setAdapter(mAdapter);
        return view;
    }


    List<MyMessage> getMsgPreviews() {
        for (int i = 0; i < 5; i++) {
            MyMessage myMessage = new MyMessage();
            MyNotification notification = new MyNotification();
            notification.setContent("因为您超时为付款，系统只好忍痛关闭您的订单！给您带来的不便请您谅解");
            myMessage.setItemType(ItemType.NOTIFICAION);
            myMessage.setMyNotification(notification);
            mMyMessages.add(myMessage);
        }
        return mMyMessages;
    }
}
