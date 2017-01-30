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

    var devBaseUrl = 'http://localhost:8080/grupo5';
    var prodBaseUrl = 'http://pawserver.it.itba.edu.ar/grupo5';
    var basePath = '';
    var baseUrl = prodBaseUrl;
    var apiBaseUrl = baseUrl + '/api/v1';
    connectOn.constants = {
      TOKEN_KEY: 'token',
      SESSION_STATUS_KEY: 'session_status',
      BASE_URL: baseUrl,
      API_V1_BASE_URL: apiBaseUrl,
      LOGGED_USER: 'logged_user',

      BASE_PATH: basePath,
      PATH_ROOT: basePath + '/',
      PATH_INDEX: basePath + '/index',
      PATH_ONBOARDING: basePath + '/onboarding'
    };

    return connectOn;
  }
);
