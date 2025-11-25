import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import type { RootState } from "../../../redux/store";

type CardsProps = {
  id: number;
  title: string;
  text: string;
   especie: string;
};


export default function cardsUserPet({ id, title, text, especie }: CardsProps) {
const token = useSelector((state: RootState) => state.auth.token);
  const navigate = useNavigate();

  function handleConhecer() {
    if (!token) {
      alert("Você precisa estar logado para conhecer mais detalhes de um pet.");
      navigate("/"); // login
      return;
    }

    

    navigate(`/pets/${id}/conhecer`);
  }


  return (
    <div className="col-md-4">
      <div className="card shadow-sm h-100">
        <div className="card-body d-flex flex-column">
          <h5 className="card-title fw-bold">{title}</h5>
          <p className="card-text text-muted">{text}</p>
          <p className="text-secondary mb-1"><strong>Espécie:</strong> {especie}</p>

        <button
            onClick={handleConhecer}
            className="btn btn-outline-primary mt-auto"
          >
            Conhecer
          </button>
        </div>
      </div>
    </div>
  );
}
