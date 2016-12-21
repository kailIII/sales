/**
 * Created by leonardo on 12/20/16.
 */
const CONTROLLER_NAME = 'InvoicesController';

function invoicesController(invoicesResponse) {
  this.invoices = invoicesResponse.data;

  this.formatInvoice = (invoiceProduct) => {
    return `${invoiceProduct.quantity} x ${invoiceProduct.product.name}`;
  };
}

invoicesController.$inject = ['invoicesResponse'];

export { CONTROLLER_NAME as invoicesControllerName, invoicesController };