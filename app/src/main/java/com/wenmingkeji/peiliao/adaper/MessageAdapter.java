package com.wenmingkeji.peiliao.adaper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.MyMessage;

import java.util.List;

/**
 * Created by bevis on 2016/7/13.
 */
public class MessageAdapter extends BaseQuickAdapter<MyMessage> {


   /* public MsgPreviewAdapter(View contentView, List<MsgPreview> data) {
        super(R.layout.item_list_message_msg, data);
    }*/

    public MessageAdapter(int layoutResId, List<MyMessage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyMessage myMessage) {

        switch (myMessage.getItemType()) {
            case NOTIFICAION:
                baseViewHolder.setText(R.id.tv_notify_content, myMessage.getMyNotification().getContent());
                break;
            case MSG_PREVIEW:
                baseViewHolder.setText(R.id.tv_user_name, myMessage.getMsgPreview().getUserName())
                        .setText(R.id.tv_msg_preview, myMessage.getMsgPreview().getContent())
                        .setText(R.id.tv_msg_num, String.valueOf(myMessage.getMsgPreview().getNum()))
                        .setText(R.id.tv_time, myMessage.getMsgPreview().getTime())
                        .setImageResource(R.id.icon_head, myMessage.getMsgPreview().getIconHead());
                break;
            case CHAT_MESSAGE:
                break;
        }

    }
}
