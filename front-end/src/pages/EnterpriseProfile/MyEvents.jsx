import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { FaUser } from "react-icons/fa";
import { MdOutlinePayment } from "react-icons/md";
import { Button, Form, Input } from "antd";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";

import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { getMyEvents } from "../../services";
import { userSelector } from "../../store/userslice";
import EventItem from "../../components/EventItem/EventItem";

const EnterpriseMyEvents = () => {
  const [events, setEvents] = useState("");

  const user = useSelector(userSelector);

  useEffect(() => {
    getMyEvents(user.token)
      .then((res) => {
        if (res.code === 200) setEvents(res.result);
      })
      .catch((err) => console.log(err));
  }, [user]);

  return (
    <div className="py-6">
      <WrapperContent>
        <div className="grid grid-cols-12 gap-x-3">
          <div className="col-span-4 border border-[#ebecee] rounded self-start">
            <Link
              to="/enterprise-profile"
              className="[&:not(:first-child)]:border-t [&:not(:first-child)]:border-t-[border-[#ebecee]] flex items-center h-16 bg-white gap-x-2 px-4 hover:bg-[#e6e8f8]"
            >
              <FaUser />
              <p>Cài đặt thông tin doanh nghiệp</p>
            </Link>

            <Link
              to=""
              className="[&:not(:first-child)]:border-t [&:not(:first-child)]:border-t-[border-[#ebecee]] flex items-center h-16 bg-white gap-x-2 px-4 hover:bg-[#e6e8f8]"
            >
              <MdOutlinePayment />
              <p>Sự kiện của tôi</p>
            </Link>
          </div>

          <div className="col-span-8 bg-white rounded p-6">
            <div className="grid gap-4 grid-cols-3">
              {events &&
                events.map((item) => <EventItem key={item.id} data={item} />)}
            </div>
          </div>
        </div>
      </WrapperContent>
    </div>
  );
};

export default EnterpriseMyEvents;
