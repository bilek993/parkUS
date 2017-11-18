package com.team_no_5.parkus.Utilities;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Michal on 18.11.2017.
 */

public class PhotosHelper {

    private String currentPhotoPath;

    public File createImageFile(Context context) throws IOException {
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "JPEG_" + time + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(fileName, ".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public String getCurrentPhotoPath() {
        return currentPhotoPath;
    }
}
