var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

total_users = parseInt($('#pagination').attr('data-value'))
current_page = getUrlParameter('page')
if (current_page == null) {
  current_page = 1
}
per_page = 20
top_page = Math.ceil(total_users / per_page)

middle = ''
if (current_page <= 1) {
  middle = middle + '<li class="disabled"><span aria-hidden="true">&laquo;</span></li>'
} else {
  middle = middle + '<li><a href="' + window.location.href.split("?")[0] + '?page=' + (parseInt(current_page) - 1).toString() + '" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>'
}

middle = middle +'<li class="active"><a href="#">' + current_page + ' <span class="sr-only">(current)</span></a></li>'

if (current_page == top_page) {
  middle = middle + '<li class="disabled"><span aria-hidden="true">&raquo;</span></li>'
} else {
  middle = middle + '<li><a href="' + window.location.href.split("?")[0] + '?page=' + (parseInt(current_page) + 1).toString() + '" aria-label="Previous"><span aria-hidden="true">&raquo;</span></a></li>'
}
$('#pagination').append('<nav><ul class="pagination">' + middle + '</ul></nav>')
