import { useState } from "react";

interface ServiceDetails {
    name: string;
    id: number;
    url: number;
}
export const useEditEvents = (serviceDetails: ServiceDetails) => {
    const {name} = serviceDetails
    const {id} = serviceDetails

    const {url} = serviceDetails

    return []

}