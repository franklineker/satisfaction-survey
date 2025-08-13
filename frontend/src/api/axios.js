import axios from "axios";

const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;

const conecteVocAxios = axios.create({
    baseURL: BASE_URL,
    timeout: 5000,
});

export default conecteVocAxios;