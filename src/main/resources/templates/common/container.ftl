<#macro page>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href=${assets.css("jquery-ui.min.css")} >
        <link rel="stylesheet" type="text/css" href=${assets.css("bootstrap.min.css")} >
        <link rel="stylesheet" type="text/css" href=${assets.css("main.less")} >
        <script  type="text/javascript" src=${assets.js("jquery-1.9.0.min.js")}></script>
        <script  type="text/javascript" src=${assets.js("bootstrap.min.js")}></script>
        <script src=${assets.js("less-1.7.0.min.js")} type="text/javascript"></script>
        <script src=${assets.js("steam.js")} type="text/javascript"></script>
        <script src=${assets.js("jquery-ui.js")} type="text/javascript"></script>
        
    </head>
    <body data-base-url="${assets.getAbsolute('/')}">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${assets.getAbsolute('/')}">Game For It</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${assets.getAbsolute('/')}">Login</a></li>
            <#if steamid??><li class="active"><a href="${assets.getAbsolute('/home')}?steamid=${steamid}">Friends</a></li></#if>
          </ul>
        </div>
      </div>
    </div>
        <#nested>
    </body>
</html>
</#macro>