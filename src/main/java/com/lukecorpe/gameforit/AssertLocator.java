package com.lukecorpe.gameforit;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

@Component
public class AssertLocator {
    
    @Autowired
    protected HttpServletRequest req;
    
    private static String RESOURCE_LOCATION = "/static/assets/";
    
    public String asset(String asset){
        StringBuilder builder = new StringBuilder();
        builder.append(RESOURCE_LOCATION);
        builder.append(asset);
        return getAbsolute(builder.toString()).toString();
    }
    public String css(String css){
        StringBuilder builder = new StringBuilder();
        builder.append(RESOURCE_LOCATION);
        builder.append("css/");
        builder.append(css);
        return getAbsolute(builder.toString()).toString();
    }

    public String js(String js){
        StringBuilder builder = new StringBuilder();
        builder.append(RESOURCE_LOCATION);
        builder.append("js/");
        builder.append(js);
        return getAbsolute(builder.toString()).toString();
    }
    
    public String img(String img){
        StringBuilder builder = new StringBuilder();
        builder.append(RESOURCE_LOCATION);
        builder.append("img/");
        builder.append(img);
        return getAbsolute(builder.toString()).toString();
    }
    
    public URI getAbsolute(String uri){
        return getAbsolute(uri, ImmutableMap.<String, String>of());
    }
    
    public URI getAbsolute(String uri, Map<String, String> queryParams) {
        StringBuilder uriBuilder = new StringBuilder(uri);

        if(queryParams.size()>0){
            uriBuilder.append("?");
            for(Entry<String, String> entry : queryParams.entrySet()){
                uriBuilder.append(entry.getKey());
                uriBuilder.append(entry.getValue());
                uriBuilder.append("&");
            }
        }

        StringBuilder domainStringBuilder = new StringBuilder(req.getServerName());
        if (!(req.getServerPort() == 80)) {
            domainStringBuilder.append(":");
            domainStringBuilder.append(req.getServerPort());
        }

        return URI.create(req.getScheme() + "://" + domainStringBuilder.toString() + req.getContextPath() + uriBuilder.toString());

    }
}
