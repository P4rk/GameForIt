package com.lukecorpe.gameforit;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class SteamUser {
    private String steamid;
    private String communityvisibilitystate;
    private String profilestate;
    private String personaname;
    private String lastlogoff;
    private String profileurl;
    private String avatar;
    private String avatarmedium;
    private String avatarfull;
    private String personastate;
    private String realname;
    private String primaryclanid;
    private String timecreated;
    private String personastateflags;
    private String loccountrycode;
    
    private List<SteamUser> friends;

    public SteamUser(Map<String, String> state){
        this.steamid = state.get("steamid");
        this.communityvisibilitystate = state.get("communityvisibilitystate");
        this.profilestate = state.get("profilestate");
        this.personaname = state.get("personaname");
        this.lastlogoff = state.get("lastlogoff");
        this.profileurl = state.get("profileurl");
        this.avatar = state.get("avatar");
        this.avatarmedium = state.get("avatarmedium");
        this.avatarfull = state.get("avatarfull");
        this.personastate = state.get("personastate");
        this.realname = state.get("realname");
        this.primaryclanid = state.get("primaryclanid");
        this.timecreated = state.get("timecreated");
        this.personastateflags = state.get("personastateflags");
        this.loccountrycode = state.get("loccountrycode");
    }

    public String getSteamid() {
        return steamid;
    }
    public String getCommunityvisibilitystate() {
        return communityvisibilitystate;
    }
    public String getProfilestate() {
        return profilestate;
    }
    public String getPersonaname() {
        return personaname;
    }
    public String getLastlogoff() {
        return lastlogoff;
    }
    public String getProfileurl() {
        return profileurl;
    }
    public String getAvatar() {
        return avatar;
    }
    public String getAvatarmedium() {
        return avatarmedium;
    }
    public String getAvatarfull() {
        return avatarfull;
    }
    public String getPersonastate() {
        return personastate;
    }
    public String getRealname() {
        return realname;
    }
    public String getPrimaryclanid() {
        return primaryclanid;
    }
    public String getTimecreated() {
        return timecreated;
    }
    public String getPersonastateflags() {
        return personastateflags;
    }
    public String getLoccountrycode() {
        return loccountrycode;
    }

    public List<SteamUser> getFriends() throws ClientProtocolException, IOException {
        if(friends == null){
            friends = Factroy.getFriends(this);
        }
        return friends;
    }

    public static class Factroy{

        private static ObjectMapper mapper = new ObjectMapper();
        private static String vanityToID = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?format=json&key="+SteamConstants.steamApiKey+"&vanityurl=";
        private static String playerSummaries = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+SteamConstants.steamApiKey+"&steamids=";
        private static String friendsUrl = "http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?relationship=friend&format=json&key="+SteamConstants.steamApiKey+"&steamid=";
        private static String ownedGames = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+SteamConstants.steamApiKey+"&format=json&include_appinfo=1&steamid=";

        private static HttpClient HTTP = HttpClientBuilder.create().build();
        private static final List<Integer> GAMES_TO_REMOVE = ImmutableList.<Integer>of(205790, 223530, 644);

        public static Optional<SteamUser> getFromVanity(String vanityUrl) throws JsonParseException, JsonMappingException, IllegalStateException, IOException{
            String mySteamId;
            if(!StringUtils.isNumeric(vanityUrl)){
                HttpGet get = new HttpGet(vanityToID.concat(vanityUrl));
                HttpResponse rsp = HTTP.execute(get);
                if(rsp.getStatusLine().getStatusCode() != 200){
                    return Optional.absent();
                }
                Map<String, Map<String, String>> map= mapper.readValue(rsp.getEntity().getContent(), new TypeReference<Map<String, Map<String, String>>>() {});
                get.releaseConnection();
                
                mySteamId = map.get("response").get("steamid");
            }else{
                mySteamId = vanityUrl;
            }

             List<SteamUser> playerSummaries2 = getPlayerSummaries(Lists.newArrayList(mySteamId));
             if(playerSummaries2.size()>0){
                 return Optional.of(playerSummaries2.get(0));
             }
             return Optional.<SteamUser>absent();
        }


        public static List<SteamUser> getPlayerSummaries(
                List<String> steamids) throws IOException, ClientProtocolException,
                JsonParseException, JsonMappingException {
            List<SteamUser> users = Lists.newArrayList();
            HttpGet get = new HttpGet(playerSummaries.concat(Joiner.on(",").join(steamids)));
            HttpResponse rsp = HTTP.execute(get);

            if(rsp.getStatusLine().getStatusCode() != 200){
                get.releaseConnection();
                return Collections.EMPTY_LIST;
            }
            Map<String, Map<String, List<Map<String, String>>>> summaries = mapper.readValue(rsp.getEntity().getContent(), new TypeReference<Map<String, Map<String, List<Map<String, String>>>>>() {});
            for(Map<String, String> user: summaries.get("response").get("players")){
                users.add(new SteamUser(user));
            }
            get.releaseConnection();
            return users;
        }
        
        public static List<SteamUser> getFriends(SteamUser user) throws ClientProtocolException, IOException{
            HttpGet get = new HttpGet(friendsUrl.concat(user.getSteamid()));
            HttpResponse rsp = HTTP.execute(get);
            Map<String, Map<String, List<Map<String, String>>>> friendsList = mapper.readValue(rsp.getEntity().getContent(), new TypeReference<Map<String, Map<String, List<Map<String, String>>>>>() {});
            get.releaseConnection();
            
            List<String> ids = Lists.newArrayList();
            Map<String, List<Map<String, String>>> friendsMap = friendsList.get("friendslist");
            if(friendsMap == null){
                return Collections.EMPTY_LIST;
            }
            for(Map<String, String> m : friendsMap.get("friends")){
                ids.add( m.get("steamid"));
            }
           return getPlayerSummaries(ids);
        }
        
        public static List<SteamApp> getGames(List<String> ids) throws ClientProtocolException, IOException{
            HttpGet get;
            HttpResponse rsp;
            Map<String,Map<String, Object>> map; //List<Map<String,Long>>>> map;
            List<Set<Integer>> sets = Lists.newArrayList();
            Map<Integer, SteamApp> apps = Maps.newHashMap();
            for(String id : ids){
                Set<Integer> appids = Sets.newHashSet(); 
                get = new HttpGet(ownedGames.concat(id));
                rsp = HTTP.execute(get);
                map = mapper.readValue(rsp.getEntity().getContent(), new TypeReference<Map<String,Map<String,Object>>>(){});
                if((Integer)map.get("response").get("game_count") > 0){
                    for(Map<String, Object> m : (List<Map<String,Object>>) map.get("response").get("games")){
                        if(!appids.contains(m.get("appid"))){
                            appids.add((Integer) m.get("appid"));
                        }
                        if(!apps.containsKey(m.get("appid"))){
                            apps.put((Integer) m.get("appid"), new SteamApp(m));
                        }
                    }
                    sets.add(appids);
                }else{
                    return Collections.EMPTY_LIST;
                }
            }
            Set<Integer> ownedGamesids = sets.get(0);
            for(int i=1;i<sets.size(); i++){
                ownedGamesids = Sets.intersection(ownedGamesids, sets.get(i));
            }

            List<SteamApp> ownedGames = Lists.newArrayList();
            for(Integer id : ownedGamesids){
                if(!GAMES_TO_REMOVE.contains(id)){
                    ownedGames.add(apps.get(id));
                }
            }
            return ownedGames;
        }
    }

}
