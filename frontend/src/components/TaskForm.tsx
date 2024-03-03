import React, { useState } from "react";
import "./styles/TaskForm.css";
import Select from 'react-select';
import { TaskButtonPanel } from "./TaskButtonsPanel";
import { isLoggedIn, requestToServer } from "../services/UserService";


var topicsOptions = [
    { value: 'Алгебра', label: 'Алгебра' },
    { value: 'ТЧ', label: 'ТЧ' },
    { value: 'Геометрия', label: 'Геометрия' },
    { value: 'Графы', label: 'Графы' },
    { value: 'Доски', label: 'Доски' },
    { value: 'Игры и стратегии', label: 'Игры и стратегии' },
    { value: 'Комбинаторная геометрия', label: 'Комбинаторная геометрия' },
    { value: 'Комбинаторика (прочее)', label: 'Комбинаторика (прочее)' }
    
  ];

  var olympiadOptions = [
    { value: 'Региональный этап ВСОШ', label: 'Региональный этап ВСОШ' },
    { value: 'Заключительный этап ВСОШ', label: 'Заключительный этап ВСОШ' },
    { value: 'УТЮМ', label: 'УТЮМ' },
    { value: 'ЮМТ', label: 'ЮМТ' },
    { value: 'Кубок памяти А.Н. Колмогорова', label: 'Кубок памяти А.Н. Колмогорова' },
    
  ];

function getOnSelectChange(setValue : any) {
    return (
        (e : any) => {
            setValue(e.value);}
    );
}


function SelectPanel(props: any) {
    return(
        <div className="task-select-panel">
            <Select
                isDisabled={props.disabled}
                name={props.name}
                options={props.options}
                className="select-block"
                classNamePrefix="select"
                placeholder={props.placeholder}
                onChange={getOnSelectChange(props.setSelectedValue)}
                defaultValue={props.defaultValue ? {value: props.defaultValue, label: props.defaultValue} : null}
                styles={{
                    control: (baseStyles) => ({
                      ...baseStyles,
                      backgroundColor: 'white',
                      border: '1px solid rgba(0, 0, 0, 0.2)',
                    }),
                    placeholder: (baseStyles) => ({
                        ...baseStyles,
                        visibility: 'hidden',
                      }),
                    singleValue: (baseStyles) => ({
                        ...baseStyles,
                        color: 'black',
                      }),
                  }}
            />
        </div>
    );
}

export function TaskForm(props:any) {
    const [id, setId] = useState(props.id);
    const [isLoaded, setIsLoaded] = useState(false);

    const [condition, setCondition] = useState(props.condition);
    const [solution, setSolution] = useState(props.solution);
    const [topic, setTopic] = useState(props.topic);
    const [olympiad, setOlympiad] = useState(props.olympiad);
    const [complexity, setComplexity] = useState(props.complexity);
    const [year, setYear] = useState(props.year);
    const [grade, setGrade] = useState(props.grade);
    const [author, setAuthor] = useState(props.author);

    const [errorMessage, setErrorMessage] = useState("");

    const handleSubmit = async (event:any) => {
        event.preventDefault();

        if (condition == "") {
            setErrorMessage("Введите условие");
            return;
        }

        if (topic == "") {
            setErrorMessage("Выберите тему");
            return;
        }

        const taskData = id ? {
            id: id,
            condition: condition,
            solution: solution,
            topic: topic,
            olympiad: olympiad,
            complexity: complexity,
            year: year,
            grade: grade,
            author: author,
        } :
        {
            condition: condition,
            solution: solution,
            topic: topic,
            olympiad: olympiad,
            complexity: complexity,
            year: year,
            grade: grade,
            author: author,
        }

        const method = id ? "put" : "post";
        const url = id ? "/tasksinfo/" + `${id}` + "/edit" : "/tasksinfo/add";

        requestToServer(method, url, JSON.stringify(taskData))
        .then((v) => {  if(v.status >= 400) {
                            window.localStorage.removeItem("jwtToken");
                            window.location.href = "/login"; 
                            return null;
                        } 
                        else 
                            window.location.reload()
        })  
    };


    return(
        <div className="task-form">
        <div className="upper-part">
            {props.disabled ? "Задача " + props.id : (props.id ? "Редактировать задачу " + props.id : "Добавить задачу")}
        </div>
        <div className="lower-part">
                <div className="task-big-data">
                    Условие
                    <textarea defaultValue={props.condition} disabled={props.disabled} onChange={(e)=>setCondition(e.target.value)}/>
                </div>
                {props.disabled && isLoggedIn() &&
                <TaskButtonPanel {...props}/>}

                <div className="task-big-data">
                    Решение
                    <textarea defaultValue={props.solution} disabled={props.disabled} onChange={(e)=>setSolution(e.target.value)}/>
                </div>


                    <div style={{display: "flex", gap: "3vw"}}>
                        <div className="task-data">
                            Тема
                            <SelectPanel name="topic-select" options={topicsOptions} placeholder="Выберите тему" defaultValue={props.topic} disabled={props.disabled} setSelectedValue={setTopic}/>
                        </div>
                        <div className="task-data">
                            Олимпиада
                            <SelectPanel name="olympiad-select" options={olympiadOptions} placeholder="Выберите олимпиаду" defaultValue={props.olympiad} disabled={props.disabled} setSelectedValue={setOlympiad}/>
                        </div>
                        <div className="task-data">
                            Автор 
                            <input type="text" defaultValue={props.author} disabled={props.disabled} onChange={(e)=>setAuthor(e.target.value)}/>
                        </div>
                    </div>
                    <div style={{display: "flex", gap: "3vw", position: "relative"}}>
                        <div className="task-small-data">
                            Сложность
                            <input type="number" min="1" max="10" placeholder="от 1 до 10" defaultValue={props.complexity} disabled={props.disabled} onChange={(e)=>setComplexity(e.target.value)}/>
                        </div>
                        <div className="task-small-data">
                            Год
                            <input type="number" min="2000" max="2024" defaultValue={props.year} disabled={props.disabled} onChange={(e)=>setYear(e.target.value)}/>
                        </div>
                        <div className="task-small-data">
                            Класс
                            <input type="number" defaultValue={props.grade} disabled={props.disabled} onChange={(e)=>setGrade(e.target.value)}/>
                        </div>
                        {props.disabled != true &&
                        <div className="button-container">
                            <button className="task-submit-button" onClick={handleSubmit}>{props.id ? "Сохранить" : "Добавить"}</button>
                        </div>}
                    </div> 
                    {props.disabled != true && errorMessage != "" &&
                    <span className="error">{errorMessage}</span>}   
            </div>
        </div>
    );
}