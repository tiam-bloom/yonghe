import axios from 'axios';
// 简单封装一个axios
const http = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 5000
});

export default http;