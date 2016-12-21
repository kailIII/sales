import app from '../app';

describe('ProductsController', () => {

  describe('init', () => {
    let ctrl;
    let data = [1,2,3];

    beforeEach(() => {
      angular.mock.module(app);

      angular.mock.inject(($controller) => {
        ctrl = $controller('ProductsController', {productsResponse: {data}});
      });
    });

    it('should init products data correctly', () => {
      ctrl.init();
      expect(ctrl.products).toBe(data);
    });
  });

  describe('addToInvoice', () => {
    let ctrl;

    beforeEach(() => {
      angular.mock.module(app);

      angular.mock.inject(($controller) => {
        ctrl = $controller('ProductsController', {productsResponse: {}});
        ctrl.init();
      });
    });

    it('should add a new product to an invoice', () => {
      const product = {uuid:'1234', quantity:10};
      ctrl.addToInvoice(product);

      expect(ctrl.invoice.products[0].uuid).toBe('1234');
      expect(ctrl.invoice.products[0].quantity).toBe(10);
    });


    it('should add products to an invoice that has already this product', () => {
      ctrl.invoice.products.push({uuid:'5678', quantity:10});
      ctrl.invoice.products.push({uuid:'1234', quantity:20});

      const product = {uuid:'1234', quantity:10};
      ctrl.addToInvoice(product);

      const used = ctrl.invoice.products.find(p => p.uuid === '1234');
      expect(used.quantity).toBe(30);
    });
  });
});