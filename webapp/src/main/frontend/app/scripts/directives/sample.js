'use strict';
define(['connectOn'], function(connectOn) {

	connectOn.directive('sample', function() {
		return {
			restrict: 'E',
			template: '<span>Sample</span>'
		};
	});
});
