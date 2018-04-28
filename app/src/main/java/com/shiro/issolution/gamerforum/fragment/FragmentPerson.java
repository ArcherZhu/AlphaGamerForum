package com.shiro.issolution.gamerforum.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shiro.issolution.gamerforum.LoadRegisterActivity;
import com.shiro.issolution.gamerforum.MainActivity;
import com.shiro.issolution.gamerforum.Person.Person_Feedback;
import com.shiro.issolution.gamerforum.Person.Person_Setting;
import com.shiro.issolution.gamerforum.Person.Personal_Collection;
import com.shiro.issolution.gamerforum.Person.Personal_Edit;
import com.shiro.issolution.gamerforum.Person.Personal_Response;
import com.shiro.issolution.gamerforum.Person.Personal_Topic;
import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model._User;

import cn.bmob.v3.BmobUser;

public class FragmentPerson extends Fragment {
    private View view;
    private TextView nickNameView;
    private View nickname_layout;
    private Button btn_lr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.yun_self, container, false);

        nickNameView = view.findViewById(R.id.NickName);
        nickNameView.setTypeface(Typeface.createFromAsset(view.getContext().getAssets(),"ttf.TTF"));

        nickname_layout = view.findViewById(R.id.nickname_layout);
        btn_lr = view.findViewById(R.id.load_register);

        //loginGrand(view);


        ((Button) view.findViewById(R.id.button_edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(view.getContext(), Personal_Edit.class);
                startActivity(i1);
            }
        });

        ((Button) view.findViewById(R.id.button_title)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(view.getContext(), Personal_Topic.class);
                startActivity(i2);
            }
        });

        ((Button) view.findViewById(R.id.button_response)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(view.getContext(), Personal_Response.class);
                startActivity(i3);
            }
        });

        ((Button) view.findViewById(R.id.button_collection)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(view.getContext(), Personal_Collection.class);
                startActivity(i4);
            }
        });

        ((ImageButton) view.findViewById(R.id.button_setting)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(view.getContext(), Person_Setting.class);
                startActivity(i5);
            }
        });

        ((Button) view.findViewById(R.id.button_feedback)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 = new Intent(view.getContext(), Person_Feedback.class);
                startActivity(i6);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loginGrand(view);
    }


    public void loginGrand(View view) {
        if (BmobUser.getCurrentUser() == null) {
            nickname_layout.setVisibility(View.GONE);
            btn_lr = (Button) getActivity().findViewById(R.id.load_register);
            btn_lr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(view.getContext(), LoadRegisterActivity.class));
                }
            });
        } else {
            nickname_layout.setVisibility(View.VISIBLE);
            String nickName = BmobUser.getCurrentUser(_User.class).getNickname();
            nickNameView.setText(nickName);
        }
    }
}
