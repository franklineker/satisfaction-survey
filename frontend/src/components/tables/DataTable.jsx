"use client";

import {
    flexRender,
    getCoreRowModel,
    useReactTable,
} from "@tanstack/react-table";

import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/tables/table";
import { useEffect, useState } from "react";
import { surveyColumns } from "./surveyColumns";
import listSurveys from "@/api/survey/listSurveys";
import CrudButton from "@/components/ui/buttons/CrudButton";
import getVisiblePageNumbers from "@/lib/utils/getVisibleNumbers";
import { useRouter } from "next/navigation";
import listCampaigns from "@/api/campaign/listCampaigns";
import listContacts from "@/api/contact/listContacts";
import { campaignColumns } from "./campaignColumns";
import { contactColumns } from "./contactColumns";


export function DataTable({ kind }) {
    const [data, setData] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);
    const [pageSize, setPageSize] = useState(12);
    const [totalPages, setTotalPages] = useState(0);

    const router = useRouter();

    const getColumns = () => {
        if (kind === "survey") {
            return surveyColumns;
        } else if (kind === "campaign") {
            return campaignColumns;
        } else if (kind === "contact") {
            return contactColumns;
        }
    }

    const table = useReactTable({
        data,
        columns: getColumns(),
        getCoreRowModel: getCoreRowModel(),
    });

    const getTableData = async (pageNumber, pageSize) => {
        console.log(kind)
        let data;
        if (kind == "survey") {
            data = await listSurveys(pageNumber, pageSize);
            console.log("Surveys page: ", data);
        } else if (kind == "campaign") {
            data = await listCampaigns(pageNumber, pageSize);
            console.log("Campaigns page: ", data);
        } else if (kind == "contact") {
            data = await listContacts(pageNumber, pageSize);
            console.log("Contacts page: ", data);
        }

        setData(data.content);
        setTotalPages(data.totalPages);
        setPageNumber(pageNumber)
    }

    const handleRedirect = (id) => {
        if (kind === "survey") {
            router.push(`/surveys/${id}`);
        } else if (kind === "campaign") {
            router.push(`/campaigns/${id}`)
        } else if (kind === "contact") {
            router.push(`/contacts/${id}`)
        }
    }

    useEffect(() => {
        getTableData(pageNumber, pageSize);
    }, [pageNumber])

    return (
        <div className="relative flex flex-col rounded-lg w-full h-full">
            <CrudButton name="Novo" type="create" kind={kind} className="mb-2 self-end" />
            <div className="overflow-y-auto max-h-[92%] bg-gray-300 rounded-lg">
                <Table >
                    <TableHeader>
                        {table.getHeaderGroups().map((headerGroup) => (
                            <TableRow key={headerGroup.id}>
                                {headerGroup.headers.map((header) => (
                                    <TableHead
                                        key={header.id}
                                        className="sticky top-0 z-10 bg-gray-400 first:rounded-tl-lg last:rounded-tr-lg"
                                    >
                                        {header.isPlaceholder
                                            ? null
                                            : flexRender(
                                                header.column.columnDef.header,
                                                header.getContext()
                                            )}
                                    </TableHead>
                                ))}
                            </TableRow>
                        ))}
                    </TableHeader>
                    <TableBody>
                        {table.getRowModel().rows?.length ? (
                            table.getRowModel().rows.map((row) => {
                                const id = row.original.id;

                                return (
                                    <TableRow
                                        key={row.id}
                                        onClick={() => handleRedirect(id)}
                                        data-state={row.getIsSelected() && "selected"}
                                        className="cursor-pointer hover:bg-sky-100 hover:text-black transition"
                                    >
                                        {row.getVisibleCells().map((cell) => (
                                            <TableCell key={cell.id}>
                                                {flexRender(cell.column.columnDef.cell, cell.getContext())}
                                            </TableCell>
                                        ))}
                                    </TableRow>
                                );
                            })
                        ) : (
                            <TableRow>
                                <TableCell colSpan={surveyColumns.length} className="h-24 text-center">
                                    No results.
                                </TableCell>
                            </TableRow>
                        )}
                    </TableBody>

                </Table>
            </div>
            <div className="flex self-center font-bold mt-2">
                <ul className="flex self-center space-x-2">
                    {getVisiblePageNumbers(pageNumber, totalPages).map((item, index) => (
                        <li key={index}>
                            {item === "..." ? (
                                <span className="px-2">...</span>
                            ) : (
                                <button
                                    className={`px-2 py-1 text-xs rounded hover:cursor-pointer ${item === pageNumber
                                        ? "bg-blue-600 text-white"
                                        : "bg-gray-500 text-white"
                                        }`}
                                    onClick={() => setPageNumber(item)}
                                >
                                    {item + 1}
                                </button>
                            )}
                        </li>
                    ))}
                </ul>
            </div>

        </div>
    );
}
