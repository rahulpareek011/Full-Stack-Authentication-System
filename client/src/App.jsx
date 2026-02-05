import { ToastContainer } from 'react-toastify'
import './App.css'
import {Routes, Route } from 'react-router-dom'
import ResetPassword from './pages/ResetPassword'
import Home from './pages/Home'
import Login from './pages/Login'
import EmailVerify from './pages/EmailVerify'

function App() {

  return (
    <>
     <ToastContainer/>
     <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/email-verify' element={<EmailVerify/>}/>
        <Route path='/reset-password' element={<ResetPassword/>}/>
     </Routes>
    </>
  )
}

export default App
