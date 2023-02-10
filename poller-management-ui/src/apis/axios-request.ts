// We have restricted importing of axios
// so that it cannot be imported outside of this file
// eslint-disable-next-line no-restricted-imports
import axios, { AxiosRequestConfig } from 'axios';
import axiosRetry, {
  exponentialDelay,
  IAxiosRetryConfig,
  isNetworkError,
  isRetryableError,
} from 'axios-retry';
import { cloneDeep } from 'lodash';

export const axiosInstance = axios.create({baseURL: 'http://localhost:8081'});
axiosRetry(axiosInstance, {
  // Retries are disabled by default.
  retries: 0,
  retryCondition: (error) => isNetworkError(error) || isRetryableError(error),
  retryDelay: exponentialDelay,
});

export const { CancelToken } = axios;

export type CustomRequestConfig = {
  
  /**
   * If `true`, the request will be retried when it fails with a network or 5xx error.
   *
   * For more control, pass in [axios-retry options](https://github.com/softonic/axios-retry#options).
   */
  retry?: boolean | IAxiosRetryConfig;
};

type AxiosRequestWrapperConfig = AxiosRequestConfig & CustomRequestConfig;

/* Removes custom request config keys from the config */
// This function is used to ensure that we only pass axios config to axios
const axiosWrapperConfigToAxiosConfig = (
  requestConfig: AxiosRequestWrapperConfig,
): AxiosRequestConfig => {
  const strippedRequestConfig: AxiosRequestWrapperConfig = {
    ...requestConfig,
  };

  delete strippedRequestConfig.retry;

  return strippedRequestConfig;
};

/** Wrapper around axios */
export const axiosRequest = <T>(requestConfig: AxiosRequestWrapperConfig) => {
  const axiosConfig = cloneDeep(axiosWrapperConfigToAxiosConfig(requestConfig));

  const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  if (timezone) {
    if (!axiosConfig.headers) {
      axiosConfig.headers = {};
    }
    const TIMEZONE_HEADER = 'X-Client-TimeZone';
    axiosConfig.headers = {
      ...axiosConfig.headers,
      [TIMEZONE_HEADER]: timezone,
    };
  }

  const {
    retry = false,
  } = requestConfig;
 
  if (retry) {
    axiosConfig['axios-retry'] = {
      ...axiosConfig['axios-retry'],
      retries: 2,
      ...(retry === true ? {} : retry),
    };
  }

  return axiosInstance.request<T>(axiosConfig).then((response) => {
   
    const clonedResponse = cloneDeep(response);
    return clonedResponse;
  });
};
