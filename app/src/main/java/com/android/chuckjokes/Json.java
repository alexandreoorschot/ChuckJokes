package com.android.chuckjokes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json {
    String value = null;



    public Json(String jsonString) {
        try{
            jsonParser(jsonString);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    private void jsonParser(String json) throws JSONException {
        JSONObject object = new JSONObject(json);
        setValue(object.getString("value"));
    }

}

