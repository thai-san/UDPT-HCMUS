import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { FaUser } from "react-icons/fa";
import { MdOutlinePayment } from "react-icons/md";
import { Button, Form, Input } from "antd";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";

import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { getMyInfoEnterprise, updateEnterpriseInfo } from "../../services";
import { userSelector } from "../../store/userslice";

const EnterpriseProfile = () => {
  const [id, setId] = useState("");
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [phone, setPhone] = useState("");

  const user = useSelector(userSelector);

  useEffect(() => {
    getMyInfoEnterprise(user.token)
      .then((res) => {
        setId(res.result.id);
        setEmail(res.result.email);
        setName(res.result.name);
        setAddress(res.result.address);
        setPhone(res.result.phone);
      })
      .catch((err) => console.log(err));
  }, [user]);

  const handleOnClickSave = () => {
    updateEnterpriseInfo(user.token, {
      id,
      name,
      address,
      phone,
    })
      .then((res) => {
        if (res.code === 200) toast.success("Cập nhật thành công");
      })
      .catch((err) => {
        toast.error("Không thể cập nhật");
        console.log(err);
      });
  };

  return (
    <div className="py-6">
      <WrapperContent>
        <div className="grid grid-cols-12 gap-x-3">
          <div className="col-span-4 border border-[#ebecee] rounded self-start">
            <Link
              to=""
              className="[&:not(:first-child)]:border-t [&:not(:first-child)]:border-t-[border-[#ebecee]] flex items-center h-16 bg-white gap-x-2 px-4 hover:bg-[#e6e8f8]"
            >
              <FaUser />
              <p>Cài đặt thông tin doanh nghiệp</p>
            </Link>

            <Link
              to="/enterprise-events"
              className="[&:not(:first-child)]:border-t [&:not(:first-child)]:border-t-[border-[#ebecee]] flex items-center h-16 bg-white gap-x-2 px-4 hover:bg-[#e6e8f8]"
            >
              <MdOutlinePayment />
              <p>Sự kiện của tôi</p>
            </Link>
          </div>

          <div className="col-span-8 bg-white rounded pt-8 pb-4">
            <div className="w-[90%] mx-auto">
              <h2 style={{ fontSize: "40px" }}>
                <b>Thông tin cá nhân</b>
              </h2>

              <Form layout="vertical" className="mt-6">
                <Form.Item
                  label={<p className="text-base font-semibold">Tên</p>}
                >
                  <Input
                    placeholder="Nhập tên"
                    className="h-10"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
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
                  label={
                    <p className="text-base font-semibold">Số điện thoại</p>
                  }
                >
                  <Input
                    placeholder="Nhập số điện thoại"
                    className="h-10"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                  />
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

export default EnterpriseProfile;
