import React from "react";
import { TaskListItem } from "./TasksListItem";
import "./styles/TasksList.css"


export function TasksList(props:any) {
    
    return (
        <div className="tasks-list">
            {props.propsList.map((taskProps:any) => <TaskListItem {...taskProps} />)}
        </div>
 
    );
}