package com.sw.rune.management.Utils;

import java.util.List;

import com.sw.rune.management.Models.Sub;

public final class RuneCalc {
    static int toMaxPurple;
    static int toMaxOrange;

    public static int toMaxPurple(List<Sub> subs, boolean ancient){
        toMaxPurple = 0;

        subs.forEach((sub) -> {
            if(sub.getType().equals("HP%") || sub.getType().equals("ATK%") || sub.getType().equals("DEF%")){
                toMaxPurple += (sub.getGrind() < 8 ? (ancient ? 9 : 7) - sub.getGrind() : 0);
            } else if (sub.getType().equals("SPD")){
                toMaxPurple += (sub.getGrind() < 5 ? (ancient ? 5 : 4) - sub.getGrind() : 0);
            }
        });

        return toMaxPurple;
    }

    public static int toMaxOrange(List<Sub> subs, boolean ancient){
        toMaxOrange = 0;

        subs.forEach((sub) -> {
            if(sub.getType().equals("HP%") || sub.getType().equals("ATK%") || sub.getType().equals("DEF%")){
                toMaxOrange += (ancient ? 12 : 10) - sub.getGrind();
            } else if (sub.getType().equals("SPD")){
                toMaxOrange += (ancient ? 6 : 5) - sub.getGrind();
            }
        });

        return toMaxOrange;
    }

    public static float maxEffPurple(){
        return 0f;
    }

    public static float maxEffOrange(){
        return 0f;
    }
}
