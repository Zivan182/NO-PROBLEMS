import "./styles/Header.css"
import React from "react";
import { Link } from "react-router-dom";
import { Tooltip } from "react-tooltip";
import { isLoggedIn } from "../services/UserService";


export function Header() {
    return (
        <header className="header">
            <Link to="/">
                <button className="main-button" id="my-tooltip-anchor">
                    <img src="/images/logo3.png" alt='logo' className='logo-img'></img>
                </button>
            </Link>
            <Tooltip 
                    content="Вернуться на главную" 
                    place="bottom" 
                    offset={5} 
                    anchorSelect="#my-tooltip-anchor" 
                    className="tooltip" 
                    classNameArrow="tooltip-arrow"
            />
            <div className="header-buttons">
            <Link to="/tasks"><button className="another-button">Задачи</button></Link>
            {!isLoggedIn() && <Link to="/login"><button className="another-button">Вход</button></Link>}
            {isLoggedIn() && <Link to="/account"><button className="another-button">Личный кабинет</button></Link>}
            </div>
        </header>
    );
}