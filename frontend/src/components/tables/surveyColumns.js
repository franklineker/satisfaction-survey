'use client';

import { createColumnHelper } from "@tanstack/react-table";

const columnHelper = createColumnHelper();

export const surveyColumns = [
    columnHelper.accessor("title", {
        header: () => <span className="font-bold">Nome</span>,
        cell: (info) => (
            <span>{info.getValue()}</span>
        )
    }),
    columnHelper.accessor("createdAt", {
        header: () => <span className="font-bold">Criado Em</span>,
        cell: info => {
            const date = new Date(info.getValue()).toLocaleDateString();
            return <span className="font-light text-gray-600">{date}</span>;
        }
    }),
    columnHelper.accessor("status", {
        header: () => <span className="font-bold">Status</span>,
        cell: info => (
            <span>{info.getValue()}</span>
        )
    }),
]

// {
//     accessorKey: "title",
//     header: "Nome",
// },
// {
//     accessorKey: "created_at",
//     header: "Criado Em",
// },
// {
//     accessorKey: "status",
//     header: "Status",
// }