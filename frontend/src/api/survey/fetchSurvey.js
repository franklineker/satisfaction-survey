import { default as conecteVocAxios } from "../axios";


const fetchSurvey = async (surveyId) => {

    try {
        const { data } = await conecteVocAxios.get(`/surveys/${surveyId}`);
        return data;
    } catch (error) {
        console.error(`Error while fetching survey with id <${surveyId}>: `, error.response?.data || error.message);
        throw new Error(`Error while fetching survey with id <${surveyId}>: ${error.response?.message || error.message}`);
    }
}

export default fetchSurvey;