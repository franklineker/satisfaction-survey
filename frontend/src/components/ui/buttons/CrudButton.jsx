"use client";

import { cn } from '@/lib/utils';
import { PlusCircle, Pencil } from 'lucide-react';
import { useRouter } from 'next/navigation';

export default function CrudButton({ className, ...props }) {

    let icon;
    let route;
    const router = useRouter();

    if (props.type === "edit") {
        icon = <Pencil className='w-3 h-3 ml-1 self-center' />;
        if (props.surveyid) {
            route = `/surveys/${props.surveyid}/update`;
        } else if (props.campaignid) {
            route = `/campaigns/${props.campaignid}/update`;
        } else if (props.contactid) {
            route = `/contacts/${props.contactid}/update`;
        }
    } else if (props.type === "create") {
        icon = <PlusCircle className='w-3 h-3 ml-1 self-center' />
        if (props.kind === "survey") {
            route = `/surveys/create`;
        } else if (props.kind === "campaign") {
            route = `/campaigns/create`;
        } else if (props.kind === "contact") {
            route = `/contacts/create`;
        }
    }

    const handleClick = (e) => {
        e.preventDefault();
        console.log("clicou", route)
        router.push(route);
    }

    return (
        <button
            className={cn("flex text-sm text-white border border-2 rounded-md border-black bg-gray-800 p-1 w-[60px] h-[30px] hover:cursor-pointer", className)}
            {...props}
            type='button'
            onClick={handleClick}
        >
            <span className='text-[12px] self-center'>{props.name}</span>{icon}
        </button>
    )
}