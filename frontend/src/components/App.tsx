import React from "react";
import Favicon from "react-favicon";
import { BrowserRouter, Navigate, Route, Routes, Outlet } from "react-router-dom";
import { MainPage } from "./pages/MainPage";
import { LogInPage } from "./pages/LogInPage";
import { SignUpPage } from "./pages/SignUpPage";
import { AccountPage } from "./pages/AccountPage";
import { TasksPage } from "./pages/TasksPage";
import { TaskPage } from "./pages/TaskPage";
import { Header } from "./Header";
import { RecoveryPage } from "./pages/RecoveryPage";
import { Footer } from "./Footer";

import "./styles/App.css";
import { SupportPage } from "./pages/SupportPage";
import { ContactsPage } from "./pages/ContactsPage";
import { ScrollToTop } from "./ScrollToTop";
import { TaskAddingPage } from "./pages/TaskAddingPage";
import { TaskEditPage } from "./pages/TaskEditPage";

export function App() {
    return (
        <div className="app">
            <Favicon url="images/favicon2.ico"/>
            <BrowserRouter>
            <ScrollToTop />
            <Header />
                <Routes>
                    <Route path="/" element={<MainPage />} />
                    <Route path="/login" element={<LogInPage />} />
                    <Route path="/signup" element={<SignUpPage />} />
                    <Route path="/recovery" element={<RecoveryPage />} />
                    <Route path="/account" element={<AccountPage />} />
                    <Route path="/tasks" element={<TasksPage />} />
                    <Route path="/tasks/:id" element={<TaskPage />} />
                    <Route path="/support" element={<SupportPage />} />
                    <Route path="/contacts" element={<ContactsPage />} />
                    <Route path="/task-adding" element={<TaskAddingPage />} />
                    <Route path="/tasks/:id/edit" element={<TaskEditPage />} />
                </Routes>
            <Footer />
            </BrowserRouter>
        </div>
    );
}