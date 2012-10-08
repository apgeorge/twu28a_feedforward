function replaceURLWithHTMLLinks(text) {
    return text.replace(/(ftp|http|https|file):\/\/[\S]+(\b|$)/gim,
    '<a href="$&" target="_blank">$&</a>')
        .replace(/([^\/])(www[\S]+(\b|$))/gim,
    '$1<a href="http://$2" target="_blank">$2</a>');
}
function set_inplace_edit_for(item_to_click, item_to_edit, type, text_size) {
        $('#'+item_to_click).click(function () {
            $("#"+item_to_edit).editInPlace({
                url:"edit_talk.html",
                show_buttons:true,
                params:"type="+item_to_edit+"&talk_id=" + $('#talk_details').attr('talk-id'),
                bg_over:"inherit",
                field_type:type,
                saving_image:"static/images/loading.gif",
                text_size: text_size
            });
            $('#'+item_to_edit).trigger('click_for_edit');
        });
}
$('#description').ready( function(){
var text = $('#description').html();
        $('#description').html(replaceURLWithHTMLLinks(text));
});

$('#talk_details').ready(function(){
    set_inplace_edit_for('edit_talk_description', 'description', 'textarea', 500);
    set_inplace_edit_for('edit_talk_venue', 'venue', 'text', 50);
    set_inplace_edit_for('edit_talk_date', 'date', 'date', 50);
    set_inplace_edit_for('edit_talk_time', 'time', 'time', 50);


});
