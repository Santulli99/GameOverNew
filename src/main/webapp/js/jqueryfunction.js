
$(document).ready(function(){
    $("#menu2").on({
        'click': function(){$("#menu1").toggle();},
        mouseleave: function(){$("#menu1").css("display","none")}
    });
});


$(document).ready(function(){
    $("#li1").on({
        'click': function(){$("#sotto_ul1").toggle();},
        mouseleave: function(){$("#sotto_ul1").css("display","none")}
    });
});

$(document).ready(function(){
    $("#li2").on({
        'click': function(){$("#sotto_ul2").toggle();},
        mouseleave: function(){$("#sotto_ul2").css("display","none")}
    });
});

$(document).ready(function(){
    $("#li3").on({
        'click': function(){$("#sotto_ul3").toggle();},
        mouseleave: function(){$("#sotto_ul3").css("display","none")}
    });
});


