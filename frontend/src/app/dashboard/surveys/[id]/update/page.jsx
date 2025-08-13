import fetchSurvey from "@/api/survey/fetchSurvey";
import UpdateSurveyForm from "@/components/ui/forms/UpdateSurveyForm";


export default async function UpdateSurveyPage({ params }) {
    const { id } = await params;
    const survey = await fetchSurvey(id);

    return <UpdateSurveyForm
        initialData={survey}
    />;
}