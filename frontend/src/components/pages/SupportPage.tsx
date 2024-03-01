import React from "react";
import "./styles/SupportPage.css";
import { SupportForm } from "../SupportForm";

export function SupportPage() {
    document.title = 'Поддержка';
    return(
        <div className="support-page">
            <SupportForm />
        </div>
    );
}