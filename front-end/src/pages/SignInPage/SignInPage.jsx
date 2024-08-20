import React, { useState } from "react";
import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { Button, Form, Input } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { ROUTE_PATH } from "../../constants/route";
import * as service from "../../services";
import { useDispatch } from "react-redux";
import userslice from "../../store/userslice";

const SignInPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const dispatch = useDispatch();

  const navigate = useNavigate();

  const handleOnClickLogin = () => {
    service
      .login(email, password)
      .then((res) => {
        if (res.code === 200) {
          dispatch(
            userslice.actions.login({
              token: res.result.token,
              role: res.result.role,
            })
          );
          navigate(-1);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="px-8 py-11">
      <WrapperContent className="bg-white py-11 rounded px-4">
        <h1 className="text-center font-semibold text-2xl uppercase mb-6">
          Đăng nhập
        </h1>

        <Form layout="vertical" className="w-[500px] max-w-full mx-auto">
          <Form.Item
            name="email"
            label={<p className="text-[#003fa3] font-bold text-base">Email</p>}
          >
            <Input
              placeholder="Nhập email"
              className="h-12 text-base"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </Form.Item>

          <Form.Item
            name="password"
            label={
              <p className="text-[#003fa3] font-bold text-base">Mật khẩu</p>
            }
          >
            <Input.Password
              placeholder="Nhập mật khẩu"
              className="h-12 text-base"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Form.Item>

          <Button
            className="bg-[#011bb6] hover:!bg-[#011bb6] h-12 rounded w-full mt-4"
            onClick={handleOnClickLogin}
          >
            <p className="uppercase text-white text-base font-semibold">
              Đăng nhập
            </p>
          </Button>

          <div className="text-center mt-3 text-base">
            <span className="text-[#626579]">Bạn chưa có tài khoản? </span>

            <Link to={ROUTE_PATH.SIGN_UP} className="text-[#f1600d]">
              Đăng ký
            </Link>
          </div>
        </Form>
      </WrapperContent>
    </div>
  );
};

export default SignInPage;
