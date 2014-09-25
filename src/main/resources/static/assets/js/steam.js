function loadUser(vurl) {
   $("#games").empty();
   $("#user").empty();
   var s = $('body').data('base-url')+"/getSteamUser?vanityUrl="+vurl;
   $("#user").load(s, function () {
       $("#steam-user").fadeIn("slow");
   });
   return false;
}

function doStuff() {
    $.extend($.expr[":"], {
        "containsIN": function(elem, i, match, array) {
        return (elem.textContent || elem.innerText || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
        }
    });


   $('#textBoxID').keyup(function() {
       $('.steamImageHolder').css("display", 'none');
       var value = $(this).val();
       $('.steamImageHolder:containsIN("' + value + '")').css("display", 'inline-block');
       $('.selected').parent().css("display", 'inline-block');
       if (value === "") {
           $('.steamImageHolder').css("display", 'inline-block');
       }
   });

//   $('.steamImageHolder').hover(function() {
//       var steamId = $(this).data('steam-id');
//       $('#' + steamId + '_overlay').css('display', 'block');
//   }, function() {
//       var steamId = $(this).data('steam-id');
//       $('#' + steamId + '_overlay').css('display', 'none');
//   });

   $('.steamImageHolder').each(function(index){
        $(this).on('click',function() {
        var steamId = $(this).data('steam-id');
        var checkBoxes = $('#' + steamId);
        checkBoxes.prop('checked', !checkBoxes.prop('checked'));
        $('#' + steamId + '_image').toggleClass('selected');
   });});
   
   
   
   
   
   $('#steam-friends').submit(function () {
       var ids="";
       for (var i = 0; i < $( "input:checked" ).length; i++) {
           ids += "ids="+$($( "input:checked" )[i]).attr("id")+"&";
       }
       var s = $('body').data('base-url')+"/games?ids="+$("#my-steam-id").val()+ids;
       $("#steam-user").fadeOut();
       $("#games").load(s, function () {
           $("#steam-games").fadeIn("slow");
       });
       return false;
   });
   
   $('.img-zoom').hover(function() {
       $(this).addClass('transition');

       }, function() {
           $(this).removeClass('transition');
    });

   $(".steamUserImage").tooltip();
   $(".steamUserImage").tooltip( "option", "position", { my: "bottom center", at:  "center bottom" } );
}

$(function() {
    if($('#steam-id').data('steam-id')!=""){
        loadUser($('#steam-id').data('steam-id'));
    }
    $('#steam-name').submit(loadUser($('#steam-name input').val()));
    $(document).ready(doStuff);
    $(document).ajaxComplete(doStuff);
});