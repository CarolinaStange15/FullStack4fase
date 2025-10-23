

export interface PetsRequest {
  nome: string;
  idadeAproximada: string;
  descricao: string;
  contatoAdocao: string;
  status: StatusPet;
  especieId: number;
}

const enum StatusPet {
  DISPONIVEL = "DISPONIVEL",
  ADOTADO = "ADOTADO",
}

interface PetsResponse {
  nome: string;
  descricao: string;
}