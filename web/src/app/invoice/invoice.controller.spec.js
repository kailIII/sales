import app from '../app';

describe('InvoicesController', () => {

  describe('init', () => {
    let ctrl;
    let data = [1,2,3];

    beforeEach(() => {
      angular.mock.module(app);

      angular.mock.inject(($controller) => {
        ctrl = $controller('InvoicesController', {invoicesResponse: {data}});
      });
    });

    it('should init invoices data correctly', () => {
      ctrl.init();
      expect(ctrl.invoices).toBe(data);
    });
  });

  describe('formatInvoice', () => {
    let ctrl;

    beforeEach(() => {
      angular.mock.module(app);

      angular.mock.inject(($controller) => {
        ctrl = $controller('InvoicesController', {invoicesResponse: {}});
      });
    });

    it('should format an invoice to be shown in the view', () => {
      const invoice = {
        product: { name: 'Product'},
        quantity: 10
      };
      expect(ctrl.formatInvoice(invoice)).toBe('10 x Product');
    });
  });
});