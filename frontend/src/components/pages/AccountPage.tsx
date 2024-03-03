import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./styles/AccountPage.css"
import { SignUpForm } from "../SignUpForm";
import { TaskForm } from "../TaskForm";
import { logOut, requestToServer } from "../../services/UserService";


const userProps = {
    login: "zivan",
    email: "i.strogalshikov@gmail.com",
    name: "Иван",
    surname: "Строгальщиков",
    mode: "view"
}

export function AccountPage() {
    document.title = 'Личный кабинет';
    const [login, setLogin] = useState("");
    const [email, setEmail] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [mode, setMode] = useState("view");

    const [isData, setIsData] = useState(true);

    const [isLoaded, setIsLoaded] = useState(false);

    useEffect(() => {
        setIsLoaded(false);
        requestToServer("get", "/users/data")
        .then((v) => {  if(v.status >= 400) {
                            window.localStorage.removeItem("jwtToken");
                            window.location.href = "/login"; 
                            return null;
                        } 
                        else 
                            return v.json();
        })  
        .then((v) => {  if (v == null) return;
                        setLogin(v.login);
                        setEmail(v.email);
                        setName(v.name);
                        setSurname(v.surname);
        })
        .then(()=>{setIsLoaded(true)})
}, []);

    const exitClick = async (event:any) => {
        await logOut();
    };

    function getUserProps() {
        return (
            {
                login: login,
                email: email,
                name: name,
                surname: surname,
                mode: mode            
            }
        );
    }

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
                {isData && isLoaded &&
                    <SignUpForm {...getUserProps()}/>
                }
                {!isData &&
                    <TaskForm />
                }
            </div>
        </div>
    );
}