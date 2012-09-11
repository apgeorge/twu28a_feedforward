function replaceURLWithHTMLLinks(text) {
    return text.replace(/(ftp|http|https|file):\/\/[\S]+(\b|$)/gim,
    '<a href="$&" target="_blank">$&</a>')
        .replace(/([^\/])(www[\S]+(\b|$))/gim,
    '$1<a href="http://$2" target="_blank">$2</a>');
}
$('#description').ready( function(){
var text = $('#description').html();
        $('#description').html(replaceURLWithHTMLLinks(text));
});
