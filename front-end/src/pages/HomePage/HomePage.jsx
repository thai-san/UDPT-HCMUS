import React, { useEffect, useState } from "react";
import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { Carousel } from "antd";
import EventItem from "../../components/EventItem/EventItem";
import { getListEvents } from "../../services";

const HomePage = () => {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    getListEvents()
      .then((res) => {
        if (res.code === 200) setEvents(res.result);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div className="py-4">
      <WrapperContent>
        {/* slider */}
        <Carousel autoplay draggable>
          <div className="pt-[35%] relative">
            <img
              src="/images/banner-1.png"
              alt="Banner 1"
              className="absolute top-0 right-0 bottom-0 left-0 w-full h-full block object-cover"
            />
          </div>

          <div className="pt-[35%] relative">
            <img
              src="/images/banner-2.jpg"
              alt="Banner 2"
              className="absolute top-0 right-0 bottom-0 left-0 w-full h-full block object-cover"
            />
          </div>

          <div className="pt-[35%] relative">
            <img
              src="/images/banner-3.jpg"
              alt="Banner 3"
              className="absolute top-0 right-0 bottom-0 left-0 w-full h-full block object-cover"
            />
          </div>

          <div className="pt-[35%] relative">
            <img
              src="/images/banner-4.jpg"
              alt="Banner 4"
              className="absolute top-0 right-0 bottom-0 left-0 w-full h-full block object-cover"
            />
          </div>
        </Carousel>

        <div className="bg-white rounded p-4 my-6">
          <h2 className="text-2xl font-semibold uppercase my-4">
            Sự kiện nổi bật
          </h2>

          <div className="grid gap-4 grid-cols-3">
            {events &&
              events.map((item) => <EventItem key={item.id} data={item} />)}
          </div>
        </div>
      </WrapperContent>
    </div>
  );
};

export default HomePage;
