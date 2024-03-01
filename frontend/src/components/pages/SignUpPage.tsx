import React from "react";
import { SignUpForm } from "../SignUpForm";
import "./styles/SignUpPage.css";

export function SignUpPage() {
    document.title = 'Регистрация';
    return(
        <div className="sign-up-page">
            <SignUpForm />
        </div>
    );
}