import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import type { RootState } from "../../../redux/store";
import { StatusPet } from "../../../services/petService";

type CardsProps = {
  id: number;
  title: string;
  text: string;
  statusPet: string;
};


export default function CardsAdminPet({ id, title, text , statusPet}: CardsProps) {
const token = useSelector((state: RootState) => state.auth.token);
  const navigate = useNavigate();

  function handleConhecer() {
    if (!token) {
      alert("Você precisa estar logado para editar um pet.");
      navigate("/"); // login
      return;
    }

    

            navigate(`/pets/${id}`);
  }


  return (
    <div className="col-md-4">
      <div className="card shadow-sm h-100">
        <div className="card-body d-flex flex-column">
          <h5 className="card-title fw-bold">{title}</h5>
          <p className="card-text text-muted">{text}</p>
<p className="card-text fw-bold">
  Status: {statusPet === "ADOTADO" ? "Adotado" : "Disponível"}
</p>

        <button
            onClick={handleConhecer}
            className="btn btn-outline-primary mt-auto"
          >
            Visualizar
          </button>
        </div>
      </div>
    </div>
  );
}
