/**
 * Created by leonardo on 12/21/16.
 */

function router($locationProvider, $routeProvider) {
  $locationProvider.html5Mode(true);
  $routeProvider.otherwise('/invoices');
}

router.$inject = ['$locationProvider', '$routeProvider'];

export default router;