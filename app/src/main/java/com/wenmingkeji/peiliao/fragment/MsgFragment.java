package com.wenmingkeji.peiliao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.MessageChatActivity;
import com.wenmingkeji.peiliao.adaper.MessageAdapter;
import com.wenmingkeji.peiliao.model.ItemType;
import com.wenmingkeji.peiliao.model.MsgPreview;
import com.wenmingkeji.peiliao.model.MyMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/13.
 */
public class MsgFragment extends Fragment {
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;
    List<MyMessage> mMyMessages = new ArrayList<MyMessage>();
    private MessageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_msg, container, false);
        ButterKnife.bind(this, view);

        getMsgPreviews();
        mAdapter = new MessageAdapter(R.layout.item_list_message_msg, mMyMessages);

        mRvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvMessage.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                startActivity(new Intent(getActivity(), MessageChatActivity.class));
            }
        });
        return view;
    }


    List<MyMessage> getMsgPreviews() {
        for (int i = 0; i < 5; i++) {
            MyMessage myMessage = new MyMessage();

            MsgPreview msgPreview = new MsgPreview();
            msgPreview.setContent("你好，很高兴认识你");
            msgPreview.setIconHead(R.drawable.icon_common_food);
            msgPreview.setNum(13);
            msgPreview.setTime("3天前");
            msgPreview.setUserName("爱吃猫的鱼");

            myMessage.setMsgPreview(msgPreview);
            myMessage.setItemType(ItemType.MSG_PREVIEW);

            mMyMessages.add(myMessage);
        }
        return mMyMessages;
    }
}
