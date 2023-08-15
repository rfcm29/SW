package com.sw.rune.management.Models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Rune implements Serializable {
    private int id;
    private String set;
    private String type;
    private int value;
    private int level;
    private String quality;
    private List<Sub> subs;
    private boolean ancient;
    private float eff;
    private int slot;
    private String innatType;
    private int innatValue;
    private int toMaxPurple;
    private int toMaxOrange;
}
