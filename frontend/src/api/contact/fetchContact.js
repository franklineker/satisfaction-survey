
import conecteVocAxios from '../axios';

const fetchContact = async (id) => {
    try {
        const response = await conecteVocAxios.get(`/contacts/${id}`);
        return response.data;
    } catch (error) {
        console.log(`Error while fetching campaign with id <${id}>::`, error.response?.data || error.message);
        console.error(`Error while fetching campaign with id <${id}>::`, error.response?.data || error.message);
    }
}

export default fetchContact;