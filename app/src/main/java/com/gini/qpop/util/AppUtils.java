package com.gini.qpop.util;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class AppUtils {

    public static JSONObject readJsonFile(Context context, String fileName) throws IOException, JSONException {
        InputStream inputStream = context.getAssets().open(fileName);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        return new JSONObject(new String(buffer, "UTF-8"));
    }

    public static JSONArray readJsonArrayFile(Context context, String fileName) throws IOException, JSONException {
        InputStream inputStream = context.getAssets().open(fileName);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        return new JSONArray(new String(buffer, "UTF-8"));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int generateRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return Color.rgb(r, g, b);
    }

}
