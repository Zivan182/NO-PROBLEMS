import React, { useState } from "react";
import "./styles/SignUpForm.css"
import { Link } from "react-router-dom";

export function SignUpForm(props : any) {
    const [mode, setMode] = useState(props.mode ? props.mode : "create");

    const renderForm = (
        <div className="form">
            <form>
                <div className="input-container">
                    <input type="text" placeholder="Логин" defaultValue={props.login} disabled={mode === "view"}/>
                </div>
                <div className="input-container">
                    <input type="text" placeholder="Почта" defaultValue={props.email} disabled={mode === "view"}/>
                </div>
                <div className="input-container">
                    <input type="text" placeholder="Имя" defaultValue={props.name} disabled={mode === "view"}/>
                </div>
                <div className="input-container">
                    <input type="text" placeholder="Фамилия" defaultValue={props.surname} disabled={mode === "view"}/>
                </div>
                {mode != "view" &&
                <div className="input-container">
                    <input type="password" placeholder={mode === "edit" ? "Новый пароль" : "Пароль"}/>
                </div>}
                {mode != "view" &&
                <div className="input-container">
                    <input type="password" placeholder="Повторите пароль"/>
                </div>}
                {mode != "view" &&
                <div className="button-container">
                    <input type="submit" value={mode === "edit" ? "Сохранить" : "Зарегистрироваться"}/>
                </div>}
            </form>
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