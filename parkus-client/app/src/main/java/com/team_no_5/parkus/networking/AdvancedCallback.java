package com.team_no_5.parkus.networking;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.team_no_5.parkus.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Michal on 18.11.2017.
 */

public abstract class AdvancedCallback<T> implements Callback<T> {

    private Context context;

    public AdvancedCallback(Context context) {
        this.context = context;
    }

    public abstract void onRetry();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            showRetryDialog(String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        showRetryDialog(context.getString(R.string.network_error_no_code) + " " + t.getMessage());
    }

    private void showRetryDialog(String msg) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(context.getString(R.string.encountered_problem))
                .setMessage(msg)
                .setPositiveButton(context.getText(R.string.try_again), (dialog, which) -> {
                    onRetry();
                    dialog.dismiss();
                })
                .setNegativeButton(context.getText(R.string.cancel),  (dialog, which) -> dialog.dismiss())
                .show();
    }
}
