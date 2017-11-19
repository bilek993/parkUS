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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextLogin)
    EditText editTextLogin;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainMapActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonRegister)
    void onButtonRegisterClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.floatingActionButtonLogin)
    void onFloatingActionButtonLoginClick() {
        String userName = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();
        if (userName.isEmpty() || password.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle(getString(R.string.login2))
                    .setMessage(getString(R.string.empty_fields))
                    .setPositiveButton(getText(R.string.ok), null)
                    .show();

            return;
        }

        User user = new User(userName, password);

        LoginNetworking loginNetworking = new LoginNetworking(this);
        loginNetworking.login(user,
                () -> {
                    LocalSharedStorage.saveUserAuthorizationData(this, userName, password);

                    startMainActivity();
                    finish();

                    return null;
                }, () -> {
                    return null;
                });
    }
}
