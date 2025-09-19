
import LayoutAdmin from "./components/layoutAdmin/index";
import { Route, Routes } from "react-router-dom";
import LayoutLogin from "./components/layoutLogin/index";
import Login from "./pages/user/login/index";
import LayoutUser from "./components/layoutUser/index";
import Home from "./pages/user/home/index";
import Usuario from "./pages/user/usuario/index";
import EditaUsuario from "./pages/user/editaUsuario/index";
import UsuarioAdmin from "./pages/admin/usuarioAdmin/index";
import HomeAdmin from "./pages/admin/homeAdmin/index";
import PetAdmin from "./pages/admin/petAdmin/index";




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



