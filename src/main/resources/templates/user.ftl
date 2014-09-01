<div>
    <div class="row">
        <div class="span4">
            <img src=${user.avatarfull} /> 
            <h3>${user.personaname}<h3>
        </div>
        <div class="span6 offset6" id="selected-friends">
        </div>
    </div>
    <div class="row">
        <div class="span12">
            Find Friend: <input id="textBoxID"/>
        </div>
        <div class="span12">
            <input type="hidden" name="ids" id="my-steam-id" value="${user.getSteamid()}" />
            <#list friends as friend>
             <div class="steamImageHolder" data-steam-id="${friend.getSteamid()}" >
                <img class="steamUserImage" id="${friend.getSteamid()}_image" src=${friend.avatarfull} />
                <div class="steamImageOverlay" id="${friend.getSteamid()}_overlay">
                    <p class="steamImageOverlayText">${friend.getPersonaname()}</p>
                </div>
                <input type="checkbox" style="display:none" id="${friend.getSteamid()}" name="ids" value="${friend.getSteamid()}"/>
             </div>
            </#list>
        </div>
    </div>
</div>