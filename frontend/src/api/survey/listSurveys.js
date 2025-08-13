import conecteVocAxios from "../axios";


/** 
 * Function to list surveys with pagination.
 * 
 * This function makes a request to the backend, passing the `page` and `size` parameters to fetch paginated surveys.
 * 
 * @param {number} page - The page number to retrieve (0-based index).
 * @param {number} size - The number of elements per page (page size).
 * 
 * @returns {Promise<Object>} Returns a promise with the response data, which includes the list of surveys and pagination metadata.
 */
const listSurveys = async (page, size) => {
    try {
        const response = await conecteVocAxios.get(`/surveys?pageNumber=${page}&pageSize=${size}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching surveys:", error);
        throw error;
    }
};

export default listSurveys;
