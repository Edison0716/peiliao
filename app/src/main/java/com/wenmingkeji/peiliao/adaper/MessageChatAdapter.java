package com.wenmingkeji.peiliao.adaper;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.MsgChat;

import java.util.List;

/**
 * Created by bevis on 2016/7/13.
 */
public class MessageChatAdapter extends BaseMultiItemQuickAdapter<MsgChat> {


    public MessageChatAdapter(List<MsgChat> data) {
        super(data);
        addItemType(MsgChat.MSG_SENT, R.layout.item_message_chat_sent);
        addItemType(MsgChat.MSG_RECEIVED, R.layout.item_message_chat_received);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MsgChat msgChat) {
        switch (msgChat.getItemType()) {
            case MsgChat.MSG_RECEIVED:
                baseViewHolder.setText(R.id.tv_time_received, msgChat.getChatTime())
                        .setText(R.id.tv_msg_received, msgChat.getChatContent());
                break;

            case MsgChat.MSG_SENT:
                baseViewHolder.setText(R.id.tv_time_sent, msgChat.getChatTime())
                        .setText(R.id.tv_msg_sent, msgChat.getChatContent());
                break;

        }
    }
}
