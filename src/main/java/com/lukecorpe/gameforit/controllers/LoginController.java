package com.lukecorpe.gameforit.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;
import org.openid4java.message.Parameter;
import org.openid4java.message.ParameterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lukecorpe.gameforit.AssertLocator;
import com.lukecorpe.gameforit.SteamConstants;

@Controller
public class LoginController {
    //FIXME move me out of the controller shared state is bad!!!
    private static final String STEAM_OPENID = "http://steamcommunity.com/openid";
    private final ConsumerManager manager;
    private final Pattern STEAM_REGEX = Pattern.compile("(\\d+)");
    private DiscoveryInformation discovered;
    //FIXME move me out of the controller shared state is bad!!!
    
    
    @Autowired
    private AssertLocator assertLocator;
    
    public LoginController() {
        manager = new ConsumerManager();
        manager.setMaxAssocAttempts(0);
        try {
            discovered = manager.associate(manager.discover(STEAM_OPENID));
        } catch (DiscoveryException e) {
            e.printStackTrace();
            discovered = null;
        }
    }
    
    @RequestMapping("/")
    public ModelAndView loginpage(ModelAndView mav){
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
         String login = login(assertLocator.getAbsolute("/auth").toString());
         return "redirect:"+login;
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        return null;
    }
    
    @RequestMapping("/auth")
    public String auth(HttpServletRequest request, HttpServletResponse response){
        String steamid = verify(assertLocator.getAbsolute(request.getContextPath()).toString(), request.getParameterMap());
        return "redirect:/home?steamid="+steamid.replaceFirst("http://steamcommunity.com/openid/id/", "");
    }

    public String login(String callbackUrl) {
        if (this.discovered == null) {
            return null;
        }
        try {
            AuthRequest authReq = manager.authenticate(this.discovered, callbackUrl);
            return authReq.getDestinationUrl(true);
        } catch (MessageException | ConsumerException e) {
            //FIXME this is awful
            e.printStackTrace();
        }
        return null;
    }
    
    public String verify(String receivingUrl, Map responseMap) {
        if (this.discovered == null) {
            return null;
        }
        ParameterList responseList = new ParameterList(responseMap);
        Parameter parameter = responseList.getParameter("openid.identity");
        return parameter.getValue();
    }
}
