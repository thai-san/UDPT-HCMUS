import { useSelector } from "react-redux";
import { Route, Routes } from "react-router-dom";
import ProtectedRoute from "./protectedroute";

import ClientLayout from "../../layouts/ClientLayout/ClientLayout";
import { ROUTE_PATH } from "../../constants/route";
import HomePage from "../../pages/HomePage/HomePage";
import SignUpPage from "../../pages/SignUpPage/SignUpPage";
import SignInPage from "../../pages/SignInPage/SignInPage";
import MyProfilePage from "../../pages/MyProfilePage/MyProfilePage";
import MyBookings from "../../pages/MyProfilePage/MyBookings";
import EnterpriseProfile from "../../pages/EnterpriseProfile/EnterpriseProfile";
import EnterpriseMyEvents from "../../pages/EnterpriseProfile/MyEvents";
import EventDetailPage from "../../pages/EventDetailPage/EventDetailPage";
import CreateEvent from "../../pages/CreateEvent/CreateEvent";

import { userSelector } from "../../store/userslice";

function AppRouter() {
  const user = useSelector(userSelector);

  return (
    <Routes>
      <Route exact path="" element={<ClientLayout />}>
        <Route path={ROUTE_PATH.HOME} element={<HomePage />} />
        <Route path={ROUTE_PATH.EVENT_DETAIL} element={<EventDetailPage />} />

        <Route
          element={<ProtectedRoute isAllowed={!user.token} redirectPath="/" />}
        >
          <Route path={ROUTE_PATH.SIGN_UP} element={<SignUpPage />} />
          <Route path={ROUTE_PATH.SIGN_IN} element={<SignInPage />} />
        </Route>

        <Route
          element={<ProtectedRoute isAllowed={user.token} redirectPath="/" />}
        >
          <Route
            element={
              <ProtectedRoute
                isAllowed={user.role === "CUSTOMER"}
                redirectPath="/"
              />
            }
          >
            <Route path={ROUTE_PATH.MY_PROFILE} element={<MyProfilePage />} />
            <Route path="my-bookings" element={<MyBookings />} />
          </Route>
          <Route
            element={
              <ProtectedRoute
                isAllowed={user.role === "ENTERPRISE"}
                redirectPath="/"
              />
            }
          >
            <Route
              path={ROUTE_PATH.ENTERPRISE_PROFILE}
              element={<EnterpriseProfile />}
            />
            <Route path="enterprise-events" element={<EnterpriseMyEvents />} />
            <Route path="create-event" element={<CreateEvent />} />
          </Route>
          <Route
            element={
              <ProtectedRoute
                isAllowed={user.role !== "ADMIN"}
                redirectPath="/"
              />
            }
          ></Route>
        </Route>
      </Route>
    </Routes>
  );
}

export default AppRouter;
