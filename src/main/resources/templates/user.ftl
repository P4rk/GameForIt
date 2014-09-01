<div>
    <div>
        <img src=${user.avatarfull} />
        <H3>${user.personaname}</H3>
    </div>
    <br>
    <div>
        Find Friend: <input id="textBoxID"/>
    </div>
    <div>
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