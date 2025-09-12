
import Sidebar from "../sidebar/index";
import Header from "../header/index";
import Footer from "../footer/index";
import { Outlet } from "react-router-dom";

export default function LayoutAdmin(){
    return(
        <>
         
                <Header></Header>
                <div className="d-flex">
                <Sidebar></Sidebar>
                <div className="flex-grow-1 p-4">
                <Outlet/>
                </div>
                </div>
                <Footer></Footer>
                </>
    );
    }


