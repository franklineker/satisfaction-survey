"use client";

import { useState, useEffect } from "react";
import createContact from "@/api/contact/createContact";
import updateContact from "@/api/contact/updateContact";
import { useRouter } from "next/navigation";

export default function ContactForm({ initialData = null }) {
    const router = useRouter();
    const [formData, setFormData] = useState({
        id: "",
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        taxNumber: "",
    });

    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);
    const isEdit = !!initialData;

    useEffect(() => {
        if (initialData) {
            setFormData(initialData);
        }
    }, [initialData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setSuccess(false);

        try {
            let responpse;
            if (isEdit) {
                responpse = await updateContact(formData);
            } else {
                responpse = await createContact(formData);
            }

            setSuccess(true);
            if (!isEdit) {
                setFormData({
                    firstName: "",
                    lastName: "",
                    email: "",
                    phoneNumber: "",
                    taxNumber: "",
                });
            }
            router.push(`/contacts/${responpse.id}`);
        } catch (error) {
            console.error("Erro ao enviar:", error);
            alert("Erro ao salvar contato");
        } finally {
            setLoading(false);
        }
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="max-w-md mx-auto mt-8 p-6 bg-white shadow-md rounded-xl space-y-4"
        >
            <h2 className="text-2xl font-bold text-center">
                {isEdit ? "Editar Contato" : "Criar Contato"}
            </h2>

            <input
                name="firstName"
                placeholder="Nome"
                value={formData.firstName}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />

            <input
                name="lastName"
                placeholder="Sobrenome"
                value={formData.lastName}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />

            <input
                name="email"
                type="email"
                placeholder="E-mail"
                value={formData.email}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />

            <input
                name="phoneNumber"
                placeholder="Telefone"
                value={formData.phoneNumber}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />

            <input
                name="taxNumber"
                placeholder="CPF/CNPJ"
                value={formData.taxNumber}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />

            <button
                type="submit"
                disabled={loading}
                className="w-full py-2 px-4 bg-blue-600 text-white rounded-md hover:bg-blue-700 disabled:opacity-50"
            >
                {loading ? "Salvando..." : isEdit ? "Atualizar Contato" : "Criar Contato"}
            </button>

            {success && (
                <p className="text-green-600 text-center font-medium">
                    {isEdit ? "Contato atualizado com sucesso!" : "Contato criado com sucesso!"}
                </p>
            )}
        </form>
    );
}
