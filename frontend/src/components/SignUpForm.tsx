import React, { useState } from "react";
import "./styles/SignUpForm.css"
import { Link } from "react-router-dom";
import { requestToServer } from "../services/UserService";

export function SignUpForm(props : any) {
    const [mode, setMode] = useState(props.mode ? props.mode : "create");
    const [login, setLogin] = useState(props.login);
    const [email, setEmail] = useState(props.email);
    const [name, setName] = useState(props.name);
    const [surname, setSurname] = useState(props.surname);
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");


    const handleSubmit = async (event:any) => {
        event.preventDefault();

        if (login == "" || email == "" || name == "" || surname == "") {
            setErrorMessage("Введите недостающие личные данные");
            return;
        }
        if (password.length <= 6) {
            setErrorMessage("Пароль слишком короткий");
            return; 
        }

        if (password != repeatPassword) {
            setErrorMessage("Пароли не совпадают");
            return; 
        }

        const signupData = {
            login: login,
            email: email,
            name: name,
            surname: surname,
            password: password
        }
        const method = (mode == "create" ? "post" : "put");
        const url = (mode == "create" ? "/users/signup" : "/users/data/edit");
        const addToken = (mode == "create" ? false : true);

        requestToServer(method, url, JSON.stringify(signupData), addToken)
        .then((v) => {  if(v.status >= 400) {
                            setErrorMessage("Такой логин уже занят"); 
                            return null;
                        } 
                        if (mode == "edit") {
                            window.location.href = "/account";
                            return null;
                        } 
                        
                        return v.json();
        })  
        .then((v) => {
                    if (v == null) return;
                    window.localStorage.setItem("jwtToken", v.token);
                    window.location.href = "/account";
        })
    };

    const renderForm = (
        <div className="form">
                <div className="input-container">
                    <input type="text" placeholder="Логин" defaultValue={props.login} disabled={mode === "view"} onChange={(e)=>{setLogin(e.target.value); if (errorMessage === "Введите недостающие личные данные" || errorMessage === "Такой логин уже занят") setErrorMessage("")}}/>
                </div>
                <div className="input-container">
                    <input type="text" placeholder="Почта" defaultValue={props.email} disabled={mode === "view"} onChange={(e)=>{setEmail(e.target.value); if (errorMessage === "Введите недостающие личные данные") setErrorMessage("")}}/>
                </div>
                <div className="input-container">
                    <input type="text" placeholder="Имя" defaultValue={props.name} disabled={mode === "view"} onChange={(e)=>{setName(e.target.value); if (errorMessage === "Введите недостающие личные данные") setErrorMessage("")}}/>
                </div>
                <div className="input-container">
                    <input type="text" placeholder="Фамилия" defaultValue={props.surname} disabled={mode === "view"} onChange={(e)=>{setSurname(e.target.value); if (errorMessage === "Введите недостающие личные данные") setErrorMessage("")}}/>
                </div>
                {mode != "view" &&
                <div className="input-container">
                    <input type="password" placeholder={mode === "edit" ? "Новый пароль" : "Пароль"} defaultValue={props.password} onChange={(e)=>{setPassword(e.target.value); if (errorMessage === "Пароль слишком короткий" || errorMessage === "Пароли не совпадают") setErrorMessage("")}}/>
                </div>}
                {mode != "view" &&
                <div className="input-container">
                    <input type="password" placeholder="Повторите пароль" defaultValue={props.password} onChange={(e)=>{setRepeatPassword(e.target.value); if (errorMessage === "Пароли не совпадают") setErrorMessage("")}}/>
                </div>}
                {mode != "view" && errorMessage != "" &&
                    <span className="error">{errorMessage}</span>}
                {mode != "view" &&
                <div className="button-container">
                    <button className="signup-button" onClick={handleSubmit}>{mode === "edit" ? "Сохранить" : "Зарегистрироваться"}</button>
                </div>}
        </div>
    );
    return (
        <div className="sign-up-page">
            <div className="signup-form">
                <div className="signup-header">
                    <span className="title">{mode === "view" ? "Личные данные" : (mode === "edit" ? "Изменение данных" : "Регистрация")}</span>
                    {(mode === "view" || mode === "edit") &&
                    <button className="data-edit-button" onClick={()=>setMode(mode === "view" ? "edit" : "view")}>
                        <img className="data-edit-img" src={mode === "view" ? "/images/edit1.png" : "/images/exit.png"}></img>
                    </button>}
                </div>
                {renderForm}
            </div>
        </div>
    );
}