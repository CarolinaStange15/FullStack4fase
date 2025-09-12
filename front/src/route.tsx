import Home from "./pages/home";
import LayoutAdmin from "./components/layoutAdmin/index";
import { Route, Routes } from "react-router-dom";
import Login from "./pages/login";
import Usuario from "./pages/usuario";
import LayoutLogin from "./components/layoutLogin";
import LayoutUser from "./components/layoutUser";
import UsuarioAdmin from "./pages/usuarioAdmin";




export default function AppRoutes() {
    return (
        <Routes>

            <Route element={<LayoutLogin />}>
                <Route path="/login" element={<Login />} />



            </Route>
            <Route element={<LayoutUser />}>

                <Route path="/" element={<Home />} />
                <Route path="/usuario" element={<Usuario />} />


            </Route>

            <Route element={<LayoutAdmin />} >
                <Route path="/usuarioAdmin" element={<UsuarioAdmin />} />

            </Route>
        </Routes>
    );
}



