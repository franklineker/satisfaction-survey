
import conecteVocAxios from '../axios';

const createContact = async (contact) => {
    console.log(contact);

    try {
        const response = await conecteVocAxios.post(`/contacts`, contact, {
            headers: { "Content-Type": "application/json" }
        });
        return response.data;
    } catch (error) {
        console.log(`Error while creatting contact with id <${contact}>::`, error.response?.data || error.message);
        console.error(`Error while creatting contact with id <${contact}>::`, error.response?.data || error.message);
    }
}

export default createContact;