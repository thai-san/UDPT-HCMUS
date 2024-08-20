import React, { useState, useEffect } from "react";
import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { Link } from "react-router-dom";

import { FaUser } from "react-icons/fa";
import { MdOutlinePayment } from "react-icons/md";

import { userSelector } from "../../store/userslice";
import { useSelector } from "react-redux";

import { getMyBookings, getEvent } from "../../services";
import { Tag } from "antd";

const MyBookings = () => {
  const [bookings, setBookings] = useState([]);

  const user = useSelector(userSelector);

  useEffect(() => {
    getMyBookings(user.token)
      .then((res) => {
        if (res.code === 200) {
          let temp = res.result;
          if (temp.length > 0) {
            temp.map((item) =>
              getEvent(item.eventId)
                .then((_res) => (item.eventName = _res.result.name))
                .catch((err) => console.log(err))
            );
          }
          setBookings(temp);
        }
      })
      .catch((err) => console.log(err));
  }, [user]);

  return (
    <div className="py-6">
      <WrapperContent>
        <div className="grid grid-cols-12 gap-x-3">
          <div className="col-span-4 border border-[#ebecee] rounded self-start">
            <Link
              className="[&:not(:first-child)]:border-t [&:not(:first-child)]:border-t-[border-[#ebecee]] flex items-center h-16 bg-white gap-x-2 px-4 hover:bg-[#e6e8f8]"
              to="/profile"
            >
              <FaUser />
              <p>Cài đặt thông tin cá nhân</p>
            </Link>

            <Link
              className="[&:not(:first-child)]:border-t [&:not(:first-child)]:border-t-[border-[#ebecee]] flex items-center h-16 bg-white gap-x-2 px-4 hover:bg-[#e6e8f8]"
              to=""
            >
              <MdOutlinePayment />
              <p>Lịch sử thanh toán</p>
            </Link>
          </div>

          <div className="col-span-8 bg-white rounded p-6">
            <div className="grid gap-4 grid-cols-1">
              {bookings.length > 0 &&
                bookings.map((item) => (
                  <Tag key={item.id} className="p-4 text-sm" color="purple">
                    {console.log(item)}
                    <p>
                      <b>Hóa đơn:</b> {item.id}
                    </p>
                    <p>
                      <b>Sự kiện: </b>
                      {item.eventName}
                    </p>
                    <p>
                      <b>Ngày mua vé:</b> {item.bookingDate}
                    </p>
                    <p>
                      <b>Số lượng:</b> {item.quantity}
                    </p>
                    <p>
                      <b>Tình trạng: </b>
                      {item.status ? "Đã thanh toán" : "Chưa thanh toán"}
                    </p>
                    <p>
                      <b>Tổng tiền: </b>
                      {item.totalPrice}
                    </p>
                  </Tag>
                ))}
            </div>
          </div>
        </div>
      </WrapperContent>
    </div>
  );
};

export default MyBookings;
