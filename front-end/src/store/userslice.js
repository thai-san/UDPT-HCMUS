import { createSlice } from "@reduxjs/toolkit";

export default createSlice({
  name: "user",
  initialState: {
    token: "",
    role: "",
  },
  reducers: {
    login: (state, action) => {
      state.token = action.payload.token;
      state.role = action.payload.role;
      localStorage.setItem("event-app-token", action.payload.token);
    },
    logout: (state, action) => {
      state.token = "";
      state.role = "";
      localStorage.removeItem("event-app-token");
    },
  },
});

export const userSelector = (state) => state.user;
