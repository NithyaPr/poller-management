// We have restricted importing of axios
// so that it cannot be imported outside of this file
// eslint-disable-next-line no-restricted-imports
import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios';

import { axiosRequest, CustomRequestConfig } from './axios-request';

export const getCsrfHeaders = () => {
  return {
    'X-Requested-With': 'XMLHttpRequest',
    'content-type': 'application/json',
  };
};

export type RequestConfig = Omit<
  AxiosRequestConfig,
  // data, url, and method, are handled by our request functions (getRequest etc)
  | 'data'
  | 'url'
  | 'method'
  // users of this file should not set these, if anyone wants to add a transformation
  // it should be done in this axios wrapper
  | 'axios-retry'
> &
  CustomRequestConfig;

/**
 * Makes a `GET` request to `url` by wrapping axios and transforming the snake_case response body to camelCase.
 * See https://github.com/webbhalsa/kry-emr-frontend/blob/release/apps/careportal-v2/src/shared/request/README.md
 */
export const getRequest = <T>(
  url: string,
  requestConfig: RequestConfig = {},
): Promise<AxiosResponse<T>> => {
  return axiosRequest({
    headers: getCsrfHeaders(),
    ...requestConfig,
    method: 'GET',
    url,
  });
};

/**
 * Makes a `DELETE` request to `url` by wrapping axios and transforming the snake_case response body to camelCase.
 * See https://github.com/webbhalsa/kry-emr-frontend/blob/release/apps/careportal-v2/src/shared/request/README.md
 */
export const deleteRequest = <T>(
  url: string,
  requestConfig: RequestConfig = {},
  data?: any,
): Promise<AxiosResponse<T>> => {
  return axiosRequest({
    headers: getCsrfHeaders(),
    ...requestConfig,
    method: 'DELETE',
    url,
    data,
  });
};

/**
 * Makes a `HEAD` request to `url` by wrapping axios and transforming the snake_case response body to camelCase.
 * See https://github.com/webbhalsa/kry-emr-frontend/blob/release/apps/careportal-v2/src/shared/request/README.md
 */
export const headRequest = <T>(
  url: string,
  requestConfig: RequestConfig = {},
): Promise<AxiosResponse<T>> => {
  return axiosRequest({
    headers: getCsrfHeaders(),
    ...requestConfig,
    method: 'HEAD',
    url,
  });
};

/**
 * Makes a `POST` request to `url` by wrapping axios and transforming the request body from camelCase to snake_case,
 * and snake_case response body to camelCase.
 * See https://github.com/webbhalsa/kry-emr-frontend/blob/release/apps/careportal-v2/src/shared/request/README.md
 */
export const postRequest = <T>(
  url: string,
  data?: any,
  requestConfig: RequestConfig = {},
): Promise<AxiosResponse<T>> => {
  return axiosRequest({
    headers: getCsrfHeaders(),
    ...requestConfig,
    method: 'POST',
    url,
    data,
  });
};

/**
 * Makes a `PUT` request to `url` by wrapping axios and transforming the request body from camelCase to snake_case,
 * and snake_case response body to camelCase.
 * See https://github.com/webbhalsa/kry-emr-frontend/blob/release/apps/careportal-v2/src/shared/request/README.md
 */
export const putRequest = <T>(
  url: string,
  data?: any,
  requestConfig: RequestConfig = {},
): Promise<AxiosResponse<T>> => {
  return axiosRequest({
    headers: getCsrfHeaders(),
    ...requestConfig,
    method: 'PUT',
    url,
    data,
  });
};

/**
 * Makes a `PATCH` request to `url` by wrapping axios and transforming the request body from camelCase to snake_case,
 * and snake_case response body to camelCase.
 * See https://github.com/webbhalsa/kry-emr-frontend/blob/release/apps/careportal-v2/src/shared/request/README.md
 */
export const patchRequest = <T>(
  url: string,
  data?: any,
  requestConfig: RequestConfig = {},
): Promise<AxiosResponse<T>> => {
  return axiosRequest({
    headers: getCsrfHeaders(),
    ...requestConfig,
    method: 'PATCH',
    url,
    data,
  });
};

/**
 * Check if an caught error is an error thrown from the request
 * Works as a type-guard for the AxiosError type
 */
export const isRequestError = axios.isAxiosError;
export type RequestError<T> = AxiosError<T>;
