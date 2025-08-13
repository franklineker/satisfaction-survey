
import conecteVocAxios from '../axios';

const updateContact = async (contact) => {
    console.log(contact);

    try {
        const response = await conecteVocAxios.patch(`/contacts/${contact.id}/update`, contact, {
            headers: { "Content-Type": "application/json" }
        });
        return response.data;
    } catch (error) {
        console.log(`Error while updating contact with id <${contact.id}>::`, error.response?.data || error.message);
        console.error(`Error while updating contact with id <${contact.id}>::`, error.response?.data || error.message);
    }
}

export default updateContact;