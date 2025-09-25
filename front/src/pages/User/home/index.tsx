import Cards from "../../../components/body/cards/cards";


export default function Home() {
  return (
    <main className="container my-5">
      <div className="text-center">
        <h1 className="display-4 fw-bold text-white bg-dark p-3 rounded">
          Adoção
        </h1>
        <p className="lead text-muted mt-3">
          Pets disponíveis para Adoção
        </p>
        
      </div>

      <section className="row mt-5 g-4">
        <Cards
          title="Fuleco"
          text="Um gato estressadinho, mas ele é legal."
          link="#"
        />
        <Cards
          title="Levi"
          text="Ele é bem maneiro"
          link="#"
        />
        <Cards
          title="Roberto"
          text="Ele é bem educado..."
          link="#"
        />
      </section>
    </main>
  );
}