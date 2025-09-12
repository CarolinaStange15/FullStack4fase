import { Outlet } from "react-router-dom";

export default  function LayoutLogin(){
     return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-dark text-light">
      <div
        className="card shadow-lg p-4 border-0"
        style={{ maxWidth: "420px", width: "100%", backgroundColor: "#1e1e1e" }}
      >
        <div className="text-center mb-4">
          <img
            src="/img/logo.png"
            alt="Logo"
            className="mb-3"
            style={{ width: "100px", height: "100px" }}
          />
          <h3 className="fw-bold text-info">Bem-vindo</h3>
          <p className="text-secondary">Faça login para continuar</p>
        </div>

        <Outlet />
      </div>
    </div>
  );
}