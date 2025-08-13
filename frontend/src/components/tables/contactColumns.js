'use client';

import { createColumnHelper } from "@tanstack/react-table";

const columnHelper = createColumnHelper();

export const contactColumns = [
    columnHelper.accessor("fullName", {
        header: () => <span className="font-bold">Nome</span>,
        cell: (info) => (
            <span>{info.getValue()}</span>
        )
    }),
    columnHelper.accessor("phoneNumber", {
        header: () => <span className="font-bold">Telefone</span>,
        cell: info => {
            return <span className="font-light text-gray-600">{info.getValue()}</span>;
        }
    }),
    columnHelper.accessor("taxNumber", {
        header: () => <span className="font-bold">CPF</span>,
        cell: info => {
            return <span className="font-light text-gray-600">{info.getValue()}</span>;
        }
    }),
    columnHelper.accessor("email", {
        header: () => <span className="font-bold">Email</span>,
        cell: info => (
            <span>{info.getValue()}</span>
        )
    }),
]