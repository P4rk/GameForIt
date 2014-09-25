<div>
    <div class="row">
        <div class="col-md-4">
            <img src=${user.avatarfull} /> 
            <h3>${user.personaname}<h3>
        </div>
        <div class="col-md-8">
            <div class="col-md-8" id="selected-friends">
            </div>
            <div class="col-md-8">
                <input id="find-games" type="submit" value="Find Games" name="Find Games"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            Find Friend: <input id="textBoxID" />
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <input type="hidden" name="ids" id="my-steam-id" value="${user.getSteamid()}" />
            <#list friends as friend>
             <div class="steamImageHolder" data-steam-id="${friend.getSteamid()}" >
                <img class="steamUserImage"  title="${friend.getPersonaname()}" id="${friend.getSteamid()}_image" src=${friend.avatarfull} />
                    <p class="steamImageOverlayText">${friend.getPersonaname()}</p>
                <input type="checkbox" style="display:none" id="${friend.getSteamid()}" name="ids" value="${friend.getSteamid()}"/>
             </div>
            </#list>
        </div>
    </div>
</div>