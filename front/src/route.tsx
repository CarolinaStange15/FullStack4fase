import Footer from "./components/footer/index";
import Header from "./components/header/index";
import Home from "./pages/home/home";
import Sidebar from "./components/sidebar/index";

        
        
        export default function AppRoutes(){
            return(
                <>
                <Header></Header>
                <div className="d-flex">
                <Sidebar></Sidebar>
                <div className="flex-grow-1 p-4">
                <Home></Home>
                </div>
</div>
                <Footer></Footer>

                </>
            );
        }



