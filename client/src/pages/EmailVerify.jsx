import { useContext, useEffect, useRef, useState } from "react";
import { assets } from "../assets/assets";
import { Link, useNavigate } from "react-router-dom";
import { AppContext } from "../context/AppContext";
import { toast } from "react-toastify";
import axios from "axios";

const EmailVerify = () => {
    const inputref = useRef([]);
    const [loading, setLoading] = useState(false);
    const { getUserData, isLoggedIn, userData,backendURL, isAccountVerified } = useContext(AppContext);
    const navigate = useNavigate();

    const handleChange = (e, index) => {
        const value = e.target.value.replace(/\D/g, "");
        e.target.value = value;

        if (value && index < 5) {
            inputref.current[index + 1]?.focus();
        }
    };

    const handleKeyDown = (e, index) => {
        if (e.key === "Backspace" && !e.target.value && index > 0) {
            inputref.current[index - 1]?.focus();
        }
    };

    const handlePaste = (e) => {
        e.preventDefault();

        const paste = e.clipboardData
            .getData("text")
            .replace(/\D/g, "")
            .slice(0, 6)
            .split("");

        paste.forEach((digit, i) => {
            if (inputref.current[i]) {
                inputref.current[i].value = digit;
            }
        });

        const next = paste.length < 6 ? paste.length : 5;
        inputref.current[next]?.focus();
    };

    const handleVerify = async () => {
        const otp = inputref.current.map(input => input.value).join("");
        if (otp.length!==6){
            toast.error("Please enter all 6 digits of the OTP.");
            return;
        }
        setLoading(true);
        try {
            const response = await axios.post(backendURL+"/verify-otp",{otp});
            if(response.status == 200){
                toast.success("OTP Verified Successfully!");
                getUserData();
                navigate("/");
            }else{
                toast.error("Invalid OTP");
            }
        } catch (error) {
            toast.error("Failed to verify the OTP. Please try again.");
        }finally{
            setLoading(false);
        }
    }

    useEffect(()=>{
        isLoggedIn && userData && userData.isAccountVerified && navigate('/');
    },[isLoggedIn,userData]);


    return (
        <div className="email-verify-container d-flex align-items-center justify-content-center vh-100 position-relative"
            style={{ background: "linear-gradient(90deg, #6a5af9, #8268f9)", borderRadius: "none" }}
        >
            <Link to="/" className="position-absolute top-0 start-0 p-4 d-flex align-items-center gap-2 text-decoration-none">
                <img src={assets.logo} alt="logo" height={32} width={32} />
                <span className="fs-4 fw-semibold text-light">Authify</span>
            </Link>

            <div className="p-5 rounded-4 shadow bg-white" style={{ width: "400px" }}>
                <h4 className="text-center fw-bold mb-4">Email Verify OTP</h4>
                <p className="text-center mb-4">
                    Enter the 6-Digit code sent to your mail
                </p>

                <div className="d-flex justify-content-between gap-2 mb-4 text-center text-white-50 mb-2">
                    {[...Array(6)].map((_, i) => (
                        <input
                            key={i}
                            type="text"
                            maxLength={1}
                            className="form-control text-center fs-4 otp-input"
                            ref={(el) => (inputref.current[i] = el)}
                            onChange={(e) => handleChange(e, i)}
                            onKeyDown={(e) => handleKeyDown(e, i)}
                            onPaste={handlePaste}
                        />
                    ))}
                </div>
                <button className="btn btn-primary w-100 fw-semibold" disabled={loading} onClick={handleVerify}>
                    {loading ? "Verifying..." : "Verify email"}
                </button>
            </div>
        </div>
    )
}
export default EmailVerify; 