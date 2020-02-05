# -*- coding: utf-8 -*

from flask import Flask, render_template, request, make_response, g
from redis import Redis
import os
import socket
import random
import json
import uuid
import sys

reload(sys)
sys.setdefaultencoding('utf-8')



option_a = os.getenv('OPTION_A', "蜂蜜烤猪肉")
option_b = os.getenv('OPTION_B', "牛肉haggis")
option_c = os.getenv('OPTION_C', "红酒炖羊腿")
option_d = os.getenv('OPTION_D', "烟熏肠")
option_e = os.getenv('OPTION_E', "蜂蜜猪肘")
option_f = os.getenv('OPTION_F', "牛肉土豆泥")


hostname = socket.gethostname()

app = Flask(__name__,static_folder='assets')

def get_redis():
    if not hasattr(g, 'redis'):
        g.redis = Redis(host="statistics_service_redis", db=0, socket_timeout=5)
    return g.redis

@app.route("/", methods=['POST','GET'])
def hello():
    voter_id = str(uuid.uuid4())
    if not voter_id:
        voter_id = hex(random.getrandbits(64))[2:-1]

    vote = None

    if request.method == 'POST':
        redis = get_redis()
        vote = request.form['vote']
        data = json.dumps({'voter_id': voter_id, 'vote': vote})
        redis.rpush('votes', data)

    resp = make_response(render_template(
        'index.html',
        option_a=option_a,
        option_b=option_b,
        option_c=option_c,
        option_d=option_d,
        option_e=option_e,
        option_f=option_f,
        hostname=hostname,
        vote=vote,
    ))
    resp.set_cookie('voter_id', voter_id)
    return resp


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80, debug=True, threaded=True)
