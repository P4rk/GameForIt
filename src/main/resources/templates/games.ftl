<#list ownedGames as game>
    <img class="img-zoom" alt="${game.getName()}" src = "http://media.steampowered.com/steamcommunity/public/images/apps/${game.getAppid()?c}/${game.getImg_logo_url()!game.getImg_icon_url()}.jpg" />
</#list>