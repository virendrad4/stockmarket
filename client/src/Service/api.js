import axios from 'axios';

const stocksGetUrl = 'http://localhost:8080/api/stocks';
const stockGetUrl = 'http://localhost:8080/api/stock';
const stocksUrl = 'http://localhost:8080/api/stock';

const headers = {
  'Content-Type': 'application/json',
  'Accept':'*/*',
}

export const getStocks = async (id) => {
    id = id || '';
    return await axios.get(`${stocksGetUrl}/${id}`, {
         headers: headers
    });
}
export const getStock = async (id) => {
    id = id || '';
    return await axios.get(`${stockGetUrl}/${id}`, {
         headers: headers
    });
}

export const addStock = async (stock) => {
    return await axios.post(`${stocksUrl}`, stock);
}

export const deleteStock = async (id) => {
    return await axios.delete(`${stocksUrl}/${id}`);
}

export const editStock = async (id, stock) => {
    return await axios.put(`${stocksUrl}/${id}`, stock)
}