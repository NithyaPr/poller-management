/** @jsxImportSource @emotion/react */
import React, {FC, InputHTMLAttributes} from "react";

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
    name: string;
    label: string;
    value?: string;
}

export const Input: FC<InputProps> = ({name, label, value}) => {
    return (
        <div className="input-wrapper">
            <label htmlFor={name}>{label}</label>
            <input id={name}>{value}</input>
        </div>
    );
};