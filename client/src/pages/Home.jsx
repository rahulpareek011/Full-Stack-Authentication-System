import Header from "../components/Header"
import Menubar from "../components/MenuBar"

const Home = () => {
    return (
        <div className="flex flex-col items-center justify-content-center min-vh-100">
            <Menubar/>
            <Header/>
        </div>
    )
}
export default Home