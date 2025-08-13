"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import createSurvey from "@/api/survey/createSurvey";
import { getQuestionDescriptionByCode, QuestionType } from "@/enums/questionTypes";

export default function CreateSurveyForm({ onSubmit }) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [questions, setQuestions] = useState([]);
    const [currentQuestionText, setCurrentQuestionText] = useState("");
    const [currentQuestionTypeCode, setCurrentQuestionTypeCode] = useState(2000);
    const creatorId = "9a6846e1-7043-45fb-9006-546fbd4a0a8d"// TODO: trocar por valor dinâmico

    const router = useRouter();

    const addQuestion = () => {
        if (currentQuestionText.trim()) {
            const newQuestion = {
                text: currentQuestionText,
                typeCode: currentQuestionTypeCode,
                isRequired: true,
                displayOrder: questions.length + 1,
            };
            setQuestions([...questions, newQuestion]);
            setCurrentQuestionText("");
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const surveyPayload = {
            title,
            description,
            status: "ACTIVE",
            isAnonymous: true,
            creatorId,
            questions,
        };

        try {
            const data = await createSurvey(surveyPayload);
            if (onSubmit) onSubmit(data);
            router.push(`/surveys/${data.id}`);
        } catch (e) {
            console.error(e);
        }
    };

    return (
        <div className="flex flex-col max-w-[70%] mx-auto p-6 bg-gray-200 shadow rounded-lg text-[14px]">
            <h2 className="text-2xl text-black font-bold mb-4">Criar Nova Pesquisa</h2>

            <form onSubmit={handleSubmit}>
                <div className="mb-4">
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

                <div className="mb-4">
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

                <div className="bg-gray-300 p-3 rounded mb-4">
                    <h3 className="font-bold mb-2">Perguntas</h3>
                    <div className="mb-2">
                        <label className="block text-black mb-1" htmlFor="questionText">Texto da Pergunta</label>
                        <input
                            id="questionText"
                            type="text"
                            value={currentQuestionText}
                            onChange={(e) => setCurrentQuestionText(e.target.value)}
                            className="w-full p-2 border border-gray-400 rounded"
                            placeholder="Texto da pergunta"
                        />
                    </div>
                    <div className="mb-2">
                        <label className="block text-black mb-1" htmlFor="questionType">Tipo da Pergunta</label>
                        <select
                            id="questionType"
                            value={currentQuestionTypeCode}
                            onChange={(e) => setCurrentQuestionTypeCode(e.target.value)}
                            className="w-full p-2 border border-gray-400 rounded"
                        >
                            {Object.values(QuestionType).map(({ code, description }) => (
                                <option key={code} value={code}>{description}</option>
                            ))}
                        </select>
                    </div>
                    <button
                        type="button"
                        onClick={addQuestion}
                        className="bg-blue-500 p-2 text-white font-bold rounded-md text-sm"
                    >
                        Adicionar Pergunta
                    </button>
                </div>

                {questions.length > 0 && (
                    <ul className="mb-4">
                        {questions.map((q, idx) => (
                            <li key={idx} className="text-black mb-2">
                                <p><strong>{idx + 1}.</strong> {q.text} <span className="text-gray-600">({getQuestionDescriptionByCode(q.typeCode)})</span></p>
                            </li>
                        ))}
                    </ul>
                )}

                <div className="flex justify-end">
                    <button
                        type="submit"
                        className="bg-violet-900 font-bold text-white p-2 rounded-md"
                    >
                        Criar Pesquisa
                    </button>
                </div>
            </form>
        </div>
    );
}
