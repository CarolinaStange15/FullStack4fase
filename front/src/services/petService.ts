import api from "./api";

export interface Pet{
  id:number;
  nome: string;
  idadeAproximada: string;
  descricao: string;
  contatoAdocao: string;
  status: StatusPet;
   especieid: {
    id: number;
    nome: string;
  };
    especieId?: number;

  ongId: number;
  img: string;
}

export interface PetsRequest {
  id?: number;
  nome: string;
  idadeAproximada: string;
  descricao: string;
  status: StatusPet;
  especieId: number;
  ongId?: number;
}

export interface PetDetalhes {
  id: number;
  nome: string;
  idadeAproximada: string;
  descricao: string;
  especieid: string;
  ong: {
    id: number;
    nome: string;
    email: string;
    telefone: string;
    cidade: string;
    endereco: string;
  };
};


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


export async function buscarTodosPetsDisponiveis(): Promise<Pet[]> {
  const response = await api.get<Pet[]>("/pets/disponiveis");
  return response.data;
  
}

export async function buscarTodosPetsPorOng(): Promise<Pet[]> {
  const response = await api.get<Pet[]>("/pets/meus-pets");
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

export async function buscarPetPorId(id: number | string): Promise<Pet> {
  const response = await api.get<Pet>(`/pets/${id}`);
  return response.data;
}


export async function buscarPetPorIdDetalhes(id: number | string): Promise<PetDetalhes> {
  const response = await api.get<PetDetalhes>(`/pets/${id}/conhecer`);
  return response.data;
}

export async function deletarPet(id: number | string): Promise<void> {
  await api.delete(`/pets/${id}`);
}


export async function editarPet(id: number | string, pet: PetsRequest): Promise<PetsResponse> {
  const response = await api.put<PetsResponse>(`/pets/${id}`, pet);
  return response.data;
}







export default buscarTodosPetsDisponiveis;


