package com.team_no_5.parkus.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.team_no_5.parkus.R;
import com.team_no_5.parkus.Utilities.LocalSharedStorage;
import com.team_no_5.parkus.networking.LoginNetworking;
import com.team_no_5.parkus.networking.items.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.editTextLogin)
    EditText editTextLogin;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextSurname)
    EditText editTextSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainMapActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.floatingActionButtonRegister)
    void onFloatingActionButtonRegisterClick() {
        LoginNetworking loginNetworking = new LoginNetworking(this);

        String userName = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();

        User user = new User(userName,
                editTextName.getText().toString(),
                editTextSurname.getText().toString(),
                password);

        loginNetworking.addNewUser(user,
                () -> {
                    new AlertDialog.Builder(this)
                            .setCancelable(false)
                            .setTitle(getString(R.string.register2))
                            .setMessage(getString(R.string.user_created))
                            .setPositiveButton(getText(R.string.ok), (dialogInterface, i) -> {

                                LocalSharedStorage.saveUserAuthorizationData(this, userName, password);
                                openMainActivity();
                                finish();
                            })
                            .show();

                    return null;
                },
                () -> {
                    return null;
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
