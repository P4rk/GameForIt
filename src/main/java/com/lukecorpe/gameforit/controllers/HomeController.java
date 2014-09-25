package com.lukecorpe.gameforit.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.lukecorpe.gameforit.SteamApp;
import com.lukecorpe.gameforit.SteamUser;

@Controller
public class HomeController {

    @RequestMapping("home")
    public ModelAndView home(ModelAndView mav, @RequestParam(required = false) String steamid, @RequestParam(required = false) String vanityUrl){
        mav.addObject("steamid", Objects.firstNonNull(steamid, getVanityNameFromUrl(vanityUrl)));
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping("/getSteamUser")
    public ModelAndView getSteamUser(ModelAndView mav, @RequestParam String vanityUrl) throws IOException {
        Optional<SteamUser> user = SteamUser.Factroy.getFromVanity(vanityUrl);
        List<SteamUser> friends;
        if(user.isPresent()){
             friends = user.get().getFriends();
             mav.addObject("user", user.get());
             mav.addObject("friends", friends);
             mav.setViewName("user");
             return mav;
        }
        return null;
    }
    
    @RequestMapping("/games")
    public ModelAndView friendsGames(ModelAndView mav, @RequestParam(value = "ids") List<String> ids) throws ClientProtocolException, IOException{
        List<SteamApp> ownedGames = SteamUser.Factroy.getGames(ids);
        mav.addObject("ownedGames", ownedGames);
        mav.setViewName("games");
        return mav;
    }
    
    private String getVanityNameFromUrl(String url){
        if(url == null){
            return null;
        }
        if(url.contains("http://steamcommunity.com/id/")) {
            return url.replaceFirst("http://steamcommunity.com/id/", "").replaceAll("/", "");
        } else if (url.contains("http://steamcommunity.com/profile/")){
            return url.replaceFirst("http://steamcommunity.com/profile/", "").replaceAll("/", "");
        }
        return url.replaceAll("/", "");
    }
}
