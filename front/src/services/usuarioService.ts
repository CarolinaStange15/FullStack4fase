import api from "./api";

export interface Usuario{
    id: number;
    nome: string;
    cpf: string;
    email: string;
    ongId: number;

}

export interface UsuarioRequest {
    nome: string;
    cpf: string;
    email: string;
    senha: string;
    role: string;
    telefone: string;
}

export interface RecuperarSenhaDto {
  email: string;
}

export interface RegistrarNovaSenhaDto {
  email: string;
  senha: string;
  token: string;
}

export interface UsuarioResponse {
    ongId: number;
    id: number;
    nome: string;
    email: string;
    role: string;
    telefone: string;
    cpf:string;
}




export async function buscarTodosUsuarios() :Promise<Usuario[]> {
    const response = await api.get<Usuario[]>("/usuarios");
        return response.data;

    
}

export async function cadastrarUsuario( usuario: UsuarioRequest ): Promise<UsuarioResponse> {
  const response = await api.post<UsuarioResponse>("/usuarios", usuario);
  return response.data; }

 export async function recuperarSenha(data: RecuperarSenhaDto) {
    const response = await api.post("/auth/esquecisenha", data);
    return response.data;
  }

  // 2) Registrar nova senha
  export async function registrarNovaSenha(data: RegistrarNovaSenhaDto) {
    const response = await api.post("/auth/registrarnovasenha", data);
    return response.data;
  }


  
export async function editarUsuario(usuario: UsuarioRequest): Promise<UsuarioResponse> {
  const response = await api.put<UsuarioResponse>("/usuarios/editar", usuario);
  return response.data;
}

export async function buscarUsuarioLogado(): Promise<UsuarioResponse> {
  const response = await api.get<UsuarioResponse>("/usuarios/me");
  return response.data;
}


export async function vincularOng(ongId: number): Promise<UsuarioResponse> {
  const response = await api.post<UsuarioResponse>(`/usuarios/vincularOng?ongId=${ongId}`);
  return response.data;
}


export default buscarTodosUsuarios;