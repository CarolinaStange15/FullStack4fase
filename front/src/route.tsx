
import LayoutAdmin from "./components/layoutAdmin/index";
import { Route, Routes } from "react-router-dom";
import Login from "./pages/User/login";
import Usuario from "./pages/User/usuario";
import LayoutLogin from "./components/layoutLogin";
import LayoutUser from "./components/layoutUser";
import UsuarioAdmin from "./pages/Admin/usuarioAdmin";
import PetAdmin from "./pages/Admin/petAdmin";
import Home from "./pages/User/home";
import EditaUsuario from "./pages/User/editaUsuario";
import HomeAdmin from "./pages/Admin/homeAdmin";




export default function AppRoutes() {
    return (
        <Routes>

            <Route element={<LayoutLogin />}>
                <Route path="/login" element={<Login />} />



            </Route>
            <Route element={<LayoutUser />}>

                <Route path="/" element={<Home />} />
                <Route path="/usuario" element={<Usuario />} />
                <Route path="/editarUsuario" element={<EditaUsuario />} />
                
                //inativa usuário
                //pet específico
                //pesquisa pet por especie
                //cadastrar pet
                //editar pet


            </Route>

            <Route element={<LayoutAdmin />} >
                <Route path="/usuarioAdmin" element={<UsuarioAdmin />} />
                <Route path="/petAdmin" element={<PetAdmin />} />
                <Route path="/homeAdmin" element={<HomeAdmin />} />


            </Route>
        </Routes>
    );
}



