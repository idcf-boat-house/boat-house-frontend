package com.idcf.boathouse.services;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FoodCategoryService {

    public boolean VerifyName(String name) {
        Pattern Name_Pattern = Pattern.compile("[\\u4e00-\\u9fa5]{2,20}");
        Matcher matcher = Name_Pattern.matcher(name);
        return matcher.matches();
    }
}
