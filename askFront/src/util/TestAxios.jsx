import axios from 'axios'

export default axios.create({
    headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer <accesstoken>`,
        accessToken: await getCookie('accessToken'),
      }, 
});
