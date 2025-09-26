import { Link } from "react-router-dom";

type CardsProps = {
  id: number;
  title: string;
  text: string;
};

export default function Cards({ id, title, text }: CardsProps) {
  return (
    <div className="col-md-4">
      <div className="card shadow-sm h-100">
        <div className="card-body d-flex flex-column">
          <h5 className="card-title fw-bold">{title}</h5>
          <p className="card-text text-muted">{text}</p>
          <Link to={`/pets/${id}`} className="btn btn-outline-primary mt-auto">
            Conhecer
          </Link>
        </div>
      </div>
    </div>
  );
}
