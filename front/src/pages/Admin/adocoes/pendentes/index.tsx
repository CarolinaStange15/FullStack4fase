import { useEffect, useState } from "react";
import { aprovarAdocao, buscarMeusPedidos, buscarPendentesPorOng, reprovarAdocao, type PedidoAdocaoResponse } from "../../../../services/adocaoService";
import { useNavigate } from "react-router-dom";
import type { RootState } from "../../../../redux/store";
import { useSelector } from "react-redux";

export default function AdocoesPendentes() {
  const [pedidos, setPedidos] = useState<PedidoAdocaoResponse[]>([]);
  const navigate = useNavigate(); 
  const token = useSelector((state: RootState) => state.auth.token);

 
  useEffect(() => {
    if (!token) {
      navigate("/"); 
    }
  }, [token, navigate]);



  useEffect(() => {
    buscarPendentesPorOng()
      .then((res) => setPedidos(res))
      .catch((err) => console.error(err));
  }, []);

  if (pedidos.length === 0) {
    return (
      <main className="container my-5 text-center">
        <h3 className="fw-bold">Pedidos de adoção pendentes</h3>
        <p className="text-muted mt-3">
          Sua ong não possui nenhum pedido de adoção pendente.
        </p>
      </main>
    );
  }

  return (
    <main className="container my-5">
      <h3 className="mb-4 fw-bold">Pedidos de adoção pendentes</h3>

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

             <div className="d-flex gap-2 mt-3">

  <button
    className="btn btn-primary flex-fill fw-semibold"
    onClick={async () => {
      if (!p) return;

      const confirmar = window.confirm(
        "Deseja mesmo aprovar essa adoção?"
      );
      if (!confirmar) return;

      try {
        await aprovarAdocao(p.id);
        alert("Pedido de adoção aprovado com sucesso!");
        navigate("/home")
      } catch (err) {
        alert("Erro ao aprovar adoção!");
        console.error(err);
      }
    }}
  >
    Aprovar
  </button>

  <button
    className="btn btn-primary flex-fill fw-semibold"
    onClick={async () => {
      if (!p) return;

      const confirmar = window.confirm(
        "Deseja mesmo reprovar essa adoção?"
      );
      if (!confirmar) return;

      try {
        await reprovarAdocao(p.id);
        alert("Pedido de adoção reprovado com sucesso!");
      } catch (err) {
        alert("Erro ao reprovar adoção!");
        console.error(err);
      }
    }}
  >
    Reprovar
  </button>

</div>


                
              </div>
            </div>
          </div>
        ))}
      </div>
    </main>
  );
}
