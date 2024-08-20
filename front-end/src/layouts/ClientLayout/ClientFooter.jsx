import React from "react";
import WrapperContent from "../../components/WrapperContent/WrapperContent";
import { Link } from "react-router-dom";

const ClientFooter = () => {
  return (
    <>
      <div className="bg-[#e8ecff] py-8">
        <WrapperContent>
          <div className="grid grid-cols-4 gap-x-6">
            <div>
              <img
                src="/images/logo-colored.png"
                alt="Bottom logo"
                className="block h-20"
              />

              <img
                src="/images/logo-bct.png"
                alt="BCT logo"
                className="block h-14 mt-8"
              />
            </div>

            <div className="text-sm">
              <h3 className="font-semibold mb-3">TRUNG TÂM HỖ TRỢ</h3>

              <ul>
                <li className="mb-[14px]">
                  <span>Hotline: </span>
                  <Link to="tel:1900638337" target="_blank">
                    1900 63 83 37 (1.000đ/phút)
                  </Link>
                </li>
                <li className="mb-[14px]">
                  (09:00 - 17:00 từ thứ hai đến thứ sáu)
                </li>
                <li className="mb-[14px]">
                  <span>Email: </span>
                  <Link to="mailto:support@actiup.net" target="_blank">
                    support@actiup.net
                  </Link>
                </li>
              </ul>

              <h3 className="font-semibold mt-8 mb-3">CHỨNG NHẬN BỞI</h3>

              <img
                src="/images/handle_cert.png"
                alt="Cert"
                className="block h-16"
              />
            </div>

            <div className="text-sm">
              <h3 className="font-semibold mb-3">VỀ ACTIUP</h3>

              <ul>
                <li className="mb-[14px]">
                  <Link>Về chúng tôi</Link>
                </li>
                <li className="mb-[14px]">
                  <Link>Dịch vụ cung cấp</Link>
                </li>
                <li className="mb-[14px]">
                  <Link>Quy chế hoạt động</Link>
                </li>
                <li className="mb-[14px]">
                  <Link>Điều khoản sử dụng</Link>
                </li>
                <li className="mb-[14px]">
                  <Link>Chính sách bảo mật</Link>
                </li>
                <li className="mb-[14px]">
                  <Link>Chính Sách Bảo Mật Thông Tin Cá Nhân</Link>
                </li>
                <li className="mb-[14px]">
                  <Link>Quy trình giao dịch</Link>
                </li>
                <li className="mb-[14px]">
                  <Link>Quy trình khiếu nại</Link>
                </li>
              </ul>
            </div>

            <div className="text-sm">
              <h3 className="font-semibold mb-3">PHƯƠNG THỨC THANH TOÁN</h3>

              <ul>
                <li className="flex items-center gap-x-2 mb-[14px]">
                  <img
                    src="/images/credit.png"
                    alt="Icon"
                    className="w-6 h-6 object-contain"
                  />
                  <p>Thẻ tín dụng</p>
                </li>

                <li className="flex items-center gap-x-2 mb-[14px]">
                  <img
                    src="/images/atm.png"
                    alt="Icon"
                    className="w-6 h-6 object-contain"
                  />
                  <p>Thẻ ATM</p>
                </li>

                <li className="flex items-center gap-x-2 mb-[14px]">
                  <img
                    src="/images/bank.png"
                    alt="Icon"
                    className="w-6 h-6 object-contain"
                  />
                  <p>Chuyển khoản ngân hàng</p>
                </li>

                <li className="flex items-center gap-x-2 mb-[14px]">
                  <img
                    src="/images/ewallet.png"
                    alt="Icon"
                    className="w-6 h-6 object-contain"
                  />
                  <p>Thanh toán ví điện tử</p>
                </li>
              </ul>
            </div>
          </div>
        </WrapperContent>
      </div>

      <div className="bg-[#4056dd]">
        <WrapperContent className="flex items-center justify-center px-3 h-12">
          <p className="text-[#96b6e8]">
            © 2023 All rights reserved. Techhaus Vietnam.
          </p>
        </WrapperContent>
      </div>
    </>
  );
};

export default ClientFooter;
