package com.sw.rune.management.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.sw.rune.management.Models.Rune;
import com.sw.rune.management.Models.Sub;

public final class JsonConverter {
    
    public static List<Rune> ConvertJson(MultipartFile file) throws JSONException, IOException{
        JSONObject jsonObject = new JSONObject(
                new String(file.getBytes(), StandardCharsets.UTF_8).trim().replaceFirst("\ufeff", ""));
        JSONArray jsonArray = jsonObject.getJSONArray("runes");
        int length = jsonArray.length();
        List<Rune> runes = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            runes.add(JsonConverter.createRune(jsonArray.getJSONObject(i)));
        }

        return runes;
    }

    public static Rune createRune(JSONObject json){
        Rune rune = new Rune();

        rune.setId(Integer.parseInt(json.get("id").toString()));
        rune.setSet(json.getString("set"));
        rune.setType(json.getString("m_t"));
        rune.setValue(Integer.parseInt(json.get("m_v").toString()));
        rune.setLevel(Integer.parseInt(json.get("level").toString()));
        rune.setQuality(json.getString("quality"));
        rune.setSubs(new ArrayList<Sub>());
        for(int i = 1; i < 5; i++){
            JSONObject subData = json.getJSONObject("s"+i+"_data");
            rune.getSubs().add(createSub(
                json.getString("s"+i+"_t"),
                Integer.parseInt(json.get("s"+i+"_v").toString()),
                subData.has("enchanted") ? subData.getBoolean("enchanted") : false,
                subData.has("gvalue") ? Integer.parseInt(subData.get("gvalue").toString()) : 0
                ));
        }
        rune.setAncient(json.getBoolean("ancient"));
        rune.setEff(Float.parseFloat(json.get("efficiency").toString()));
        rune.setSlot(Integer.parseInt(json.get("slot").toString()));
        rune.setInnatType(json.getString("i_t"));
        rune.setInnatValue(Integer.parseInt(json.get("i_v").toString()));
        rune.setToMaxPurple(RuneCalc.toMaxPurple(rune.getSubs(), rune.isAncient()));
        rune.setToMaxOrange(RuneCalc.toMaxOrange(rune.getSubs(), rune.isAncient()));

        return rune;
    }

    private static Sub createSub(String type, int value, boolean enchanted, int grind) {
        Sub sub = new Sub();
        sub.setType(type);
        sub.setValue(value);
        sub.setEnchanted(enchanted);
        sub.setGrind(grind);

        return sub;
    }
}
