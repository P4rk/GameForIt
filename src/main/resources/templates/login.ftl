<@page>
    <div class="container content">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                
            </div>
        </div>
        <div class="row">
            <div class="col-md-1 col-md-offset-5">
                <a href="${assets.getAbsolute('/login')}" ><img src='${assets.img("steam_large_noborder.png")}' /></a>
            </div>
        </div>
        <div class="row">
            <div class="c0l-md-1 col-md-offset-5">
                <p>
                or enter your profile url below
                </p>
            </div>
        </div>
        <div class="row">
            <form name="input" action="${assets.getAbsolute('/home')}">
                <div class="col-lg-6 col-md-offset-3" >
                    <div class="input-group">
                        <input type="text" class="form-control" name="vanityUrl" placeholder="http://steamcommunity.com/id/xxx">
                        <span class="input-group-btn">
                            <input type="submit" class="btn btn-default" value="Go!">
                        </span>
                    </div>
                </div>
            </form>
        </div>
    </div>
</@page>