import React from "react";
import "./styles/SupportForm.css"
import Select from 'react-select';

var supportOptions = [
    { value: '1', label: 'Восстановление пароля' },
    { value: '2', label: 'Технические проблемы на сайте' },
    { value: '3', label: 'Другое' },    
  ];

function SupportTopicPanel(props: any) {
    return(
        <div className="support-panel">
            <Select
                name={props.name}
                options={props.options}
                className="support-select-block"
                classNamePrefix="support-select"
                placeholder={props.placeholder}
            />
        </div>
    );
}

export function SupportForm() {

    const renderForm = (
        <div className="form">
            <form>
                <div className="input-container">
                    {/* <input type="text" maxLength={50} placeholder="Тема"/> */}
                    <SupportTopicPanel name="support-select" options={supportOptions} placeholder="Выберите категорию проблемы"/>
                </div>
                <div className="input-container">
                    <textarea placeholder="Опишите проблему подробно"/>
                </div>
                <div className="button-container">
                    <input type="submit" value="Отправить"/>
                </div>
            </form>
        </div>
    );
    return (
        <div>
            <div className="support-form">
                <div className="title">Поддержка</div>
                {renderForm}
            </div>
        </div>
    );
}