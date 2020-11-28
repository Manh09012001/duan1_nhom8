package vn.poly.goodfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vn.poly.goodfood.CheckConectsion.CheckConectSion;
import vn.poly.goodfood.dao.UserDao;
import vn.poly.goodfood.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    TextView txtSignup,txtForgotPass;
    Button btnSignin;
    CheckBox chkRememberme;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        edtUsername=findViewById(R.id.edUsername);
        edtPassword=findViewById(R.id.edPassword);
        btnSignin=findViewById(R.id.btn_signin);
        chkRememberme=findViewById(R.id.chk_checkBox);
        txtSignup = findViewById(R.id.txt_Signup);
        txtForgotPass = findViewById(R.id.txt_ForgotPass);
        userDao = new UserDao(this);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityGiaoDienChinh.class);
                startActivity(intent);
                if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean kiemTra = userDao.CheckSignIn(edtUsername.getText().toString(),edtPassword.getText().toString());

                if (kiemTra){
                    if (CheckConectSion.haveNetworkConnection(getApplicationContext())){
                        rememberUser(edtUsername.getText().toString(),edtPassword.getText().toString(),chkRememberme.isChecked());

                        edtUsername.setText("");
                        edtPassword.setText("");
                        finish();
                    }else {
                        CheckConectSion.showToast_Short(getApplicationContext(),"Không có kết nối internet!!");
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Usename hoặc Password không đúng!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckConectSion.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(MainActivity.this,ActivitySignup.class);
                    startActivity(intent);
                    finish();
                }else {
                    CheckConectSion.showToast_Short(getApplicationContext(),"Không có kết nối internet!!");
                }
            }
        });

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_forgot_password);

                Button btnCancle = dialog.findViewById(R.id.btn_DialogCancle);

                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    public void rememberUser(String usename, String pass, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("Use_File.txt",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status){
            editor.clear();
        }else {
            editor.putString("USENAME",usename);
            editor.putString("PASS",pass);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}



