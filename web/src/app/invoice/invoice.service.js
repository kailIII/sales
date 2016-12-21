/**
 * Created by leonardo on 12/20/16.
 */

const SERVICE_NAME = 'InvoiceService';

function invoiceService($http) {
  return {
    findAll: (version = 'v1') => {
      const req = {
        method: 'GET',
        url: `http://localhost:9000/${version}/invoices`,
      };
      return $http(req);
    },
    create: (documentNumber, products, version = 'v1') => {
      const req = {
        method: 'POST',
        url: `http://localhost:9000/${version}/invoices`,
        data: { documentNumber, products },
      };
      return $http(req);
    },
  };
}

export { SERVICE_NAME as invoiceServiceName, invoiceService };