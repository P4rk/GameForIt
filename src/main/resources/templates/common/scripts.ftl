<script>
    $(function () {
        $('#steam-name').submit(function () {
            $("#games").empty();
            $("#user").empty();
            var s = "${assets.getAbsolute("/getSteamId")}?vanityUrl="+$('#steam-name input').val();
            $("#user").load(s, function () {
                $("#steam-user").fadeIn("slow");
            });
            return false;
        });
        $('#steam-friends').submit(function () {
            var ids="";
            for (var i = 0; i < $( "input:checked" ).length; i++) {
                ids += "ids="+$($( "input:checked" )[i]).attr("id")+"&";
            }
            var s = "${assets.getAbsolute("/games")}?ids="+$("#my-steam-id").val()+ids;
            $("#steam-user").fadeOut();
            $("#games").load(s, function () {
                $("#steam-games").fadeIn("slow");
            });
            return false;
        });
    });
</script>