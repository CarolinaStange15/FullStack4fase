export default function Cards({ title, text, img, link }) {
    return (
      <div className="col-md-4">
        <div className="card shadow-sm h-100">
          <img src={img} className="card-img-top" alt={title} />
          <div className="card-body d-flex flex-column">
            <h5 className="card-title fw-bold">{title}</h5>
            <p className="card-text text-muted">{text}</p>
            {link && (
              <a href={link} className="btn btn-outline-primary mt-auto">
                Acessar
              </a>
            )}
          </div>
        </div>
      </div>
    );
  }