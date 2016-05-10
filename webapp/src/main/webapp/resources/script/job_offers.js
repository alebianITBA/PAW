$( "#filter-offers" ).click(function() {
  window.location.href = window.location.href.split('?')[0] + "?skill_id=" + $('#skills-select-filter').val();
});
