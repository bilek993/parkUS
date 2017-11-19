package com.team_no_5.parkus.networking;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.team_no_5.parkus.R;
import com.team_no_5.parkus.networking.items.User;

import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Michal on 19.11.2017.
 */

public class LoginNetworking {

    private Context context;

    public LoginNetworking(Context context) {
        this.context = context;
    }

    public void addNewUser(User user, Callable<Void> onSuccess, Callable<Void> onFinish) {
        RestService restService = RestManager.getInstance();
        Call<Boolean> call = restService.addNewUser(user);

        call.enqueue(new AdvancedCallback<Boolean>(context) {
            @Override
            public void onRetry() {
                addNewUser(user, onSuccess, onFinish);
            }

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                super.onResponse(call, response);

                if (response.isSuccessful()) {
                    if (response.body()) {
                        try {
                            onSuccess.call();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        new AlertDialog.Builder(context)
                                .setCancelable(false)
                                .setTitle(context.getString(R.string.register2))
                                .setMessage(context.getString(R.string.user_not_created))
                                .setPositiveButton(context.getText(R.string.ok), null)
                                .show();

                        finish(onFinish);
                    }
                } else {
                    finish(onFinish);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                super.onFailure(call, t);

                finish(onFinish);
            }
        });
    }

    private void finish(Callable<Void> onFinish) {
        try {
            onFinish.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
