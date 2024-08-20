import classNames from "classnames";
import React from "react";

const WrapperContent = ({ children, className }) => {
  return (
    <div className={classNames("w-[1200px] max-w-full mx-auto", className)}>
      {children}
    </div>
  );
};

export default WrapperContent;
