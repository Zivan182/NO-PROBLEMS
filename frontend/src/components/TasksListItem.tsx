import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./styles/TaskListItem.css";
import { TaskButtonPanel } from "./TaskButtonsPanel";
import { isLoggedIn } from "../services/UserService";



export function TaskListItem(props : any) {
    const {id, complexity, condition} = props;


    return(
        <div className="task-list-item">
            <div className="task-header">
                <Link to={"/tasks/" + id} className="task-number">
                    Задача {id}
                </Link>
                <div className="task-complexity">
                    Сложность: {complexity}
                </div>
            </div>
            <div className="task-middle">
                <div className="task-condition">
                {condition}
                </div>
            </div>
            {isLoggedIn() &&
            <TaskButtonPanel {...props}/>}
        </div>

    );
}