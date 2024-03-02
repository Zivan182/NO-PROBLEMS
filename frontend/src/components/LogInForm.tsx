import React, { useState } from "react";
import "./styles/LogInForm.css"
import { Link, useNavigate } from "react-router-dom";
import { requestToServer } from "../services/UserService";

export function LogInForm() {
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");


    const [isError, setIsError] = useState(false);


    
    const handleSubmit = async (event:any) => {
        event.preventDefault();

        const loginData = {
            login: login,
            password: password
        }

        requestToServer("post", "/users/login", JSON.stringify(loginData), false)
        .then((v) => {  if(v.status >= 400) {
                            setIsError(true); 
                            return null;
                        } 
                        else 
                            return v.json();
        })  
        .then((v) => {  if (v == null) return;
                        window.localStorage.setItem("jwtToken", v.token);
                        window.location.href = "/account";
        })
      
    };


    const renderForm = (
        <div className="form">
                <div className="input-container">
                    <input type="text" placeholder="Логин" onChange={(e)=>{setLogin(e.target.value); if (isError) setIsError(false)}}/>
                </div>
                <div className="input-container">
                    <div>
                    </div>
                    <input type="password" placeholder="Пароль" onChange={(e)=>{setPassword(e.target.value); if (isError) setIsError(false)}}/>
                    <Link to="/support" className="small-link">Забыли пароль?</Link>
                </div>
                {isError &&
                    <span className="error">Неправильные данные входа</span>}
                <div className="button-container">
                    <button className="login-button" onClick={handleSubmit}>Войти</button>
                </div>
        </div>
    );
    return (
        <div className="log-in-page-content">
            <div className="login-form">
                <div className="title">Вход</div>
                {renderForm}
            </div>
            <div>
                Ещё нет аккаунта? <Link to="/signup" className="small-link">Зарегистрироваться</Link>
            </div>
        </div>
    );
}