import api from "./api";

export interface Pet{
  id:number;
  idadeAproximada: string;
  descricao: string;
  contatoAdocao: string;
  status: StatusPet;
  especieId: number;
  ongId: number;
}

export interface PetsRequest {
  nome: string;
  idadeAproximada: string;
  descricao: string;
  contatoAdocao: string;
  status: StatusPet;
  especieId: number;
  ongId: number;
}

export const enum StatusPet {
  DISPONIVEL = "DISPONIVEL",
  ADOTADO = "ADOTADO",
}

export interface PetsResponse {
  nome: string;
  descricao: string;
}

export interface EspecieResponse {
  id: number;
  nome: string;
}


export async function buscarTodosPets(): Promise<Pet[]> {
  const response = await api.get<Pet[]>("/pets");
  return response.data;
  
}

export async function cadastrarPet(pet: PetsRequest) : Promise<PetsResponse> {
  const response = await api.post<PetsResponse>("/pets", pet)
  return response.data;
  
}

export async function buscarEspecies(): Promise<EspecieResponse[]> {
  const response = await api.get<EspecieResponse[]>("/especies");
  return response.data;
}

export default buscarTodosPets;


