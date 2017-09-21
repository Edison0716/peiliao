package com.wenmingkeji.peiliao.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.MessageChatAdapter;
import com.wenmingkeji.peiliao.model.MsgChat;
import com.wenmingkeji.peiliao.view.ChatLayout;
import com.wenmingkeji.peiliao.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageChatActivity extends AppCompatActivity implements ChatLayout.LayoutListener {

    @BindView(R.id.tv_title)
    TextView mTvSellerName;
    @BindView(R.id.ic_back)
    ImageButton mIcBack;
    @BindView(R.id.tv_next_step)
    ImageButton mIbtnMore;
    @BindView(R.id.icon_head)
    CircleImageView mIconHead;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_skills)
    TextView mTvSkills;
    @BindView(R.id.tv_confirm_finishing)
    TextView mTvConfirmFinishing;
    @BindView(R.id.et_msg_input)
    EditText mEtMsgInput;
    @BindView(R.id.ibtn_msg_send)
    ImageButton mIbtnMsgSend;
    @BindView(R.id.ibtn_send_voice)
    ImageButton mIbtnSendVoice;
    @BindView(R.id.ibtn_send_img)
    ImageButton mIbtnSendImg;
    @BindView(R.id.ibtn_send_emoji)
    ImageButton mIbtnSendEmoji;
    @BindView(R.id.rv_msg_chat)
    RecyclerView mRvMsgChat;
    @BindView(R.id.chat_layout)
    ChatLayout mChatLayout;
    Handler mHandler;
    private List<MsgChat> mMsgChats = new ArrayList<MsgChat>();
    private MessageChatAdapter mChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_chat);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    mRvMsgChat.smoothScrollToPosition(mMsgChats.size() - 1);
                }
            }
        };
        ButterKnife.bind(this);

        //获取数据
        getMsgChats();
        mChatAdapter = new MessageChatAdapter(mMsgChats);
        mRvMsgChat.setLayoutManager(new LinearLayoutManager(this));
        mRvMsgChat.setAdapter(mChatAdapter);

        mChatLayout.setLayoutListener(this);
    }


    //初始化消息列表数据
    private List<MsgChat> getMsgChats() {
        for (int i = 0; i < 5; i++) {
            MsgChat msgChat = new MsgChat();
            msgChat.setItemType(MsgChat.MSG_RECEIVED);
            msgChat.setChatTime("7月13日 下午16:27");
            msgChat.setChatContent("你好，很高兴认识你，请问您有什么需要帮助呵呵呵呵呵呵呵呵呵呵呵呵呵");
            mMsgChats.add(msgChat);

            MsgChat msgChat1 = new MsgChat();
            msgChat1.setItemType(MsgChat.MSG_SENT);
            msgChat1.setChatTime("7月19日 下午16:56");
            msgChat1.setChatContent("你好，我不认识你，呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵");

            mMsgChats.add(msgChat1);
        }
        return mMsgChats;
    }


    @OnClick({R.id.ic_back, R.id.tv_next_step, R.id.icon_head, R.id.ibtn_msg_send, R.id.ibtn_send_voice, R.id.ibtn_send_img, R.id.ibtn_send_emoji})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.tv_next_step:
                break;
            case R.id.icon_head:
                break;
            case R.id.ibtn_msg_send:
                String input = mEtMsgInput.getText().toString();

                MsgChat msgChat = new MsgChat();
                msgChat.setChatContent(input);
                msgChat.setItemType(MsgChat.MSG_SENT);

                mMsgChats.add(msgChat);

                //mChatAdapter.notifyDataSetChanged();
                //mHandler.sendEmptyMessageDelayed(1,1000);

                mChatAdapter.notifyDataSetChanged();


                mRvMsgChat.smoothScrollToPosition(mMsgChats.size() - 1);
//                mChatAdapter.notifyItemInserted(m);

                break;
            case R.id.ibtn_send_voice:
                Toast.makeText(this, "you click voice", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibtn_send_img:
                Toast.makeText(this, "you click img", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibtn_send_emoji:
                Toast.makeText(this, "you click emoji", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onKeyboardPop() {
        mHandler.sendEmptyMessageDelayed(1, 0);
//        mRvMsgChat.smoothScrollToPosition(mMsgChats.size() - 1);
    }

    @Override
    public void onKeyboardClose() {
//        mRvMsgChat.smoothScrollToPosition(mMsgChats.size() - 1);
    }
}
