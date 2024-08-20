import axios from "../axios.js";

const p = "http://localhost:8080/api";

export const login = async (email, password) => {
  return axios.post(`${p}/identity/auth/token`, { email, password });
};

export const introspect = async (token) => {
  return axios.post(`${p}/identity/auth/introspect`, { token });
};

export const getUserInfo = async (token) => {
  return axios({
    method: "get",
    url: `${p}/identity/users/my-info`,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export const logout = async (token) => {
  return axios.post(`${p}/identity/auth/logout`, { token });
};

export const getMyInfoCustomer = async (token) => {
  return axios.get(`${p}/customer/my-info`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export const getMyInfoEnterprise = async (token) => {
  return axios.get(`${p}/enterprise/my-info`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export const updateCustomerInfo = async (token, payload) => {
  return axios({
    method: "put",
    url: `${p}/customer/${payload.id}`,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: payload,
  });
};

export const updateEnterpriseInfo = async (token, payload) => {
  return axios({
    method: "put",
    url: `${p}/enterprise/${payload.id}`,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: payload,
  });
};

export const getListEvents = async () => {
  return axios({
    method: "get",
    url: `${p}/event/get`,
  });
};

export const getEvent = async (id) => {
  return axios({
    method: "get",
    url: `${p}/event/get/${id}`,
  });
};

export const getMyEvents = async (token) => {
  return axios({
    method: "get",
    url: `${p}/event/my-events`,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export const getEnterprise = async (email) => {
  return axios({
    method: "get",
    url: `${p}/enterprise/get/${email}`,
  });
};

export const getEnterpriseByEmail = async (email) => {
  return axios({
    method: "get",
    url: `${p}/enterprise/get-by-email/${email}`,
  });
};

export const buyTicket = async (token, payload) => {
  return axios({
    method: "post",
    url: `${p}/booking/create`,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: payload,
  });
};

export const getMyBookings = async (token) => {
  return axios({
    method: "get",
    url: `${p}/booking/my-bookings`,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export const createEvent = async (token, payload) => {
  return axios({
    method: "post",
    url: `${p}/event/create`,
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: payload,
  });
};

export const createCustomer = async (payload) => {
  return axios({
    method: "post",
    url: `${p}/identity/users/customer/registration`,
    data: payload,
  });
};

export const createEnterprise = async (payload) => {
  return axios({
    method: "post",
    url: `${p}/identity/users/enterprise/registration`,
    data: payload,
  });
};
