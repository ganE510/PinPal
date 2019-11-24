from flask import Flask, redirect, url_for, request
from firebase_admin import messaging
import json
import DAO
import datetime

app = Flask(__name__)  # 创建一个wsgi应用


@app.route('/latest', methods=['POST', 'GET'])  # 添加路由
def resp_latest():
    user_id = request.form['user_id']
    print(user_id)
    latest = DAO.get_latest(DAO.get_auth_pins(int(user_id)))
    return json.dumps(latest)


@app.route('/update', methods=['POST', 'GET'])  # 添加路由
def update_new():
    pin_id = 123456
    dt = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    gps = "aa234-nw456"
    DAO.update_latest(pin_id, dt, gps)


def send_to_token():
    # [START send_to_token]
    # This registration token comes from the client FCM SDKs.
    registration_token = 'YOUR_REGISTRATION_TOKEN'

    # See documentation on defining a message payload.
    message = messaging.Message(
        data={
            'score': '850',
            'time': '2:45',
        },
        token=registration_token,
    )

    # Send a message to the device corresponding to the provided
    # registration token.
    response = messaging.send(message)
    # Response is a message ID string.
    print('Successfully sent message:', response)
    # [END send_to_token]
    return redirect(url_for('resp_latest'))


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000, debug=True)

