<@page>
    <div class="container">
        <div id="steam-id" <#if steamid??>data-steam-id="${steamid}"</#if>></div>
        <form id="steam-name">
        <input id="" class="show" type="text" name="id"/>
        </form>
        <div id="steam-user">
            <form id="steam-friends">
                <div id="user">
                </div>
                <input id="find-games" type="submit" name="Find Games"/>
            </form>
        </div>
        <div id="steam-games">
            <div id="games">
            </div>
        </div>
    </div>
</@page>