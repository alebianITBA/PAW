$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

$('.remove-button').click(function() {
  $.ajax({
    type: "DELETE",
    url: $(this).attr('data-href'),
    complete: function() {
      location.reload();
    }
  })
})
