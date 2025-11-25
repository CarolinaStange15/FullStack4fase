
import { Route, Routes } from "react-router-dom";
import LayoutLogin from "./components/layouts/layoutLogin/index";
import Login from "./pages/User/login";
import EditaUsuario from "./pages/User/editaUsuario";
import UsuarioAdmin from "./pages/User/cadastrarUsuario";
import PetAdmin from "./pages/Admin/consultaPets";
import DetalhesPet from "./pages/Admin/consultaPets";
import EditarPet from "./pages/Admin/editaPet";
import CadastrarUsuario from "./pages/User/cadastrarUsuario";
import CadastrarPet from "./pages/Admin/cadastrarPets";
import GridUsuarios from "./pages/User/GridUsuarios";
import Home from "./pages/User/home";
import DetalhesPetOng from "./pages/User/consultaPetDetalhado";
import MeusPedidos from "./pages/User/adocoes/meusPedidos";
import LayoutUser from "./components/layouts/layoutUser";
import LayoutAdmin from "./components/layouts/layoutAdmin";
import LayoutMain from "./components/layouts/layoutMain";
import MeusPets from "./pages/Admin/consultaMeusPets";
import AdocoesPendentes from "./pages/Admin/adocoes/pendentes";
import AdocoesAprovados from "./pages/Admin/adocoes/aprovados";
import AdocoesReprovados from "./pages/Admin/adocoes/reprovados";
import ResetPassword from "./pages/User/recuperarSenha";
import ResetarSenha from "./pages/User/resetarSenha";
import { recuperarSenha } from "./services/usuarioService";
import RecuperarSenha from "./pages/User/recuperarSenha";
import CadastrarOng from "./pages/Admin/cadastrarOng";
import EditarOng from "./pages/Admin/editarOng";





export default function AppRoutes() {
    return (
       <Routes>
  {/* Rotas públicas (login, cadastro) */}
  <Route element={<LayoutLogin />}>
    <Route path="/" element={<Login />} />
    <Route path="/usuarioCadastro" element={<CadastrarUsuario />} />
    <Route path="/recuperarSenha"element={<RecuperarSenha/>} />
    <Route path="/registrarNovaSenha" element={< ResetarSenha />} />
  </Route>,

  {/* Rotas privadas */}
  <Route element={<LayoutMain />}>
    <Route path="/home" element={<Home />} />
    <Route path="/pets/:id/conhecer" element={<DetalhesPetOng />} />
    <Route path="/adocoes/meus-pedidos" element={<MeusPedidos />} />
    <Route path="/usuario/editar" element= {<EditaUsuario />} />
    <Route path="/ong/cadastrar" element= {<CadastrarOng />} />

    {/* Rotas de admin/ONG */}
    <Route path="/usuarioAdmin" element={<UsuarioAdmin />} />
    <Route path="/petAdmin" element={<PetAdmin />} />
    <Route path="/pets/cadastrar" element={<CadastrarPet />} />
    <Route path="/pets/consultar" element={<MeusPets/>} />
    <Route path="/pets/:id" element={<DetalhesPet />} />
    <Route path="/pets/:id/editar" element={<EditarPet />} />
    <Route path="/adocoes/pendentes" element={<AdocoesPendentes />} />
    <Route path="/adocoes/aprovadas" element={<AdocoesAprovados />} />
    <Route path="/adocoes/reprovadas" element={<AdocoesReprovados />} />
    <Route path="/ongs/minhaOng" element={<EditarOng />} />

    <Route path="/usuariosConsultar" element={<GridUsuarios />} />
  </Route>
</Routes>


       
    );
}



