/**
 * Created by leonardo on 12/20/16.
 */

import template from './products.view.html';
import {productsControllerName} from './products.controller';

function router($routeProvider) {
  $routeProvider.when("/products", {
      template,
      controller: productsControllerName,
      controllerAs: "PCtrl",
      resolve: {
        productsResponse: ['ProductService', (ProductService) => ProductService.findAll()]
      }
    }
  );
}

router.$inject = ['$routeProvider'];

export default router;