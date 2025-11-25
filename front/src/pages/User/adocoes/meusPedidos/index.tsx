import { useEffect, useState } from "react";
import { buscarMeusPedidos, type PedidoAdocaoResponse } from "../../../../services/adocaoService";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import type { RootState } from "../../../../redux/store";

export default function MeusPedidos() {
  const [pedidos, setPedidos] = useState<PedidoAdocaoResponse[]>([]);
const navigate = useNavigate();
  const token = useSelector((state: RootState) => state.auth.token);

 
  useEffect(() => {
    if (!token) {
      navigate("/"); 
    }
  }, [token, navigate]);


  useEffect(() => {
    buscarMeusPedidos()
      .then((res) => setPedidos(res))
      .catch((err) => console.error(err));
  }, []);

  if (pedidos.length === 0) {
    return (
      <main className="container my-5 text-center">
        <h3 className="fw-bold">Meus Pedidos de Adoção</h3>
        <p className="text-muted mt-3">
          Você ainda não fez nenhum pedido de adoção.
        </p>
      </main>
    );
  }

  return (
    <main className="container my-5">
      <h3 className="mb-4 fw-bold">Meus Pedidos de Adoção</h3>

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

                <p className="text-muted">
                  <strong>ONG:</strong> {p.petResumo.ongNome}
                </p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </main>
  );
}
