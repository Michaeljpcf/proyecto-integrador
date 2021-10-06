// CREAMOS LA URL
export let CreateUrl = {
    fnc: function(value){
        value = value.toLowerCase();
        value = value.replace(/[ ]/g, "-");
        value = value.replace(/[á]/g, "a");
        value = value.replace(/[é]/g, "e");
        value = value.replace(/[í]/g, "i");
        value = value.replace(/[ó]/g, "o");
        value = value.replace(/[ú]/g, "u");
        value = value.replace(/[ñ]/g, "n");

        return value;
    }
}