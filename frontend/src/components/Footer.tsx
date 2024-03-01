import "./styles/Footer.css"
import React from "react";
import { Link } from "react-router-dom";


export function Footer() {
    return (
        <div className="footer">
            <Link to="/support" className="small-link"> Поддержка </Link>
            <Link to="/contacts" className="small-link"> Контакты </Link>
        </div>
    );
}