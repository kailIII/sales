/**
 * Created by leonardo on 12/21/16.
 */
import angular from 'angular';
import ngRoute from 'angular-route';

import {invoiceServiceName, invoiceService} from './invoice.service';
import {invoicesControllerName, invoicesController} from './invoice.controller';
import invoiceRouter from './invoice.router';

const MODULE_NAME = 'app.invoice';

angular.module(MODULE_NAME, [ngRoute])
  .service(invoiceServiceName, invoiceService)
  .controller(invoicesControllerName, invoicesController)
  .config(invoiceRouter);

export default MODULE_NAME;