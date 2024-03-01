import React from "react";
import { LogInForm } from "../LogInForm";
import "./styles/LogInPage.css";

export function LogInPage() {
    document.title = 'Вход';
    return(
        <div className="log-in-page">
            <LogInForm />
        </div>
    );
}