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
       if (value === "") {
           $('.steamImageHolder').css("display", 'inline-block');
       }
   });

   $('.steamImageHolder').hover(function() {
       var steamId = $(this).data('steam-id');
       $('#' + steamId + '_overlay').css('display', 'block');
   }, function() {
       var steamId = $(this).data('steam-id');
       $('#' + steamId + '_overlay').css('display', 'none');
   });

   $('.steamImageHolder').each(function(index){
        $(this).on('click',function() {
        var steamId = $(this).data('steam-id');
        var checkBoxes = $('#' + steamId);
        checkBoxes.prop('checked', !checkBoxes.prop('checked'));
        $('#' + steamId + '_image').toggleClass('selected');
   });});
}

$(function() {
    $(document).ready(doStuff);
    $(document).ajaxComplete(doStuff);
});