import React from "react";
import { FaCalendarCheck, FaRegHeart } from "react-icons/fa";
import { FiMapPin } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

import { userSelector } from "../../store/userslice";

const EventItem = ({ data }) => {
  const navigate = useNavigate();

  const user = useSelector(userSelector);

  const onNavigateToEventDetail = () => {
    navigate(`/event/${data.id}`);
  };

  return (
    <div
      className="border border-[#d1d5e8] rounded-md cursor-pointer"
      onClick={onNavigateToEventDetail}
    >
      <div
        className="pt-[45%] bg-no-repeat bg-cover bg-center rounded-t relative"
        style={{
          backgroundImage: `url(https://pix.raceez.com/2024/06/03/Banner_1440_x_600.jpg)`,
        }}
      ></div>

      <div className="p-4">
        <p className="line-clamp-2 uppercase font-semibold text-[#011bb6]">
          {data.name}
        </p>

        <div className="flex items-center text-xs text-[#626579] gap-x-2 mt-2">
          <FaCalendarCheck className="text-sm" />
          <p
            style={{
              display: "flex",
              justifyContent: "space-between",
              width: "100%",
            }}
          >
            <span>{data.date}</span>
            <span>
              {data.startTime} - {data.endTime}
            </span>
          </p>
        </div>

        <div className="flex items-center text-xs text-[#626579] gap-x-2 mt-2">
          <FiMapPin className="text-sm" />
          <p className="flex-1">{data.location}</p>
        </div>

        <div className="flex items-center justify-between mt-3">
          <p className="text-sm text-[#f1600d]">
            <span className="font-semibold">${data.ticketPrice}</span>
            {data.promotion ? (
              <span className="font-black ml-6 text-lg">
                -{data.promotion * 100}%
              </span>
            ) : (
              <></>
            )}
          </p>

          {(user.role === "CUSTOMER" || !user.role) && (
            <button className="bg-[#002876] h-[30px] rounded-[10px] text-white font-semibold text-xs px-6">
              Mua vé
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default EventItem;
