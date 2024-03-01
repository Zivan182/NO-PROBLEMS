import React, { useState } from "react";
import "./styles/DropDown.css"

const arrowUp = "/images/arrow_up.png";
const arrowDown = "/images/arrow_down.png";

export function DropDown(props: any) {
    const [maxHeightValue, setMaxHeightValue] = useState("0px");
    const [overflowValue, setOverflowValue] = useState("hidden");
    const [arrowDirection, setArrowDirection] = useState(arrowDown);

    const DropDownClick = (event:any) => {
        event.preventDefault();
        setMaxHeightValue(maxHeightValue == "0px" ? "1000px" : "0px"); 
        if (props.overflow) {
            setOverflowValue(overflowValue == "hidden" ? props.overflow : "hidden");
        }
        setArrowDirection(arrowDirection == arrowDown ? arrowUp : arrowDown); 
        
    };
    
    return (
        <div>
            <button className={"dropdown-button" + " " + props.button_class} onClick={DropDownClick}>
                {props.text} 
                <img className="arrow-img" src={arrowDirection}></img>
            </button>
            <div className="dropdown-content" style={{maxHeight: maxHeightValue, overflow: overflowValue}}>
                {props.content}
            </div>
        </div>
    );
}