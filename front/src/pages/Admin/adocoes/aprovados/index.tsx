import { useEffect, useState } from "react";
import { buscarAprovadosPorOng, buscarMeusPedidos, buscarPendentesPorOng, cancelarRespostaAdocao, type PedidoAdocaoResponse } from "../../../../services/adocaoService";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import type { RootState } from "../../../../redux/store";

export default function AdocoesAprovados() {
  const [pedidos, setPedidos] = useState<PedidoAdocaoResponse[]>([]);
 const navigate = useNavigate();
  const token = useSelector((state: RootState) => state.auth.token);

 
  useEffect(() => {
    if (!token) {
      navigate("/"); 
    }
  }, [token, navigate]);


  useEffect(() => {
    buscarAprovadosPorOng()
      .then((res) => setPedidos(res))
      .catch((err) => console.error(err));
  }, []);

  if (pedidos.length === 0) {
    return (
      <main className="container my-5 text-center">
        <h3 className="fw-bold">Pedidos de adoção aprovados</h3>
        <p className="text-muted mt-3">
          Sua ong não possui nenhum pedido de adoção aprovado.
        </p>
      </main>
    );
  }

  return (
    <main className="container my-5">
      <h3 className="mb-4 fw-bold">Pedidos de adoção aprovados</h3>

      <div className="row g-4">
        {pedidos.map((p) => (
          <div className="col-md-4" key={p.id}>
            <div className="card shadow-sm h-100">
              <div className="card-body">

                <h5 className="card-title fw-bold text-primary">
                  {p.petResumo.nome}
                </h5>

                <p className="mt-2">
                  <strong>Status:</strong> {p.status}
                </p>
                <p>
                  <strong>Data:</strong>{" "}
                  {new Date(p.dataCadastro).toLocaleString()}
                </p>

                <hr />

                <p className="mt-2">
                  <strong>Usuário:</strong> {p.usuarioResumo.nome}
                </p>
                <p className="mt-2">
                  <strong>Telefone:</strong> {p.usuarioResumo.telefone}
                </p>
                <p className="mt-2">
                  <strong>Email:</strong> {p.usuarioResumo.email}
                </p>
                  <button
                    className="btn btn-primary flex-fill fw-semibold"
                    onClick={async () => {
                      if (!p) return;
                
                      
                
                      try {
                        await cancelarRespostaAdocao(p.id);
                        alert("Resposta do pedido de adoção apagada com sucesso!");
                      } catch (err) {
                        alert("Erro ao cancelar resposta de pedido de adoção");
                        console.error(err);
                      }
                    }}
                  >
                    Cancelar
                  </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </main>
  );
}
