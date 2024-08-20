import React from "react";
import { Outlet } from "react-router-dom";
import ClientHeader from "./ClientHeader";
import ClientFooter from "./ClientFooter";

const ClientLayout = () => {
  return (
    <>
      <ClientHeader />
      <div className="bg-[#eff5ff]">
        <Outlet />
      </div>
      <ClientFooter />
    </>
  );
};

export default ClientLayout;
