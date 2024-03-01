import React, { useState } from "react";
import { useParams } from "react-router-dom";
import "./styles/TaskPage.css"
import { Tooltip } from "react-tooltip";
import { DropDown } from "../DropDown";
import { TaskForm } from "../TaskForm";

interface TaskListItemProps {
    id: string;
    complexity: string;
    condition: string;
    solution: string;
    topics: string;
    olympiad?: string;
    author?: string;
    added_to_the_site?: string;
    year?: string;
    grade?: string;   

}

const props = {
    id: "1",
    complexity: "1",
    condition: "В каждой клетке шахматной доски стоит фишка. Каждым ходом выбираются произвольные четыре клетки на пересечении двух строк и двух столбцов, на которых стоят фишки, после чего одна из фишек снимается с доски. Какое наибольшее число фишек можно снять с доски, соблюдая эти правила?",
    solution: "Рассмотрим двудольный граф, в котором вершины одной доли соответствуют строкам таблицы, вершины другой доли - столбцам. Рёбра в этом графе соответствуют фишкам на доске. Тогда каждым ходом мы стираем ребро из цикла длины 4. При таких операциях связность графа не нарушится. Всего в графе 16 вершин, следовательно, при любых операциях останется хотя бы 15 рёбер. Таким образом, с доски можно снять не больше, чем 64-15=49 фишек.",
    topic: "Графы",
    olympiad: "УТЮМ",
    author: "Строгальщиков И.А.",
    added_to_the_site: "zivan",
    year: "2024",
    grade: "11",
    disabled: true,

}




export function TaskPage() {

    const params = useParams();





    document.title = 'Задача ' + params.id;
    return(
        <div className="task-page">
            <TaskForm {...props}/>
        </div>
    );
}