"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import updateContact from "@/api/contact/updateContact";
import createCampaign from "@/api/campaign/createCampaign";
import listCampaigns from "@/api/campaign/listCampaigns";
import listSurveys from "@/api/survey/listSurveys";
import listContacts from "@/api/contact/listContacts";

export default function CampaignForm({ initialData = null }) {
    const router = useRouter();
    const isEdit = !!initialData;

    const [formData, setFormData] = useState({
        id: "",
        title: "",
        surveyId: "",
        contactIds: [],
        attempts: 1,
        maxResponses: 100,
        statusCode: 3000,
    });

    const [contacts, setContacts] = useState([]);
    const [surveys, setSurveys] = useState([]);
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        if (initialData) {
            setFormData({
                ...initialData,
                contactIds: initialData.contacts?.map((c) => c.id) || [],
                surveyId: initialData.survey?.id || "",
            });
        }
    }, [initialData]);

    useEffect(() => {
        const fetchData = async () => {
            const [contactsRes, surveysRes] = await Promise.all([
                listContacts(0, 100),
                listSurveys(0, 100),
            ]);
            console.log(contactsRes, surveysRes)
            setContacts(contactsRes.content);
            setSurveys(surveysRes.content);
        };

        fetchData();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleMultiSelect = (e) => {
        const selected = Array.from(e.target.selectedOptions).map((opt) => opt.value);
        setFormData((prev) => ({ ...prev, contactIds: selected }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setSuccess(false);

        try {
            if (isEdit) {
                await updateContact(formData);
                router.push(`/campaigns/${initialData.id}`);
            } else {
                console.log("formData::", formData)
                const res = await createCampaign(formData);
                console.log(res);
                router.push(`/campaigns/${res.id}`);
            }

            setSuccess(true);
        } catch (error) {
            console.error("Erro ao salvar campanha:", error);
            alert("Erro ao salvar campanha");
        } finally {
            setLoading(false);
        }
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="max-w-xl mx-auto mt-8 bg-white p-6 rounded-xl shadow-md space-y-4"
        >
            <h2 className="text-2xl font-bold text-center">
                {isEdit ? "Editar Campanha" : "Criar Campanha"}
            </h2>

            <input
                name="title"
                value={formData.title}
                onChange={handleChange}
                placeholder="Título da campanha"
                required
                className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-blue-500"
            />

            <select
                name="surveyId"
                value={formData.surveyId}
                onChange={handleChange}
                required
                className="w-full px-4 py-2 border rounded-md focus:ring-2 focus:ring-blue-500"
            >
                <option value="">Selecione a pesquisa</option>
                {surveys.map((s) => (
                    <option key={s.id} value={s.id}>
                        {s.title}
                    </option>
                ))}
            </select>

            <select
                multiple
                name="contactIds"
                value={formData.contactIds}
                onChange={handleMultiSelect}
                className="w-full px-4 py-2  border rounded-md h-32 focus:ring-2 focus:ring-blue-500"
            >
                {contacts.map((c) => (
                    <option key={c.id} value={c.id}>
                        {c.fullName}
                    </option>
                ))}
            </select>

            <input
                type="number"
                name="attempts"
                value={formData.attempts}
                onChange={handleChange}
                placeholder="Tentativas"
                className="w-full px-4 py-2 border rounded-md"
            />

            <input
                type="number"
                name="maxResponses"
                value={formData.maxResponses}
                onChange={handleChange}
                placeholder="Máximo de respostas"
                className="w-full px-4 py-2 border rounded-md"
            />

            {/* <input
                type="number"
                name="statusCode"
                value={formData.statusCode}
                onChange={handleChange}
                placeholder="Status Code"
                className="w-full px-4 py-2 border rounded-md"
            /> */}

            <button
                type="submit"
                disabled={loading}
                className="w-full py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 disabled:opacity-50"
            >
                {loading ? "Salvando..." : isEdit ? "Atualizar Campanha" : "Criar Campanha"}
            </button>

            {success && (
                <p className="text-green-600 text-center font-medium">
                    {isEdit ? "Campanha atualizada com sucesso!" : "Campanha criada com sucesso!"}
                </p>
            )}
        </form>
    );
}
