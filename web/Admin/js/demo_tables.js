
    function delete_row(row,productId){
        
        var box = $("#mb-remove-row");
        box.addClass("open");
        
        box.find(".mb-control-yes").on("click",function(){
            box.removeClass("open");
            $("#"+row).hide("slow",function(){
                $(this).remove();
                document.location = "Product?productMode=delete&id="+productId;
            });
        });
        
    }
