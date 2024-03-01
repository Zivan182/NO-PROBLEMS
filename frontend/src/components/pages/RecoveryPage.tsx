import React from "react";
import "./styles/RecoveryPage.css"

export function RecoveryPage() {
    document.title = 'Восстановление пароля';
    return(
        <div className="recovery-page">
            <h1>К сожалению, на данный момент нет автоматической системы восстановления пароля. Напишите, пожалуйста, в поддержку.</h1>
        </div>
    );
}