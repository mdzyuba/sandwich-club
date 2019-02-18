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
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    @Nullable
    public static Sandwich parseSandwichJson(@Nullable String json) {
        if (json == null) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject name = jsonObject.optJSONObject(NAME);

            String mainName = "";
            List<String> alsoKnownAs = new ArrayList<>();

            if (name != null) {
                mainName = name.optString(MAIN_NAME);
                JSONArray alsoKnownAsArray = name.optJSONArray(ALSO_KNOWN_AS);
                alsoKnownAs = toArrayOfStrings(alsoKnownAsArray);
            }

            String placeOfOrigin = jsonObject.optString(PLACE_OF_ORIGIN);

            String description = jsonObject.optString(DESCRIPTION);

            String image = jsonObject.optString(IMAGE);

            JSONArray ingredientsJSONArray = jsonObject.optJSONArray(INGREDIENTS);
            List<String> ingredients = toArrayOfStrings(ingredientsJSONArray);

            return new Sandwich(mainName,
                                alsoKnownAs,
                                placeOfOrigin,
                                description,
                                image,
                                ingredients);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to create a JSONObject: " + e.getMessage(), e);
        }
        return null;
    }

    private static List<String> toArrayOfStrings(@Nullable JSONArray jsonArray) {
        if (jsonArray == null) {
            return new ArrayList<>();
        }
        List<String> alsoKnownAs = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            alsoKnownAs.add(jsonArray.optString(i));
        }
        return alsoKnownAs;
    }
}
