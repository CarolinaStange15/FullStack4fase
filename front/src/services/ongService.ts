import api from "./api";


export interface OngResponseDto{
    cidade: string;
    endereco: string;
    cnpj: string;
    telefone: string;
    email: string;
    id: number;
    nome:string;
    descricao: string;
    status:string;
}

export interface OngRequestDto{
id:number;
nome: string;
email: string;
descricao: string;
cnpj: string;
telefone: string;
endereco: string;
cidade: string;

}

export async function criarOng(ong: OngRequestDto): Promise<OngResponseDto>{
  const response = await api.post<OngResponseDto>("/ongs/cadastrar", ong);
  return response.data;

} 

export async function atualizarMinhaOng(data: OngRequestDto): Promise<OngResponseDto> {
  const response = await api.put<OngResponseDto>("/ongs/minhaOng", data);
  return response.data;
}

export async function buscarOngPorId(id: number): Promise<OngResponseDto> {
  const response = await api.get<OngResponseDto>(`/ongs/${id}`);
  return response.data;
}

  export default criarOng;