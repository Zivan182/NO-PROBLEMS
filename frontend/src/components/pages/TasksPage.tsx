import React, { useEffect, useState } from "react";
import { TasksList } from "../TasksList";
import "./styles/TasksPage.css"
import { FilterBar } from "../FilterBar";
import { useSearchParams } from "react-router-dom";
import { requestToServer } from "../../services/UserService";

const PropsList = [
    {
        id: "1",
        complexity: "1",
        condition: "В каждой клетке шахматной доски стоит фишка. Каждым ходом выбираются произвольные четыре клетки на пересечении двух строк и двух столбцов, на которых стоят фишки, после чего одна из фишек снимается с доски. Какое наибольшее число фишек можно снять с доски, соблюдая эти правила?",
    },
    {
        id: "2",
        complexity: "2",
        condition: "В стране Центумии некоторые пары городов соединены дорогами, причём из каждого города выходит ровно 100 дорог. Пучком называется набор из 10 дорог, выходящих из одного города. Докажите, что все дороги можно разбить на несколько пучков.",
    },
    {
        id: "3",
        complexity: "1",
        condition: "Назовем лабиринтом шахматную доску 8x8, где между некоторыми полями вставлены перегородки. Если ладья может обойти все поля, не перепрыгивая через перегородки, то лабиринт называется хорошим, иначе - плохим. Каких лабиринтов больше - хороших или плохих?",
    },
    {
        id: "4",
        complexity: "4",
        condition: "Каждые два из 25 городов соединены прямым рейсом одной из четырёх авиакомпаний, причём из каждого города выходит по шесть рейсов каждой авиакомпании. Докажите, что существует замкнутый маршрут из четырех рейсов одной авиакомпании.",
    },
]





export function TasksPage() {

    const [taskProps, setTaskProps] = useState([]);
    const [searchParams, setSearchParams] = useSearchParams();
    const [pageNumber, setPageNumber] = useState(searchParams.get("page") ? searchParams.get("page") : "1");
    const [filterProps, setFilterProps] = useState(getFilterProps());

    const [isLoaded, setIsLoaded] = useState(false);

    function getFilterProps() {
        return (
            {
                search: searchParams.get("search"),
                topic: searchParams.getAll("topic"),
                olympiad: searchParams.getAll("olympiad"),
                complexityfrom: searchParams.get("complexityfrom"),
                complexityto: searchParams.get("complexityto"),
                yearfrom: searchParams.get("yearfrom"),
                yearto: searchParams.get("yearto"),
                liked: searchParams.getAll("liked"),
                solved: searchParams.getAll("solved"),
                added: searchParams.getAll("added"),
            }
        );
    }

    
    useEffect(() => {
        setIsLoaded(false);
        if (!searchParams.get("page")) {
            setPageNumber("1");
        }

        setFilterProps(getFilterProps());


        requestToServer("get", "tasksinfo/search?" + searchParams)
        .then((v) => {  if(v.status >= 400) {
                            window.localStorage.removeItem("jwtToken");
                            window.location.href = "/login"; 
                            return null;
                        } 
                        else 
                            return v.json();
        })  
        .then((v) => {  if (v == null) return;
                        setTaskProps(v);            
        })
        .then(()=>{setIsLoaded(true)});

}, [searchParams]);

    function onLeftClick() {
        if (pageNumber === "1") return;
        setIsLoaded(false);
        searchParams.set("page", `${Number(pageNumber) - 1}`);
        setPageNumber(`${Number(pageNumber) - 1}`);
        setSearchParams(searchParams);

    }

    function onRightClick() {
        setIsLoaded(false);
        searchParams.set("page", `${Number(pageNumber) + 1}`);
        setPageNumber(`${Number(pageNumber) + 1}`);
        setSearchParams(searchParams);
    }



    document.title = 'Поиск задач';
    return(
        <div className="tasks-page">
            <div className="upper-part">
                Поиск задач
            </div>

            <div className="lower-part">
                <FilterBar filterProps={filterProps} applyFilters={setSearchParams}/>
                {isLoaded &&
                <TasksList propsList={taskProps}/>}
            </div>
            {isLoaded &&
            <div className="lowest-part">
                <div className="page-panel">
                    <button className={Number(pageNumber) > 1 ? "page-button" : "page-button-disabled"} onClick={onLeftClick}>
                        <img src="/images/arrow_left.png" className="page-arrow-img"></img>
                    </button>
                    <span className="page-text">Страница {pageNumber}</span>
                    <button className="page-button" onClick={onRightClick}>
                        <img src="/images/arrow_right.png" className="page-arrow-img"></img>
                    </button>
                </div>
            </div>}
        </div>
    );
}