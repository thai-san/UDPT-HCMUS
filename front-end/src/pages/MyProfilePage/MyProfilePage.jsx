import React, { useState, useEffect } from "react";
import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { Link } from "react-router-dom";

import { FaUser } from "react-icons/fa";
import { MdOutlinePayment } from "react-icons/md";
import { Button, DatePicker, Form, Input, Select } from "antd";
import dayjs from "dayjs";

import { userSelector } from "../../store/userslice";
import { useSelector } from "react-redux";

import { getMyInfoCustomer, updateCustomerInfo } from "../../services";

const MyProfilePage = () => {
  const [id, setId] = useState("");
  const [email, setEmail] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [address, setAddress] = useState("");
  const [gender, setGender] = useState("");
  const [dob, setDob] = useState("");

  const user = useSelector(userSelector);

  useEffect(() => {
    let res;

    getMyInfoCustomer(user.token)
      .then((_res) => {
        res = _res.result;
        setId(res.id);
        setEmail(res.email);
        setFirstName(res.firstName);
        setLastName(res.lastName);
        setAddress(res.address);
        setGender(res.gender);
        setDob(res.dob);
      })
      .catch((err) => {
        res = err.response.data;
        console.log(res);
      });
  }, [user]);

  const handleOnClickSave = () => {
    let res;
    updateCustomerInfo(user.token, {
      id,
      firstName,
      lastName,
      address,
      gender,
      dob,
    })
      .then((_res) => {
        res = _res;
        console.log(res);
      })
      .catch((err) => {
        res = err.response.data;
        console.log(res);
      });
  };

  return (
    <div className="py-6">
      <WrapperContent>
        <div className="grid grid-cols-12 gap-x-3">
          <div className="col-span-4 border border-[#ebecee] rounded self-start">
            <Link
              className="[&:not(:first-child)]:border-t [&:not(:first-child)]:border-t-[border-[#ebecee]] flex items-center h-16 bg-white gap-x-2 px-4 hover:bg-[#e6e8f8]"
              to=""
            >
              <FaUser />
              <p>Cài đặt thông tin cá nhân</p>
            </Link>

            <Link
              className="[&:not(:first-child)]:border-t [&:not(:first-child)]:border-t-[border-[#ebecee]] flex items-center h-16 bg-white gap-x-2 px-4 hover:bg-[#e6e8f8]"
              to="/my-bookings"
            >
              <MdOutlinePayment />
              <p>Lịch sử thanh toán</p>
            </Link>
          </div>

          <div className="col-span-8 bg-white rounded pt-8 pb-4">
            <div className="w-[90%] mx-auto">
              <h2 style={{ fontSize: "40px" }}>
                <b>Thông tin cá nhân</b>
              </h2>

              <Form layout="vertical" className="mt-10">
                <Form.Item
                  label={<p className="text-base font-semibold">Họ</p>}
                >
                  <Input
                    placeholder="Nhập họ của bạn"
                    className="h-10"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                  />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Tên</p>}
                >
                  <Input
                    placeholder="Nhập tên của bạn"
                    className="h-10"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                  />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Email</p>}
                >
                  <Input disabled className="h-10" value={email} />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Địa chỉ</p>}
                >
                  <Input
                    placeholder="Nhập địa chỉ"
                    className="h-10"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                  />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Ngày sinh</p>}
                >
                  <DatePicker
                    className="h-10"
                    value={dayjs(dob, "YYYY-MM-DD")}
                    format="YYYY-MM-DD"
                    style={{ width: "100%" }}
                    onChange={(_, dateStr) => setDob(dateStr)}
                  />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Giới tính</p>}
                >
                  <Select
                    placeholder="Chọn giới tính"
                    className="h-10"
                    value={gender}
                    onChange={(value) => setGender(value)}
                  >
                    <Select.Option value="M">Nam</Select.Option>
                    <Select.Option value="F">Nữ</Select.Option>
                    <Select.Option value="O">Khác</Select.Option>
                  </Select>
                </Form.Item>
              </Form>
            </div>

            <div className="px-3 border-t border-t-[#ddeaff] pt-4 flex gap-x-2 justify-center">
              <Button
                className="bg-[#011bb6] hover:!bg-[#011bb6] h-10 rounded w-40 max-w-full"
                onClick={handleOnClickSave}
              >
                <p className="uppercase text-white text-base font-semibold">
                  Lưu
                </p>
              </Button>
            </div>
          </div>
        </div>
      </WrapperContent>
    </div>
  );
};

export default MyProfilePage;
