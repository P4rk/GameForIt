<#macro page>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href=${assets.css("bootstrap.min.css")} >
        <link rel="stylesheet" type="text/css" href=${assets.css("main.less")} >
        <script  type="text/javascript" src=${assets.js("jquery-1.9.0.min.js")}></script>
        <script src=${assets.js("less-1.7.0.min.js")} type="text/javascript"></script>
        <script src=${assets.js("steam.js")} type="text/javascript"></script>
        
    </head>
    <body data-base-url="${assets.getAbsolute('/')}">
        <div id="wrapper">
            <div class="main">
                <#nested>
            </div>
        </div>
    </body>
</html>
</#macro>