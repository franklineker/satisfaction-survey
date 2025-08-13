import NoThumbsDownButton from "@/components/ui/buttons/NoThumbsDownButton";
import OpenTextAnswer from "@/components/ui/OpenTextAnswer";
import RatingScaleAnswer from "@/components/ui/RatingScaleAnswer";
import YesThumbsUpButton from "@/components/ui/buttons/YesThumbsUpButton";
import fetchSurvey from "@/api/survey/fetchSurvey";
import CrudButton from "@/components/ui/buttons/CrudButton";

export default async function SurveyDetail({ params }) {

    const { id } = await params;
    const survey = await fetchSurvey(id);


    if (!survey) {
        return <div>Survey não encontrada.</div>;
    }

    return (
        <div className="flex flex-col max-w-[70%] mx-auto p-6 bg-gray-200 shadow rounded-lg">
            <CrudButton name="Editar" type="edit" className="self-end" surveyid={survey.id} />
            <h2 className="text-2xl text-black font-bold mb-4">{survey?.title}</h2>
            <p className="mb-4 text-black"><span><b>Descrição:</b></span> {survey?.description}</p>
            <p className="mb-1 text-black"><span><b>Perguntas:</b></span></p>
            {survey.questions.map((question, index) => {
                switch (question.typeCode) {
                    case 2000:
                        return (
                            <div key={index} className="text-black">
                                <p>{++index}. {question.text}</p>
                                <div className="flex ml-4 justify-between w-[105px]">
                                    <YesThumbsUpButton />
                                    <NoThumbsDownButton />
                                </div>
                            </div>
                        );
                    case 2001:
                        return (
                            <div key={index} className="text-black">
                                <p>{++index}. {question.text}</p>
                                <RatingScaleAnswer />
                            </div>
                        );
                    case 2002:
                        return (
                            <div key={index} className="text-black">
                                <p>{++index}. {question.text}</p>
                                <OpenTextAnswer value={question.text} />
                            </div>
                        )
                    case 2003:
                        return (
                            <div key={index} className="text-black">
                                <p>{++index}. {question.text}</p>
                                <OpenTextAnswer value={question.text} />
                            </div>
                        )
                    case 2004:
                        return (
                            <div key={index} className="text-black">
                                <p>{++index}. {question.text}</p>
                                <OpenTextAnswer value={question.text} />
                            </div>
                        )
                }
            })}
            <div className="text-sm mt-5 text-muted-foreground self-end">
                Criada em: {new Date(survey?.createdAt).toLocaleDateString()}
            </div>
        </div>
    );
}
