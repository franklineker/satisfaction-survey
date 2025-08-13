import { default as conecteVocAxios } from "../axios";

const createSurvey = async (survey) => {
    console.log(survey)
    try {
        const { data } = await conecteVocAxios.post("/surveys", survey, {
            headers: {
                "Content-Type": "application/json"
            }
        });
        return data;
    } catch (e) {
        console.error("Error while creating survey:", e.response?.data || e.message);
        throw new Error(`Fail to create survey: ${e.response?.data?.message || e.message}`);
    }
};

export default createSurvey;