package com.udacity.sandwichclub.utils;

import android.support.annotation.Nullable;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    @Nullable
    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject name = jsonObject.getJSONObject("name");

            String mainName = name.getString("mainName");

            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = toArrayOfStrings(alsoKnownAsArray);

            String placeOfOrigin = jsonObject.getString("placeOfOrigin");

            String description = jsonObject.getString("description");

            String image = jsonObject.getString("image");

            JSONArray ingredientsJSONArray = jsonObject.getJSONArray("ingredients");
            List<String> ingredients = toArrayOfStrings(ingredientsJSONArray);

            Sandwich sandwich = new Sandwich(mainName,
                                             alsoKnownAs,
                                             placeOfOrigin,
                                             description,
                                             image,
                                             ingredients);
            return sandwich;
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create a JSONObject: " + e.getMessage(), e);
        }
        return null;
    }

    private static List<String> toArrayOfStrings(JSONArray jsonArray) throws JSONException {
        List<String> alsoKnownAs = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            alsoKnownAs.add(jsonArray.getString(i));
        }
        return alsoKnownAs;
    }
}
