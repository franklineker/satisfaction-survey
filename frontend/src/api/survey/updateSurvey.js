
import { default as conecteVocAxios } from "../axios";

const updateSurvey = async (surveyPayload) => {
    console.log("upate survey payload::", surveyPayload)
    try {
        const { data } = await conecteVocAxios.patch(`/surveys/${surveyPayload.id}/update`, surveyPayload, {
            headers: {
                "Content-Type": "application/json"
            }
        });
        return data;
    } catch (e) {
        console.error("Error while updating survey:", e.response?.data || e.message);
        throw new Error(`Fail to update survey: ${e.response?.data?.message || e.message}`);
    }
};

export default updateSurvey;