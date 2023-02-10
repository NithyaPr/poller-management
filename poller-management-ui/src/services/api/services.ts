import {
    getRequest,
    postRequest,
    putRequest,
    deleteRequest,
    RequestConfig,
  } from '../../apis/request';

  export type GetServicesResponse = {
    id: number;
    url: string;
    name?: string;
    serviceUrl?: string;
    serviceName?: string;
    status?: string;
    createdBy?: string;
    createdDate?: string;
    modifiedDate?: string;
    lastActiveDate?: string;
    statusDate?: string;
  };

  export type CreateOrUpdateServiceRequest = {
    id?: number,
    serviceUrl?: string;
    serviceName?: string;
  };
  
  export type GetServicesRequest = {
    id: string
  };

  //MOCK starts
  var services = [
    {
      id: 1,
      url: 'url 1',
      name: 'service 1',
      status: 'started',
      createdBy: 'admin'
    },
    {
      id: 2,
      url: 'url 2',
      name: 'service 2',
      status: 'started',
      createdBy: 'admin'
    }
  ]
  //MOCK ends
  export const getServices = (): Promise<Array<GetServicesResponse>> =>
    getRequest<Promise<Array<GetServicesResponse>>>(
        '/v1/service?user=admin',
      ).then(({ data }) => data);

  export const getService = (serviceId: string): Promise<GetServicesResponse> =>
    getRequest<{ id: number; url: string }>(
        '/v1/service/'+serviceId,
      ).then(({ data }) => ({
          id: data.id,
          url: data.url,
      }));

  export const createService = (service: CreateOrUpdateServiceRequest): Promise<GetServicesResponse> => {
    console.log("service in name " + service.serviceName)
    console.log("service in url " + service.serviceUrl)

    return postRequest<{ id: number; url: string }>(
        '/v1/service',
        service
      ).then(({ data }) => ({
          id: data.id,
          url: data.url,
      }));
    }

  export const editService = (service: CreateOrUpdateServiceRequest): Promise<GetServicesResponse> =>
    putRequest<{ id: number; url: string }>(
        '/v1/service/'+service.id,
        service
      ).then(({ data }) => ({
          id: data.id,
          url: data.url,
      }));

  export const deleteService = (serviceId: string): Promise<String> =>
    deleteRequest(
        '/v1/service/'+serviceId
      ).then();


