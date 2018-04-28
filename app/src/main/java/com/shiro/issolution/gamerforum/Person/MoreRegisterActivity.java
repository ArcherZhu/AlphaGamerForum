package com.shiro.issolution.gamerforum.Person;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.MainActivity;
import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model._User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Archer on 2018/4/12.
 */

public class MoreRegisterActivity extends AppCompatActivity{
    ImageButton take_image,get_image;
    Button moreregister;
    public String mypath;
    ImageView headimage;
    TextView niname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yun_register2);
        take_image = (ImageButton)findViewById(R.id.camera);
        get_image = (ImageButton)findViewById(R.id.photo);
        moreregister = (Button)findViewById(R.id.moreregister);
        Intent intent_old=getIntent();
        final String theusername=intent_old.getStringExtra("username");
        final String thepassword=intent_old.getStringExtra("password");
        headimage = (ImageView)findViewById(R.id.I1);
        niname = (TextView) findViewById(R.id.niname);

        get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }

        });
        moreregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _User newuser = new _User();
                //BmobFile imagefile=new BmobFile(theusername,null,new File(mypath).toString());
                newuser.setUsername(theusername);
                newuser.setPassword(thepassword);
                newuser.setEmail(theusername);
                newuser.setNickname(niname.getText().toString());
                //newuser.setHeadimage(imagefile);
                //注意：不能用save方法进行注册
                newuser.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(MoreRegisterActivity.this, "注册成功!" + s.toString(), Toast.LENGTH_SHORT).show();
                            Intent intent_1 = new Intent(MoreRegisterActivity.this, com.shiro.issolution.gamerforum.LoadRegisterActivity.class);
                            startActivity(intent_1);
                        } else {
                            Toast.makeText(MoreRegisterActivity.this, "注册失败!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            mypath = getImagePath(uri, null);
            ContentResolver cr = this.getContentResolver();
            try {
                Log.e("qwe", mypath.toString());
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                /* 将Bitmap设定到ImageView */
                headimage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("qwe", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String getImagePath(Uri uri, String seletion) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            }
            cursor.close();
        }
        return path;

    }

}
