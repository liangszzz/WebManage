(function($){
    $.fn.extend({
        validateForm:function(option){
            var defaultSetting = {
                max:function (val) {

                },
                min:function () {
                    
                },
                isNumber:function () {
                    
                },
                isNumberOverZero:function () {
                    
                },
                maxLength:function () {
                    
                },
                minLength:function () {
                    
                }
            };
            var setting = $.extend(defaultSetting,option);
            this.css("color",setting.colorStr).css("fontSize",setting.fontSize+"px");
            return this;
        }
    });
}(jQuery));