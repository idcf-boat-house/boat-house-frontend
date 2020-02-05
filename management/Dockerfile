FROM node:5.11.0-slim

WORKDIR /app

RUN npm config set registry https://repo.huaweicloud.com/repository/npm/
RUN npm install -g nodemon
ADD package.json /app/package.json
RUN npm install && npm ls
RUN mv /app/node_modules /node_modules

ADD . /app

ENV PORT 80
EXPOSE 80

CMD ["node", "server.js"]
