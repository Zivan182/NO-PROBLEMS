import React, { useEffect } from "react";
import "./styles/MainPage.css"
import Aos from "aos";
import "aos/dist/aos.css";
import { Link } from "react-router-dom";

const solved = "/images/solved.png";
const liked = "/images/liked.png";
const edit = "/images/edit1.png";


export function MainPage() {
        useEffect(() => {
            Aos.init();
    }, []);

    document.addEventListener("DOMContentLoaded", function() {
        setTimeout(function() { Aos.refresh(); }, 500);
    });
    // useEffect(() => {
    //     window.scrollTo(0, 0);
    // }, []);
    // useEffect(() => {
    //     document.title = 'NO PROBLEMS';
    //   }, []);
    document.title = 'NO PROBLEMS';
    var text = "Решай математические задачи \n без проблем!";
    return(
        <div className="main-page">  
            <div className="first-part">
                <img className="main-page-background" src="/images/background.jpg"></img>
                <div className="main-page-header">
                    <span data-aos-delay="500" data-aos="zoom-out-up" className="BBB">NO PROBLEMS</span>
                    <span data-aos-delay="600" data-aos="zoom-out-up" className="CCC">Решай математические задачи</span>
                    <span data-aos-delay="600" data-aos="zoom-out-up" className="CCC">без проблем!</span>
                </div>
            </div> 
            <div className="second-part">
                <span data-aos-anchor-placement="center-bottom" data-aos-delay="100" data-aos="zoom-out-up" className="AAA"><b>NO PROBLEMS</b> - ваш помощник в поиске уникальных математических задач. Добавляйте, решайте, отмечайте и улучшайте свои навыки в математике!</span>
                <div className="main-page-blocks">

                    <div data-aos-anchor-placement="top-bottom" className="main-page-block" data-aos-delay="100" data-aos="zoom-out-up">
                        <div className="main-page-block-text">
                            <span data-aos-anchor-placement="center-bottom" className="DDD" data-aos-delay="100" data-aos="zoom-out-up">Сохраняй</span>
                            <span data-aos-anchor-placement="center-bottom" className="DDD" data-aos-delay="100" data-aos="zoom-out-up">понравившиеся</span>
                            <span data-aos-anchor-placement="center-bottom" className="DDD" data-aos-delay="100" data-aos="zoom-out-up">задачи</span>
                        </div>
                        <img data-aos-anchor-placement="top-bottom" className="main-page-img" data-aos-delay="100" data-aos="zoom-out-up" src={liked}></img>
                    </div>

                    <div data-aos-anchor-placement="top-bottom" className="main-page-block" data-aos-delay="100" data-aos="zoom-out-up">
                        <div className="main-page-block-text">
                            <span data-aos-anchor-placement="center-bottom" className="DDD" data-aos-delay="100" data-aos="zoom-out-up">Отмечай</span>
                            <span data-aos-anchor-placement="center-bottom" className="DDD" data-aos-delay="100" data-aos="zoom-out-up">решенные</span>
                        </div>
                        <img data-aos-anchor-placement="top-bottom" className="main-page-img" data-aos-delay="100" data-aos="zoom-out-up" src={solved}></img>
                    </div>

                    <div data-aos-anchor-placement="top-bottom" className="main-page-block" data-aos-delay="100" data-aos="zoom-out-up">
                        <div className="main-page-block-text">
                            <span data-aos-anchor-placement="center-bottom" className="DDD" data-aos-delay="100" data-aos="zoom-out-up">Добавляй и</span>
                            <span data-aos-anchor-placement="center-bottom" className="DDD" data-aos-delay="100" data-aos="zoom-out-up">редактируй</span>
                        </div>
                        <img data-aos-anchor-placement="top-bottom" className="main-page-img" data-aos-delay="100" data-aos="zoom-out-up" src={edit}></img>
                    </div>
                </div>
            </div> 
            <div className="third-part">
                <div className="third-part-header">
                    <span data-aos-delay="500" data-aos="zoom-out-up" className="BBB">Задачи ждут тебя!</span>
                    <span data-aos-delay="600" data-aos="zoom-out-up" className="CCC">Хватит сомневаться.</span>
                </div>
                <div className="third-part-buttons">
                    <Link data-aos-anchor-placement="bottom-bottom" data-aos-delay="500" data-aos="zoom-out-up" to="/tasks" className="VVV"><button className="third-part-button">НАЧАТЬ РЕШАТЬ</button></Link>
                    <Link data-aos-anchor-placement="bottom-bottom" data-aos-delay="500" data-aos="zoom-out-up" to="/signup" className="VVV"><button className="third-part-button">ЗАРЕГИСТРИРОВАТЬСЯ</button></Link>
                </div>
            </div> 
        </div>
    );
}