import Cards from "../../components/body/cards/cards";

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
          img="https://tse1.mm.bing.net/th/id/OIP._IEkT1iK_ZDl8brpXB3nFAHaJg?cb=thfvnext&w=652&h=837&rs=1&pid=ImgDetMain&o=7&rm=3/400x200"
          link="#"
        />
        <Cards
          title="Levi"
          text="Ele é bem maneiro"
          img="https://tse1.explicit.bing.net/th/id/OIP.-0LeG9XBntAZ4gBoygP0MAHaHa?cb=thfvnext&rs=1&pid=ImgDetMain&o=7&rm=3"
          link="#"
        />
        <Cards
          title="Roberto"
          text="Ele é bem educado..."
          img="https://i.pinimg.com/736x/6c/64/fa/6c64fa1e5b68cd63733e475c6d5735ff.jpg"
          link="#"
        />
      </section>
    </main>
  );
}