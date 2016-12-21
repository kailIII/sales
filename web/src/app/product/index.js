/**
 * Created by leonardo on 12/21/16.
 */
import angular from 'angular';
import ngRoute from 'angular-route';
import toastr from 'angular-toastr';

import {productServiceName, productService} from './product.service';
import {productsControllerName, productsController} from './products.controller';
import productRouter from './product.router';

import '../../../node_modules/angular-toastr/dist/angular-toastr.min.css';

const MODULE_NAME = 'app.product';

angular.module(MODULE_NAME, [ngRoute, toastr])
  .service(productServiceName, productService)
  .controller(productsControllerName, productsController)
  .config(productRouter);

export default MODULE_NAME;