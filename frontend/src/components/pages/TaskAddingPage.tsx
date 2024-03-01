import React from "react";
import "./styles/TaskAddingPage.css";
import { TaskForm } from "../TaskForm";


export function TaskAddingPage() {
    document.title = 'Добавить задачу';
    return(
        <div className="task-adding-page">
            <TaskForm />
        </div>
    );
}