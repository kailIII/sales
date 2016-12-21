import angular from 'angular';

import productModule from './product';
import invoiceModule from './invoice';
import router from './router';

import '../style/main.css';
import '../style/products.css';
import '../style/invoices.css';

const MODULE_NAME = 'app';

angular.module(MODULE_NAME, [productModule, invoiceModule])
  .config(router);

export default MODULE_NAME;