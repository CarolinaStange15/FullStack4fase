
import LayoutAdmin from "./components/layoutAdmin/index";
import { Route, Routes } from "react-router-dom";
import LayoutLogin from "./components/layoutLogin/index";
import LayoutUser from "./components/layoutUser/index";
import Login from "./pages/User/login";
import Usuario from "./pages/User/usuario";
import EditaUsuario from "./pages/User/editaUsuario";
import UsuarioAdmin from "./pages/Admin/cadastrarUsuario";
import PetAdmin from "./pages/Admin/consultaPets";
import HomeAdmin from "./pages/Admin/home";
import DetalhesPet from "./pages/Admin/consultaPets";
import EditarPet from "./pages/Admin/editaPet";
import CadastrarUsuario from "./pages/Admin/cadastrarUsuario";
import CadastrarPet from "./pages/Admin/cadastrarPets";





export default function AppRoutes() {
    return (
        <Routes>

            <Route element={<LayoutLogin />}>
                <Route path="/" element={<Login />} />



            </Route>
            <Route element={<LayoutUser />}>

                {/* <Route path="/" element={<Home />} /> */}
                <Route path="/usuario" element={<Usuario />} />
                <Route path="/editarUsuario" element={<EditaUsuario />} />
                
                //inativa usuário
                //pesquisa pet por especie



            </Route>

            <Route element={<LayoutAdmin />} >
                <Route path="/usuarioAdmin" element={<UsuarioAdmin />} />
                <Route path="/petAdmin" element={<PetAdmin />} />
                <Route path="/homeAdmin" element={<HomeAdmin />} />
                <Route path="/pets" element={<CadastrarPet />} />
                <Route path="/pets/:id" element={<DetalhesPet />} />
                <Route path="/pets/:id/editar" element={<EditarPet />} />
                <Route path="/usuarios" element={<CadastrarUsuario />} />
                <Route path="/usuariosConsultar" element={<Usuario />} />






            </Route>
        </Routes>
    );
}



