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


        // const params = {
        //     method: "post",
        //     headers: {
        //       "Content-Type": "application/json",
        //     },
        //     body: JSON.stringify({ "login": login, "password": password }),
        //   }

        requestToServer("post", "/users/login", 
            JSON.stringify({ "login": login, "password": password }), false).then(
                (v) => { if(v.status >= 400) setIsError(true);
                    else v.json().then((v) => {
                        window.localStorage.setItem("jwtToken", v.token);
                        window.location.href = "/account";
                });
                }
            )
      

        // const response = await fetch("/users/login", params);
        // console.log(response.status);
        // console.log(response.json());
        // if (response.status >= 400) {
        //     setIsError(true);
        // }

        // else {
        //     const {jwtToken} = await response.json();
        //     console.log(jwtToken);
        //     window.localStorage.saveItem("jwtToken", jwtToken);
        //     window.location.href = "/";

        // }
    };


    const renderForm = (
        <div className="form">
            <form onSubmit={handleSubmit}>
                <div className="input-container">
                    {/* <label>Логин </label> */}
                    <input type="text" placeholder="Логин" onChange={(e)=>{setLogin(e.target.value); if (isError) setIsError(false)}}/>
                </div>
                <div className="input-container">
                    <div>
                        {/* <label><b>Пароль</b> </label> */}
                    </div>
                    <input type="password" placeholder="Пароль" onChange={(e)=>{setPassword(e.target.value); if (isError) setIsError(false)}}/>
                    <Link to="/support" className="small-link">Забыли пароль?</Link>
                    {isError &&
                    <span className="error">Неправильные данные входа</span>}
                </div>
                <div className="button-container">
                    <input type="submit" value="Войти"/>
                </div>
            </form>
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