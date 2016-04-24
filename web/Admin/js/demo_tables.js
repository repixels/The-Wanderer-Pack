
    function delete_row(row,itemType,itemId){
        
        var box = $("#mb-remove-row");
        box.addClass("open");
        
        box.find(".mb-control-yes").on("click",function(){
            box.removeClass("open");
            $("#"+row).hide("slow",function(){
                $(this).remove();
                if(itemType === 'product')
                    document.location = "Product?productMode=delete&id="+itemId;
                else if(itemType === 'category')
                {
                    document.location = "Category?categoryMode=delete&id="+itemId;
                }
            });
        });
        
    }
