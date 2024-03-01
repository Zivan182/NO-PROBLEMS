import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Tooltip } from "react-tooltip";

import "./styles/TaskButtonsPanel.css"




const solvedImg = "/images/solved.png";
const notSolvedImg = '/images/not_solved.png';

const likedImg = "/images/liked.png";
const notLikedImg = '/images/not_liked.png';



export function TaskButtonPanel(props : any) {

    const [isLiked, setIsLiked] = useState(props.isLiked);
    const [isSolved, setIsSolved] = useState(props.isSolved);

    const likedClick = (event:any) => {
        event.preventDefault();
        setIsLiked(!isLiked); 
    };

    const solvedClick = (event:any) => {
        event.preventDefault();
        setIsSolved(!isSolved); 
    };

    return(
            <div className="task-buttons-panel">
                <div className="user-reactions">

                    <button id={"like-button" + props.id} onClick={likedClick} className="liked-button">
                    <img src={isLiked ? likedImg : notLikedImg} alt='like' className={isLiked ? "liked-img" : "not-liked-img"} />
                    </button>
                    <Tooltip 
                        content={isLiked ? "Удалить из избранного" : "Добавить в избранное"} 
                        place="bottom" 
                        offset={5} 
                        anchorSelect={"#like-button" + props.id}
                        className="tooltip" 
                        classNameArrow="tooltip-arrow"
                    />

                    <button id={"solved-button" + props.id} onClick={solvedClick} className="solved-button">
                        <img src={isSolved ? solvedImg : notSolvedImg} alt='solve' className={isSolved ? "solved-img" : "not-solved-img"} />
                    </button>
                    <Tooltip 
                        content={isSolved ? "Отметить нерешённой" : "Отметить решённой"} 
                        place="bottom" 
                        offset={5} 
                        anchorSelect={"#solved-button" + props.id}
                        className="tooltip" 
                        classNameArrow="tooltip-arrow"
                    />
                </div>

                <Link to={"/tasks/" + props.id + "/edit"}><button id={"edit-button" + props.id} className="edit-button">
                <img src={"/images/edit1.png"} alt='edit' className="edit-img" />
                </button></Link>
                <Tooltip 
                    content="Редактировать" 
                    place="bottom" 
                    offset={5} 
                    anchorSelect={"#edit-button" + props.id}
                    className="tooltip" 
                    classNameArrow="tooltip-arrow"
                />
            </div>
    );
}