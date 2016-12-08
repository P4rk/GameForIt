<div>
    <div class="row ">
        <div class="col-md-4">
            <div class="margin-side">
                <img src=${user.avatarfull} /> 
                <h3>${user.personaname}<h3>
            </div>
        </div>
        <div class="col-md-8">
            <div class="col-md-8" id="selected-friends">
            </div>
            
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="margin-side">
                <div class="input-group">
                    <span class="input-group-addon">Find Friend: </span>
                    <input id="textBoxID" type="text" class="form-control" placeholder="Friend Name">
                </div>
            </div>
        </div>
        <div class="col-md-2 col-md-offset-4">
            <div class="margin-side">
                <button id="find-games"  name="Find Games" type="submit" class="btn btn-default dropdown-toggle">
                    Find Games
                </button>
            </div>
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