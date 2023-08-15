package com.sw.rune.management.Models;

import java.io.Serializable;

import lombok.Data;

@Data
public class Sub implements Serializable{
    private String type;
    private int value;
    private boolean enchanted;
    private int grind;
    
}
