/**
 * Created by leonardo on 12/20/16.
 */

import template from './invoice.view.html';
import { invoicesControllerName } from './invoice.controller';

function router($routeProvider) {
  $routeProvider.when("/invoices", {
      template,
      controller: invoicesControllerName,
      controllerAs: "ICtrl",
      resolve: {
        invoicesResponse: ['InvoiceService', (InvoiceService) => InvoiceService.findAll()]
      }
    }
  );
}

router.$inject = ['$routeProvider'];

export default router;