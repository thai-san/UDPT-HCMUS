import React, { useEffect } from "react";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import AppRouter from "./components/approuter";
import { useDispatch } from "react-redux";
import userslice from "./store/userslice";
import { introspect, getUserInfo } from "./services";

const App = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    const token = localStorage.getItem("event-app-token");
    if (token) {
      introspect(token)
        .then((res) => {
          if (res.code === 200 && res.result.valid) {
            getUserInfo(token)
              .then((res) => {
                if (res.code === 200)
                  dispatch(
                    userslice.actions.login({ token, role: res.result.role })
                  );
              })
              .catch((err) => {
                dispatch(userslice.actions.logout());
              });
          } else dispatch(userslice.actions.logout());
        })
        .catch((err) => {
          dispatch(userslice.actions.logout());
        });
    }
  }, []);

  return (
    <div className="App">
      <div className="app-content">
        <AppRouter />
        <ToastContainer
          position="top-right"
          autoClose={3000}
          hideProgressBar={true}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="light"
        />
      </div>
    </div>
  );
};

export default App;
