import "./styles/FilterBar.css"
import React, { useState } from "react";
import { DropDown } from "./DropDown";
// import Select from "react-select/dist/declarations/src/Select";
import Select from 'react-select';
import { Link, useSearchParams } from "react-router-dom";

var topicsOptions = [
    { value: 'Алгебра', label: 'Алгебра' },
    { value: 'Tеория чисел', label: 'Tеория чисел' },
    { value: 'Геометрия', label: 'Геометрия' },
    { value: 'Графы', label: 'Графы' },
    { value: 'Доски', label: 'Доски' },
    { value: 'Игры и стратегии', label: 'Игры и стратегии' },
    { value: 'Комбинаторная геометрия', label: 'Комбинаторная геометрия' },
    { value: 'Комбинаторика (прочее)', label: 'Комбинаторика (прочее)' },
    { value: 'Неравенства', label: 'Неравенства' }
    
  ];

  var olympiadOptions = [
    { value: 'Региональный этап ВСОШ', label: 'Региональный этап ВСОШ' },
    { value: 'Заключительный этап ВСОШ', label: 'Заключительный этап ВСОШ' },
    { value: 'УТЮМ', label: 'УТЮМ' },
    { value: 'ЮМТ', label: 'ЮМТ' },
    { value: 'Кубок памяти А.Н. Колмогорова', label: 'Кубок памяти А.Н. Колмогорова' },
    
  ];

//   function textChange = inputValue => { // whole object of selected option 
//     this.setState({ inputValue:inputValue.value });
// };


function getOnSelectChange(setValue : any) {
    return (
        (e : any) => {
            setValue(Array.isArray(e) ? e.map(x => x.value) : []);}
    );
}

function getOptions(v : String) {
    return (
        {
            value: v,
            label: v
        }
    );
}


function SelectPanel(props: any) {
    return(
        <div className="select-panel">
            <Select
                isMulti
                name={props.name}
                options={props.options}
                className="basic-multi-select"
                classNamePrefix="select"
                placeholder={props.placeholder}
                onChange={getOnSelectChange(props.setSelectedValue)}
                
                defaultValue={props.selectedValue ? props.selectedValue.map(getOptions):null}
            />
        </div>
    );
}


function YesNoPanel(props: any) {
    return(
        <div className="yes-no-panel">
            <label className="filter-checkbox-label">
                <input className="filter-checkbox" type="checkbox" checked={props.yesNoValue.yes.length} onChange={(event) => {var yesValue = (event.target.checked ? ["yes"] : []); props.setYesNoValue({...props.yesNoValue, yes:yesValue})}}/>
                да
            </label>
            <label className="filter-checkbox-label">
                <input className="filter-checkbox" type="checkbox" checked={props.yesNoValue.no.length} onChange={(event) => {var noValue = (event.target.checked ? ["no"] : []); props.setYesNoValue({...props.yesNoValue, no:noValue})}}/>
                нет
            </label>
        </div>
    );
}

function FromToPanel(props: any) {
    return(
        <div className="from-to-panel">
            <input type="number" className="filter-number" value={props.fromToValue.from} style={{marginRight: "5px"}} min={props.min} max={props.max} placeholder={"от " + props.min} onChange={(event) => {var fromValue = event.target.value ? [`${event.target.value}`] : []; props.setFromToValue({...props.fromToValue, from: fromValue})}} />
            <input type="number" className="filter-number" value={props.fromToValue.to} style={{marginLeft: "5px"}} min={props.min} max={props.max} placeholder={"до " + props.max} onChange={(event) => {var toValue = event.target.value ? [`${event.target.value}`] : []; props.setFromToValue({...props.fromToValue, to: toValue})}} />
        </div>
    );
}

function SearchPanel(props: any) {
    return(
        <div className="search">
        <input className="task-search-input" type="text" value={props.searchValue} placeholder="Поиск" maxLength={100} onKeyUp={props.onKeyUp} onChange={props.onChange}/>
        <button className="search-button" onClick={props.onClick}>
        <img src={"/images/search.png"} alt='search' className="search-img" />
        </button>
    </div>
    );
}




