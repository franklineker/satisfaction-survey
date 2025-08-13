
import conecteVocAxios from '../axios';

const fetchCampaign = async (id) => {
    try {
        const response = await conecteVocAxios.get(`/campaigns/${id}`);
        return response.data;
    } catch (error) {
        console.log(`Error while fetching campaign with id <${id}>::`, error.response?.data || error.message);
        console.error(`Error while fetching campaign with id <${id}>::`, error.response?.data || error.message);
    }
}

export default fetchCampaign;