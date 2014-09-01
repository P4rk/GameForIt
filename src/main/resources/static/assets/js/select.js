$(function() {
    $('.steamImageHolder').hover(function() {
        var steamId = $(this).data('steam-id');
        $('#'+steamId+'_overlay').css('display', 'block');
    }, function() {
        var steamId = $(this).data('steam-id');
        $('#'+steamId+'_overlay').css('display', 'none');
    });

    $('.steamImageHolder').click(function() {
        var steamId = $(this).data('steam-id');
        var checkBoxes = $('#'+steamId);
        checkBoxes.prop('checked', !checkBoxes.prop('checked'));
        $('#'+steamId+'_image').toggleClass('selected');
    });
    //onmouseover="$('#${friend.getSteamid()}_overlay').css('display', 'block');" 
    //onmouseout="$('#${friend.getSteamid()}_overlay').css('display', 'none');" 
    //onClick="var checkBoxes = $('#${friend.getSteamid()}') ; checkBoxes.prop('checked', !checkBoxes.prop('checked')); $('#${friend.getSteamid()}_image').toggleClass('selected');
});