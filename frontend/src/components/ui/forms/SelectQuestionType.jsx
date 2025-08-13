import { questionTypes } from "@/enums/questionTypes";

export default function SelectQuestionType({ question }) {
    return (
        <div>
            <label className="block text-black mb-1" htmlFor="questionType">Tipo da Pergunta</label>
            <select
                id="questionType"
                value={question.type}
                onChange={(e) => setCurrentQuestionTypeCode(e.target.value)}
                className="w-[30%] p-2 border border-gray-400 rounded"
            >
                {Object.entries(questionTypes).map(([code, type]) => (
                    <option key={code} value={code}>
                        {type}
                    </option>
                ))}
            </select>
        </div>
    )
}
