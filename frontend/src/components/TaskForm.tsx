import React from "react";
import "./styles/TaskForm.css";
import Select from 'react-select';
import { TaskButtonPanel } from "./TaskButtonsPanel";
import { isLoggedIn } from "../services/UserService";


var topicsOptions = [
    { value: 'Алгебра', label: 'Алгебра' },
    { value: 'Tеория чисел', label: 'Tеория чисел' },
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
    return(
        <div className="task-form">
        <div className="upper-part">
            {props.disabled ? "Задача " + props.id : (props.id ? "Редактировать задачу " + props.id : "Добавить задачу")}
        </div>
        <div className="lower-part">
                <div className="task-big-data">
                    Условие
                    <textarea defaultValue={props.condition} disabled={props.disabled}/>
                </div>
                {props.disabled && isLoggedIn() &&
                <TaskButtonPanel {...props}/>}

                <div className="task-big-data">
                    Решение
                    <textarea defaultValue={props.solution} disabled={props.disabled}/>
                </div>


                    <div style={{display: "flex", gap: "3vw"}}>
                        <div className="task-data">
                            Тема
                            <SelectPanel name="topic-select" options={topicsOptions} placeholder="Выберите тему" defaultValue={props.topic} disabled={props.disabled}/>
                        </div>
                        <div className="task-data">
                            Олимпиада
                            <SelectPanel name="olympiad-select" options={olympiadOptions} placeholder="Выберите олимпиаду" defaultValue={props.olympiad} disabled={props.disabled}/>
                        </div>
                        <div className="task-data">
                            Автор 
                            <input type="text" defaultValue={props.author} disabled={props.disabled}/>
                        </div>
                    </div>
                    <div style={{display: "flex", gap: "3vw", position: "relative"}}>
                        <div className="task-small-data">
                            Сложность
                            <input type="number" min="1" max="10" placeholder="от 1 до 10" defaultValue={props.complexity} disabled={props.disabled}/>
                        </div>
                        <div className="task-small-data">
                            Год
                            <input type="number" min="2000" max="2024" defaultValue={props.year} disabled={props.disabled}/>
                        </div>
                        <div className="task-small-data">
                            Класс
                            <input type="number" defaultValue={props.grade} disabled={props.disabled}/>
                        </div>
                        {props.disabled != true &&
                        <div className="button-container">
                            <input type="submit" value={props.id ? "Сохранить" : "Добавить"} disabled={props.disabled}/>
                        </div>}
                    </div>    
            </div>
        </div>
    );
}