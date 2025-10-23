import Usuario from "../pages/User/usuario";
import api from "./api";

export interface Usuario{
    id: number;
    nome: string;
    cpf: string;
    email: string;

}

export interface UsuarioRequest {
    nome: string;
    cpf: string;
    email: string;
    senha: string;
    role: string;
}

export interface UsuarioResponse {
    id: number;
    nome: string;
    email: string;
    role: string;
}




export async function buscarTodosUsuarios() :Promise<Usuario[]> {
    const response = await api.get<Usuario[]>("/usuarios");
        return response.data;

    
}

export async function cadastrarUsuario(
  usuario: UsuarioRequest
): Promise<UsuarioResponse> {
  const response = await api.post<UsuarioResponse>("/usuarios", usuario);
  return response.data;
}

export default buscarTodosUsuarios;