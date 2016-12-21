/**
 * Created by leonardo on 12/20/16.
 */

const SERVICE_NAME = 'ProductService';

function productService($http) {
  return {
    findAll: (version = 'v1') => {
      const req = {
        method: 'GET',
        url: `http://localhost:9000/${version}/products`,
      };
      return $http(req);
    },
  };
}

export { SERVICE_NAME as productServiceName, productService };