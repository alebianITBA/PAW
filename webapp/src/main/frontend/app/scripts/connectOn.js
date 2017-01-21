'use strict';
define(['routes',
	'services/dependencyResolverFor',
	'i18n/i18nLoader!',
	'angular',
	'angular-route',
	'bootstrap',
	'angular-translate',
	'angular-local-storage'],
	function(config, dependencyResolverFor, i18n) {
		var connectOn = angular.module('connectOn', [
			'ngRoute',
			'pascalprecht.translate',
			'LocalStorageModule'
		]);
		connectOn
			.config(
				['$routeProvider',
				'$locationProvider',
				'$controllerProvider',
				'$compileProvider',
				'$filterProvider',
				'$provide',
				'$translateProvider',
				'localStorageServiceProvider',
				function($routeProvider, $locationProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, $translateProvider, localStorageServiceProvider) {

					connectOn.controller = $controllerProvider.register;
					connectOn.directive = $compileProvider.directive;
					connectOn.filter = $filterProvider.register;
					connectOn.factory = $provide.factory;
					connectOn.service = $provide.service;

					if (config.routes !== undefined) {
						angular.forEach(config.routes, function(route, path) {
							$routeProvider.when(path, {templateUrl: route.templateUrl, resolve: dependencyResolverFor(['controllers/' + route.controller]), controller: route.controller, gaPageTitle: route.gaPageTitle});
						});
					}
					if (config.defaultRoutePath !== undefined) {
						$routeProvider.otherwise({redirectTo: config.defaultRoutePath});
					}
					$locationProvider.html5Mode(true);

					$translateProvider.translations('preferredLanguage', i18n);
					$translateProvider.preferredLanguage('preferredLanguage');

					localStorageServiceProvider.setPrefix('connectOn');
				}]);

		connectOn.constants = {
			TOKEN_KEY: 'token',
			API_V1_BASE_URL: 'http://localhost:8080/api/v1',
			LOGGED_USER: 'logged_user',

			PATH_ROOT: '/',
			PATH_INDEX: '/index'
		};

		return connectOn;
	}
);
