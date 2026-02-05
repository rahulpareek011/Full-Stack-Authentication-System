import { useContext } from "react";
import { AppContext } from "../context/AppContext";


const Header = () => {
    const {userData} = useContext(AppContext);
    return(
        <div className="text-center d-flex flex-column align-items-center justify-content-center py-5 px-3" style={{minHeight:"80vh"}}>
            <img src="https://img.freepik.com/free-vector/access-control-system-abstract-concept_335657-3180.jpg?t=st=1769926492~exp=1769930092~hmac=9409d17d2c6d5bbbf1ed4c151d5cfba76191db5845947a3423c4c40f6b196f42&w=1480" alt="header" width={180} className="mb-4"/>

            <h5 className="fw-semi-bold">
                Hey {userData ? userData.name : 'Developer'} <span role="img" aria-label="wave">👋</span>
            </h5>
            <h2 className="fw-bold display-5 mb-3">Welcome to our product</h2>
            <p className="text-muted fs-5 mb-4" style={{maxWidth:"400px"}}>
                Let's start with a quick product tour and you can setup the authentication in no time!
            </p>

            <button className="btn btn-outline-dark round-pill px-2 py-2">
                Get Started
            </button>
        </div>
    )
}

export default Header;