"use client";

import { useState, useEffect } from "react";
import { useParams, useRouter } from "next/navigation";
import updateSurvey from "@/api/survey/updateSurvey";
import { QuestionType } from "@/enums/questionTypes";

export default function UpdateSurveyForm({ initialData = {}, onSubmit }) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [questions, setQuestions] = useState([]);
    const [questionsUpdate, setQuestionsUpdate] = useState([]);
    const [currentQuestionText, setCurrentQuestionText] = useState("");
    const [currentQuestionTypeCode, setCurrentQuestionTypeCode] = useState("RATING_SCALE");
    const router = useRouter();
    const currentSurveyId = useParams().id;

    useEffect(() => {
        if (initialData) {
            setTitle(initialData.title || "");
            setDescription(initialData.description || "");
            setQuestions(initialData.questions || []);
        }
    }, [initialData]);


    const handleQuestionChange = (id, field, value) => {
        setQuestions(prevQuestions =>
            prevQuestions.map(q => q.id === id ? { ...q, [field]: value } : q)
        );

        setQuestionsUpdate(prevQuestions => {
            const existing = prevQuestions.find(q => q.id === id);

            if (existing && existing[field] === value) {
                return prevQuestions;
            }

            if (!existing) {
                return [...prevQuestions, { id, [field]: value }];
            }

            return prevQuestions.map(q =>
                q.id === id ? { ...q, [field]: value } : q
            );
        });
    };

    const addQuestion = () => {
        if (currentQuestionText.trim()) {
            const newQuestion = {
                text: currentQuestionText,
                typeCode: currentQuestionTypeCode,
                is_required: true,
                display_order: questions.length + 1,
            };
            setQuestions([...questions, newQuestion]);
            setCurrentQuestionText("");
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const surveyPayload = {
            id: currentSurveyId,
            title,
            description,
            status: "ACTIVE",
            isAnonymous: true,
            questions: questionsUpdate.length ? questionsUpdate : questions,
        };

        try {
            const data = await updateSurvey(surveyPayload);
            if (onSubmit) onSubmit(data);
            router.push(`/surveys/${data.id}`);
        } catch (e) {
            console.error(e);
        }
    };

    return (
        <div className="flex flex-col max-w-[70%] mx-auto p-6 bg-gray-200 shadow rounded-lg text-[14px]">
            <h2 className="text-2xl text-black font-bold mb-2">
                Atualizar Pesquisa
            </h2>

            <form onSubmit={handleSubmit}>
                <div className="mb-2">
                    <label className="block text-black mb-1" htmlFor="title"><strong>Título</strong></label>
                    <input
                        id="title"
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        className="w-full p-2 border border-gray-400 rounded"
                        placeholder="Título da pesquisa"
                        required
                    />
                </div>

                <div className="mb-2">
                    <label className="block text-black mb-1" htmlFor="description"><strong>Descrição</strong></label>
                    <textarea
                        id="description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        className="w-full p-2 border border-gray-400 rounded"
                        placeholder="Descrição da pesquisa"
                        required
                    />
                </div>

                <div className="mb-2">
                    {questions.length > 0 && (
                        <ul>
                            {questions.map((question, index) => (
                                <li key={index} className="text-black mb-2">
                                    <p><strong>Pergunta {index + 1}:</strong></p>
                                    <textarea
                                        value={question.text}
                                        onChange={e => handleQuestionChange(question.id, "text", e.target.value)}
                                        className="w-[80%] p-2 border border-gray-500 rounded"
                                    />
                                    <label className="block text-black mb-1" htmlFor="questionType">Tipo da Pergunta</label>
                                    <select
                                        id="questionType"
                                        value={question.typeCode}
                                        onChange={(e) => handleQuestionChange(question.id, "typeCode", e.target.value)}
                                        className="w-[30%] p-2 border border-gray-400 rounded"
                                    >
                                        {Object.values(QuestionType).map(({ code, description }) => (
                                            <option key={code} value={code}>{description}</option>
                                        ))}
                                    </select>
                                </li>
                            ))}
                        </ul>
                    )}
                </div>
                <div className="flex justify-end mt-4">
                    <button
                        type="button"
                        onClick={() => router.back()}
                        className="bg-red-900 font-bold text-white p-2 mr-1 rounded-md hover:outline hover:outline-2 hover:outline-stone-900 hover:cursor-pointer"

                    >
                        Cancelar
                    </button>
                    <button
                        type="submit"
                        className="bg-violet-900 font-bold text-white p-2 rounded-md hover:outline hover:outline-2 hover:outline-stone-900 hover:cursor-pointer"

                    >
                        Atualizar Pesquisa
                    </button>
                </div>
            </form>
        </div>
    );
}
