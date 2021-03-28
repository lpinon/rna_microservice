import json
import requests

API_URL = 'http://localhost:8080/decode'


class APIClient:
    @staticmethod
    def send_str_to_decoder(input_string, session_id):
        data_to_send = {"data": input_string, "id": session_id}
        result = requests.post(API_URL, json=data_to_send)
        print(data_to_send)
        print(result.json())
        return result.json()
