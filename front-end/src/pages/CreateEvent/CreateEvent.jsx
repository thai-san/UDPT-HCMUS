import React, { useState } from "react";
import { Button, DatePicker, Form, Input, TimePicker } from "antd";
import dayjs from "dayjs";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";

import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { userSelector } from "../../store/userslice";
import { createEvent } from "../../services";

let today = new Date().toISOString().slice(0, 10);

const CreateEvent = () => {
  const [name, setName] = useState("");
  const [location, setLocation] = useState("");
  const [date, setDate] = useState(today);
  const [startTime, setStartTime] = useState("00:00:00");
  const [endTime, setEndTime] = useState("00:00:00");
  const [ticketPrice, setTicketPrice] = useState("");
  const [ticketQuantity, setTicketQuantity] = useState("");
  const [promotion, setPromotion] = useState("");

  const user = useSelector(userSelector);

  const handleOnClickCreate = () => {
    createEvent(user.token, {
      name,
      location,
      date,
      startTime,
      endTime,
      ticketPrice,
      ticketQuantity,
      promotion,
    })
      .then((res) => {
        if (res.code === 200) toast.success("Đã tạo sự kiện");
      })
      .catch((err) => {
        console.log(err);
        toast.success("Không thể tạo sự kiện");
      });
  };

  return (
    <div className="py-6">
      <WrapperContent>
        <div className="grid grid-cols-12 gap-x-3 pl-40 pr-40">
          <div className="col-span-12 bg-white rounded pt-8 pb-4 pl-4 pr-4">
            <div className="w-[90%] mx-auto">
              <h2 style={{ fontSize: "40px" }}>
                <b>Tạo sự kiện</b>
              </h2>

              <Form layout="vertical" className="mt-10">
                <Form.Item
                  label={<p className="text-base font-semibold">Tên sự kiện</p>}
                >
                  <Input
                    placeholder="Nhập tên sự kiện"
                    className="h-10"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                  />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Địa điểm</p>}
                >
                  <Input
                    placeholder="Nhập địa điểm"
                    className="h-10"
                    value={location}
                    onChange={(e) => setLocation(e.target.value)}
                  />
                </Form.Item>

                <Form.Item
                  label={
                    <p className="text-base font-semibold">Ngày tổ chức</p>
                  }
                >
                  <DatePicker
                    className="h-10"
                    value={dayjs(date, "YYYY-MM-DD")}
                    format="YYYY-MM-DD"
                    style={{ width: "100%" }}
                    onChange={(_, dateStr) => setDate(dateStr)}
                    placeholder="Chọn ngày tổ chức"
                  />
                </Form.Item>

                <Form.Item
                  label={
                    <p className="text-base font-semibold">Thời gian bắt đầu</p>
                  }
                >
                  <TimePicker
                    className="h-10"
                    style={{ width: "100%" }}
                    placeholder="Chọn ngày thời gian bắt đầu"
                    value={dayjs(startTime, "HH:mm:ss")}
                    format="HH:mm:ss"
                    onChange={(time, timeStr) => setStartTime(timeStr)}
                  />
                </Form.Item>

                <Form.Item
                  label={
                    <p className="text-base font-semibold">
                      Thời gian kết thúc
                    </p>
                  }
                >
                  <TimePicker
                    className="h-10"
                    style={{ width: "100%" }}
                    placeholder="Chọn ngày thời gian kết thúc"
                    value={dayjs(startTime, "HH:mm:ss")}
                    format="HH:mm:ss"
                    onChange={(time, timeStr) => setEndTime(timeStr)}
                  />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Giá vé</p>}
                >
                  <Input
                    placeholder="Nhập giá vé"
                    className="h-10"
                    value={ticketPrice}
                    onChange={(e) => setTicketPrice(e.target.value)}
                  />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Số lượng vé</p>}
                >
                  <Input
                    placeholder="Nhập số lượng vé"
                    className="h-10"
                    value={ticketQuantity}
                    onChange={(e) => setTicketQuantity(e.target.value)}
                  />
                </Form.Item>

                <Form.Item
                  label={<p className="text-base font-semibold">Khuyến mãi</p>}
                >
                  <Input
                    placeholder="Nhập khuyến mãi"
                    className="h-10"
                    value={promotion}
                    onChange={(e) => setPromotion(e.target.value)}
                  />
                </Form.Item>
              </Form>
            </div>

            <div className="px-3 border-t border-t-[#ddeaff] pt-4 flex gap-x-2 justify-center">
              <Button
                className="bg-[#011bb6] hover:!bg-[#011bb6] h-10 rounded w-40 max-w-full"
                onClick={handleOnClickCreate}
              >
                <p className="uppercase text-white text-base font-semibold">
                  Tạo sự kiện
                </p>
              </Button>
            </div>
          </div>
        </div>
      </WrapperContent>
    </div>
  );
};

export default CreateEvent;
