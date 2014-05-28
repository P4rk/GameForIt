package com.lukecorpe.gameforit;

import org.springframework.stereotype.Component;

@Component
public class AssertLocator {
    
    private static String RESOURCE_LOCATION = "/assets/";
    
    public String asset(String asset){
        StringBuilder builder = new StringBuilder();
        builder.append(RESOURCE_LOCATION);
        builder.append(asset);
        return builder.toString();
    }
    public String css(String css){
        StringBuilder builder = new StringBuilder();
        builder.append(RESOURCE_LOCATION);
        builder.append("css/");
        builder.append(css);
        return builder.toString();
    }

    public String js(String js){
        StringBuilder builder = new StringBuilder();
        builder.append(RESOURCE_LOCATION);
        builder.append("js/");
        builder.append(js);
        return builder.toString();
    }
    
    public String img(String img){
        StringBuilder builder = new StringBuilder();
        builder.append(RESOURCE_LOCATION);
        builder.append("img/");
        builder.append(img);
        return builder.toString();
    }
}
