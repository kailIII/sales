/**
 * Created by leonardo on 12/20/16.
 */
import angular from 'angular';

const CONTROLLER_NAME = 'ProductsController';

function productsController(productsResponse, InvoiceService, toastr) {

  const initInvoice = () => {
    this.invoice = { products: [] };
  };

  this.init = () => {
    this.products = productsResponse.data;
    initInvoice();
  };

  this.addToInvoice = (product) => {
    const found = this.invoice.products.find(item => item.uuid === product.uuid);
    if (found) {
      found.quantity += product.quantity;
    } else {
      this.invoice.products.push(angular.copy(product));
    }
    product.quantity = undefined;
  };

  this.removeFromInvoice = (product) => {
    const found = this.invoice.products.find(item => item.uuid === product.uuid);
    const index = this.invoice.products.indexOf(found);
    if (index >= 0) {
      this.invoice.products.splice(index, 1);
    }
  };

  this.createInvoice = (invoice) => {
    const products = invoice.products.map(product => {
      return {product: product.uuid, quantity: product.quantity};
    });
    InvoiceService.create(invoice.documentNumber, products)
      .then(data => {
        toastr.success('Invoice was generated successfully! You can check it sooner on your list!', 'Success');
        initInvoice();
      })
      .catch(err => {
        toastr.error(err.data.message, 'Error');
      });
  }
}

productsController.$inject = ['productsResponse', 'InvoiceService', 'toastr'];

export { CONTROLLER_NAME as productsControllerName, productsController };