export function FilterBar(props: any) {

    const [searchValue, setSearchValue] = useState(props.filterProps.search ? [props.filterProps.search] : []);
    const [topicsValue, setTopicsValue] = useState(props.filterProps.topic);
    const [olympiadsValue, setOlympiadsValue] = useState(props.filterProps.olympiad);

    const [complexityValue, setComplexityValue] = useState({from: props.filterProps.complexityfrom ? [props.filterProps.complexityfrom] : [], to: props.filterProps.complexityto ? [props.filterProps.complexityto] : []});
    const [yearValue, setYearValue] = useState({from: props.filterProps.yearfrom ? [props.filterProps.yearfrom] : [], to: props.filterProps.yearto ? [props.filterProps.yearto] : []});


    const [likedValue, setLikedValue] = useState({yes: props.filterProps.liked.includes("yes") ? ["yes"] : [], no: props.filterProps.liked.includes("no") ? ["no"] : []});
    const [solvedValue, setSolvedValue] = useState({yes: props.filterProps.solved.includes("yes") ? ["yes"] : [], no: props.filterProps.solved.includes("no") ? ["no"] : []});
    const [myTaskValue, setMyTaskValue] = useState({yes: props.filterProps.added.includes("yes") ? ["yes"] : [], no: props.filterProps.added.includes("no") ? ["no"] : []});

    

    function onApplyClick() {
        window.scrollTo(0, 0);

        props.applyFilters({
            search: searchValue,
            topic: topicsValue,
            olympiad: olympiadsValue,
            complexityfrom: complexityValue.from,
            complexityto: complexityValue.to,
            yearfrom: yearValue.from,
            yearto: yearValue.to,
            liked: [...likedValue.yes, ...likedValue.no],
            solved: [...solvedValue.yes, ...solvedValue.no],
            added: [...myTaskValue.yes, ...myTaskValue.no],
        });
    }

    function onResetClick() {
        window.location.href = "/tasks";
    }

    const handleKeyUp = (event: React.KeyboardEvent<HTMLInputElement>) => {
        if (event.key === 'Enter') {
            onApplyClick();
        }
    };

    const onSearchChange = (event: any) => {
        event.target.value ? setSearchValue([event.target.value]) : setSearchValue([])
    };




    return (
        <div className="filter-bar">
            <SearchPanel searchValue={searchValue} onKeyUp={handleKeyUp} onChange={onSearchChange} onClick={onApplyClick}/>

            <div className="filters">
                <DropDown text="Темы" button_class="filter-button" overflow="visible" content={
                    <SelectPanel name="topics" options={topicsOptions} placeholder="Выберите темы" selectedValue={topicsValue} setSelectedValue={setTopicsValue}/>
                }/>

                <DropDown text="Олимпиада" button_class="filter-button" overflow="visible" content={
                    <SelectPanel name="olympiads" options={olympiadOptions} placeholder="Выберите олимпиады" selectedValue={olympiadsValue} setSelectedValue={setOlympiadsValue}/>
                }/>

                <DropDown text="Сложность" button_class="filter-button" content={
                    <FromToPanel min="1" max="10" fromToValue={complexityValue} setFromToValue={setComplexityValue}/>
                }/>

                <DropDown text="Год" button_class="filter-button" content={
                    <FromToPanel min="2000" max="2024" fromToValue={yearValue} setFromToValue={setYearValue}/>
                }/>

                {(window.localStorage.getItem("loggedIn") === "true") && 
                <DropDown text="В избранном" button_class="filter-button" content={
                    <YesNoPanel yesNoValue={likedValue} setYesNoValue={setLikedValue}/>                
                }/>}

                {(window.localStorage.getItem("loggedIn") === "true") &&
                <DropDown text="Решено" button_class="filter-button" content={  
                    <YesNoPanel yesNoValue={solvedValue} setYesNoValue={setSolvedValue}/>                
                }/>}

                {(window.localStorage.getItem("loggedIn") === "true") &&
                <DropDown text="Мои задачи" button_class="filter-button" content={
                    <YesNoPanel yesNoValue={myTaskValue} setYesNoValue={setMyTaskValue}/>
                }/>}
            </div>
            <div className="filter-buttons">
                <button className="apply-button" onClick={onApplyClick}>Применить</button>
                <button className="reset-button" onClick={onResetClick}>Сбросить</button>
            </div>
        </div>
    );
}