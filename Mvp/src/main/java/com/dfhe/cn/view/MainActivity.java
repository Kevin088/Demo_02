package com.dfhe.cn.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dfhe.cn.R;
import com.dfhe.cn.presenter.UserPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IUserView, View.OnClickListener {

    @Bind(R.id.id)
    EditText id;
    @Bind(R.id.firstname)
    EditText firstname;
    @Bind(R.id.lastname)
    EditText lastname;
    @Bind(R.id.write)
    TextView write;
    @Bind(R.id.read)
    TextView read;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    UserPresenter userPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userPresenter=new UserPresenter(this);
        read.setOnClickListener(this);
        write.setOnClickListener(this);
    }

    @Override
    public int getID() {
        return new Integer(id.getText().toString());
    }

    @Override
    public String getFristName() {
        return firstname.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastname.getText().toString();
    }

    @Override
    public void setFirstName(String firstName) {
        firstname.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        lastname.setText(lastName);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.read:
                userPresenter.loadUser(getID());
            break;
            case R.id.write:

                userPresenter.saveUser(getID(),getFristName(),getLastName());
                break;
            default:
            break;
        }
    }
}
