from flask import Flask, redirect, url_for, request
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
    return redirect(url_for('resp_latest'))


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000, debug=True)
