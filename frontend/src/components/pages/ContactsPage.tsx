import React from "react";
import "./styles/ContactsPage.css";

export function ContactsPage() {
    document.title = 'Контакты';
    return(
        <div className="contacts-page">
            <div className="contacts-form">
                <div className="title">Контакты</div>
                <div className="contacts" style={{display: "flex", flexDirection: "column", gap: "2vh"}}>
                <span>Почта: i.strogalshikov@gmail.com</span>
                <span>Телеграм: @zivan_182</span>
                </div>
            </div>
        </div>    

    );
}