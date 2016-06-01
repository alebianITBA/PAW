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

$('.finish-button').click(function() {
  $.ajax({
    type: "PUT",
    url: $(this).attr('data-href'),
    complete: function() {
      location.reload();
    }
  })
})

$('.reopen-button').click(function() {
  $.ajax({
    type: "PUT",
    url: $(this).attr('data-href'),
    complete: function() {
      location.reload();
    }
  })
})
