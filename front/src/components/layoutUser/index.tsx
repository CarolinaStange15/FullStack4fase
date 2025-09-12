import { Outlet } from "react-router-dom"
import Header from "../header"
import Footer from "../footer"

    export default function LayoutUser(){
        return(
            <>
             <Header></Header>
                <div className="d-flex">
                <div className="flex-grow-1 p-4">
                <Outlet/>
                </div>
                </div>
                <Footer></Footer>
                </>
        )


    }