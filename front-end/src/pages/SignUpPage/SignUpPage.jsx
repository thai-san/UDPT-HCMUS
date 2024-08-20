import React, { useState } from "react";
import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { Button, DatePicker, Form, Input, Select } from "antd";
import { Link } from "react-router-dom";
import { ROUTE_PATH } from "../../constants/route";
import dayjs from "dayjs";
import { toast } from "react-toastify";

import { createCustomer, createEnterprise } from "../../services";

const SignUpPage = () => {
  const [role, setRole] = useState("CUSTOMER");

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [address, setaddress] = useState("");

  const [firstName, setfirstName] = useState("");
  const [lastName, setlastName] = useState("");
  const [gender, setgender] = useState("M");
  const [dob, setdob] = useState("2000-01-01");

  const [name, setname] = useState("");
  const [phone, setphone] = useState("");

  const handleOnClickSignUp = () => {
    if (role === "CUSTOMER") {
      createCustomer({
        email,
        password,
        firstName,
        lastName,
        address,
        gender,
        dob,
      })
        .then((res) => {
          if (res.code === 200) toast.success("Đăng ký thành công");
        })
        .catch((err) => toast.success("Đăng ký không thành công"));
    } else
      createEnterprise({ email, password, name, address, phone })
        .then((res) => {
          toast.success("Đăng ký thành công");
        })
        .catch((err) => toast.success("Đăng ký không thành công"));
  };

  return (
    <div className="px-8 py-11">
      <WrapperContent className="bg-white py-11 rounded px-4">
        <h1 className="text-center font-semibold text-2xl uppercase mb-6">
          Đăng ký
        </h1>

        <Form layout="vertical" className="w-[500px] max-w-full mx-auto">
          <Form.Item
            label={
              <p className="text-[#003fa3] font-bold text-base">Bạn là?</p>
            }
          >
            <Select
              value={role}
              placeholder="Chọn vai trò"
              className="h-12 text-base"
              onChange={(value) => setRole(value)}
            >
              <Select.Option value="CUSTOMER">
                Người tham gia sự kiện
              </Select.Option>
              <Select.Option value="ENTERPRISE">
                Người tạo sự kiện
              </Select.Option>
            </Select>
          </Form.Item>

          <Form.Item
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

          <Form.Item
            label={
              <p className="text-[#003fa3] font-bold text-base">Địa chỉ</p>
            }
          >
            <Input
              placeholder="Nhập địa chỉ"
              className="h-12 text-base"
              value={address}
              onChange={(e) => setaddress(e.target.value)}
            />
          </Form.Item>

          {role === "CUSTOMER" ? (
            <>
              <Form.Item
                label={<p className="text-[#003fa3] font-bold text-base">Họ</p>}
              >
                <Input
                  placeholder="Nhập họ của bạn"
                  className="h-10"
                  value={lastName}
                  onChange={(e) => setlastName(e.target.value)}
                />
              </Form.Item>

              <Form.Item
                label={
                  <p className="text-[#003fa3] font-bold text-base">Tên</p>
                }
              >
                <Input
                  placeholder="Nhập tên của bạn"
                  className="h-10"
                  value={firstName}
                  onChange={(e) => setfirstName(e.target.value)}
                />
              </Form.Item>

              <Form.Item
                label={
                  <p className="text-[#003fa3] font-bold text-base">
                    Ngày sinh
                  </p>
                }
              >
                <DatePicker
                  className="h-10"
                  value={dayjs(dob, "YYYY-MM-DD")}
                  format="YYYY-MM-DD"
                  style={{ width: "100%" }}
                  onChange={(_, dateStr) => setdob(dateStr)}
                />
              </Form.Item>

              <Form.Item
                label={
                  <p className="text-[#003fa3] font-bold text-base">
                    Giới tính
                  </p>
                }
              >
                <Select
                  placeholder="Chọn giới tính"
                  className="h-10"
                  value={gender}
                  onChange={(value) => setgender(value)}
                >
                  <Select.Option value="M">Nam</Select.Option>
                  <Select.Option value="F">Nữ</Select.Option>
                  <Select.Option value="O">Khác</Select.Option>
                </Select>
              </Form.Item>
            </>
          ) : (
            <>
              <Form.Item
                label={
                  <p className="text-[#003fa3] font-bold text-base">Tên</p>
                }
              >
                <Input
                  placeholder="Nhập tên"
                  className="h-10"
                  value={name}
                  onChange={(e) => setname(e.target.value)}
                />
              </Form.Item>
              <Form.Item
                label={
                  <p className="text-[#003fa3] font-bold text-base">
                    Số điện thoại
                  </p>
                }
              >
                <Input
                  placeholder="Nhập số điện thoại"
                  className="h-10"
                  value={phone}
                  onChange={(e) => setphone(e.target.value)}
                />
              </Form.Item>
            </>
          )}

          <Button
            className="bg-[#011bb6] hover:!bg-[#011bb6] h-12 rounded w-full mt-4"
            onClick={handleOnClickSignUp}
          >
            <p className="uppercase text-white text-base font-semibold">
              Đăng ký
            </p>
          </Button>

          <div className="text-center mt-3 text-base">
            <span className="text-[#626579]">Bạn đã có tài khoản? </span>

            <Link to={ROUTE_PATH.SIGN_IN} className="text-[#f1600d]">
              Đăng nhập
            </Link>
          </div>
        </Form>
      </WrapperContent>
    </div>
  );
};

export default SignUpPage;
