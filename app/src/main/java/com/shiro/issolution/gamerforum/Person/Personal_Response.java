package com.shiro.issolution.gamerforum.Person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.PostActivity;
import com.shiro.issolution.gamerforum.R;
import com.shiro.issolution.gamerforum.model.ArticleComment;
import com.shiro.issolution.gamerforum.model.PostComment;
import com.shiro.issolution.gamerforum.model._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Personal_Response extends AppCompatActivity {

    public List<String> CommentContents = new ArrayList<>();
    public List<PostComment> PostComments = new ArrayList<>();
    public List<String> CommentContents2 = new ArrayList<>();
    public List<ArticleComment> ArticleComments = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal__response);
        listView = (ListView) findViewById(R.id.list_response);
        initResponses();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String postId = PostComments.get(i).getPostId();
                Intent intent = new Intent(Personal_Response.this, PostActivity.class);
                intent.putExtra("id", postId);
                startActivity(intent);
            }
        });
    }

    public void initResponses() {
        //寻找帖子回复
        BmobQuery<PostComment> query1 = new BmobQuery<PostComment>();
        _User user = BmobUser.getCurrentUser(_User.class);
        if(user==null){
            Toast.makeText(Personal_Response.this,"尚未登录！",Toast.LENGTH_SHORT).show();
            finish();
        }
        query1.addWhereEqualTo("username", user.getUsername());
        query1.findObjects(new FindListener<PostComment>() {
            @Override
            public void done(List<PostComment> list, BmobException e) {
                if (e == null) {
                    for (PostComment answer : list) {
                        CommentContents.add(answer.getCommentContent());
                        PostComments.add(answer);
                    }
                    ArrayAdapter adapter = new ArrayAdapter(Personal_Response.this, R.layout.support_simple_spinner_dropdown_item, CommentContents.toArray(new String[CommentContents.size()]));

                    listView.setAdapter(adapter);
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });

    }
}

/*
class ResponseAdapter extends ArrayAdapter {
    private int resourceId;

    ResponseAdapter(Context context, int textViewResourceId, List<Answer> list) {
        super(context, textViewResourceId, list);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Answer answer = (Answer) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView response_Content = (TextView) view.findViewById(R.id.response);
        TextView response_Title = (TextView) view.findViewById(R.id.title);
        response_Content.setText("内容："+answer.getAnswerContent());
        response_Title.setText("主题："+answer.getPostId());
        return view;
    }
}
*/
