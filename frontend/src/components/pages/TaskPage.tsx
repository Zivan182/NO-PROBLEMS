import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./styles/TaskPage.css"
import { Tooltip } from "react-tooltip";
import { DropDown } from "../DropDown";
import { TaskForm } from "../TaskForm";
import { requestToServer } from "../../services/UserService";


export function TaskPage() {    

    const [id, setId] = useState(useParams().id);
    const [isLoaded, setIsLoaded] = useState(false);
    const [condition, setCondition] = useState("");
    const [solution, setSolution] = useState("");
    const [topic, setTopic] = useState("");
    const [olympiad, setOlympiad] = useState("");
    const [complexity, setComplexity] = useState("");
    const [year, setYear] = useState("");
    const [grade, setGrade] = useState("");
    const [author, setAuthor] = useState("");
    const [whoAdded, setWhoAdded] = useState("");
    const [liked, setLiked] = useState(false);
    const [solved, setSolved] = useState(false);
    const [added, setAdded] = useState(false);

    document.title = 'Задача ' + id;

    function getTaskProps() {
        return (
            {
                id: id,
                condition: condition,
                solution: solution,
                topic: topic,
                olympiad: olympiad,
                complexity: complexity,
                year: year,
                grade: grade,
                author: author,
                whoAdded: whoAdded,
                liked: liked,
                solved: solved,
                added: added,
                disabled: true,
            }
        );
    }

    useEffect(() => {
        setIsLoaded(false);
        requestToServer("get", "/tasksinfo/" + `${id}`)
        .then((v) => {  if(v.status >= 400) {
                            window.localStorage.removeItem("jwtToken");
                            window.location.href = "/login"; 
                            return null;
                        } 
                        else 
                            return v.json();
        })  
        .then((v) => {  if (v == null) return;
                        setCondition(v.condition);
                        setSolution(v.solution);
                        setTopic(v.topic);
                        setOlympiad(v.olympiad);
                        setComplexity(v.complexity);
                        setYear(v.year);
                        setGrade(v.grade);
                        setAuthor(v.author);
                        setWhoAdded(v.whoAdded.login);
                        setLiked(v.liked);
                        setSolved(v.solved);
                        setAdded(v.added);
        })
        .then(()=>{setIsLoaded(true)})
}, []);
    return(
        <div className="task-page">
            {isLoaded &&
            <TaskForm {...getTaskProps()}/>}
        </div>
    );
}