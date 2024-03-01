import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./styles/AccountPage.css"
import { SignUpForm } from "../SignUpForm";
import { TaskForm } from "../TaskForm";
import { logOut } from "../../services/UserService";


const userProps = {
    login: "zivan",
    email: "i.strogalshikov@gmail.com",
    name: "Иван",
    surname: "Строгальщиков",
    mode: "view"
}

export function AccountPage() {
    document.title = 'Личный кабинет';

    const [isData, setIsData] = useState(true);
    

    var navigateTo = useNavigate();

    const exitClick = async (event:any) => {
        await logOut();
    };

    return(
        <div className="account-page">
            <div className="account-buttons">
                <div className="account-buttons-panel">
                    <span>Личный кабинет</span>
                    <button onClick={()=>setIsData(true)}>Личные данные</button>
                    <button onClick={()=>setIsData(false)}>Добавить задачу</button>
                    <button onClick={exitClick}>Выход</button>
                </div>

            </div>
            <div className="account-content">
                {isData &&
                    <SignUpForm {...userProps}/>
                }
                {!isData &&
                    <TaskForm />
                }
            </div>
        </div>
    );
}