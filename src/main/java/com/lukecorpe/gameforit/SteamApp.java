package com.lukecorpe.gameforit;

import java.util.Map;

public class SteamApp {
    private Integer appid;
    private String name;
    private Integer playtime_forever;
    private String img_icon_url;
    private String img_logo_url;
    private boolean has_community_visible_stats;
    
    public SteamApp(Map<String, Object> state){
        this.appid = (Integer)state.get("appid");
        this.name = (String)state.get("name");
        this.playtime_forever = (Integer)state.get("playtime_forever");
        this.img_icon_url =(String) state.get("img_icon_url");
        this.img_logo_url = (String)state.get("img_logo_url");
        //this.has_community_visible_stats = (Boolean) state.get("has_community_visible_stats");
    }

    public Integer getAppid() {
        return appid;
    }

    public String getName() {
        return name;
    }

    public Integer getPlaytime_forever() {
        return playtime_forever;
    }

    public String getImg_icon_url() {
        return img_icon_url;
    }

    public String getImg_logo_url() {
        return img_logo_url;
    }

    public boolean getHas_community_visible_stats() {
        return has_community_visible_stats;
    }
}
