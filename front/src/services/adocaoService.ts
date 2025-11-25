import api from "./api";


export interface PedidoAdocaoResponse {
  id: number;
  status: string; 
  dataCadastro: string;

  petResumo: {
    id: number;
    nome: string;
    ongNome: string;
  };

  usuarioResumo: {
    id: number;
    nome: string;
    email: string;
    telefone: string;

  }
}


export async function solicitarAdocao(petId: number): Promise<PedidoAdocaoResponse> {
  const response = await api.post(`/adocoes/${petId}`, {});
  return response.data;
}

export async function buscarMeusPedidos(): Promise<PedidoAdocaoResponse[]> {
  const response = await api.get<PedidoAdocaoResponse[]>(`/adocoes/meus-pedidos`);
  return response.data;
}

export async function buscarPendentesPorOng(): Promise<PedidoAdocaoResponse[]> {
  const response = await api.get("/adocoes/pendentes");
  return response.data;
}

export async function buscarAprovadosPorOng(): Promise<PedidoAdocaoResponse[]> {
  const response = await api.get("/adocoes/aprovadas");
  return response.data;
}

export async function buscarReprovadosPorOng(): Promise<PedidoAdocaoResponse[]> {
  const response = await api.get("/adocoes/reprovadas");
  return response.data;
}

export async function aprovarAdocao(adocaoId: number): Promise<PedidoAdocaoResponse> {
  const response = await api.put<PedidoAdocaoResponse>(`/adocoes/${adocaoId}/aprovar`);
  return response.data;
}
export async function reprovarAdocao(adocaoId: number): Promise<PedidoAdocaoResponse> {
  const response = await api.put<PedidoAdocaoResponse>(`/adocoes/${adocaoId}/reprovar`);
  return response.data;
}

export async function cancelarRespostaAdocao(adocaoId: number): Promise<PedidoAdocaoResponse> {
  const response = await api.put<PedidoAdocaoResponse>(`/adocoes/${adocaoId}/cancelarResposta`);
  return response.data;
}


export default buscarMeusPedidos;
