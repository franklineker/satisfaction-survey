
import conecteVocAxios from '../axios';

const createCampaign = async (campaign) => {
    console.log(campaign);

    try {
        const response = await conecteVocAxios.post(`/campaigns`, campaign, {
            headers: { "Content-Type": "application/json" }
        });
        return response.data;
    } catch (error) {
        console.log(`Error while creatting campaign with id <${campaign}>::`, error.response?.data || error.message);
        console.error(`Error while creatting campaign with id <${campaign}>::`, error.response?.data || error.message);
    }
}

export default createCampaign;