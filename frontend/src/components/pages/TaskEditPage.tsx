import React, { useEffect, useState } from "react";
import "./styles/TaskAddingPage.css";
import { TaskForm } from "../TaskForm";
import { useParams } from "react-router-dom";
import { requestToServer } from "../../services/UserService";

const props = {
    id: "1",
    complexity: "1",
    condition: "В каждой клетке шахматной доски стоит фишка. Каждым ходом выбираются произвольные четыре клетки на пересечении двух строк и двух столбцов, на которых стоят фишки, после чего одна из фишек снимается с доски. Какое наибольшее число фишек можно снять с доски, соблюдая эти правила?",
    solution: "Рассмотрим двудольный граф, в котором вершины одной доли соответствуют строкам таблицы, вершины другой доли - столбцам. Рёбра в этом графе соответствуют фишкам на доске. Тогда каждым ходом мы стираем ребро из цикла длины 4. При таких операциях связность графа не нарушится. Всего в графе 16 вершин, следовательно, при любых операциях останется хотя бы 15 рёбер. Таким образом, с доски можно снять не больше, чем 64-15=49 фишек.",
    topic: "Графы",
    olympiad: "УТЮМ",
    author: "Строгальщиков И.А.",
    year: "2024",
    grade: "11",

}


export function TaskEditPage() {
    
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

    document.title = 'Редактировать задачу ' + id;

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
                disabled: false,
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
        <div className="task-adding-page">
            {isLoaded &&
            <TaskForm {...getTaskProps()}/>}
        </div>
    );
}