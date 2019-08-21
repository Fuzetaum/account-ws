FROM node:10

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY package.json /usr/src/app
COPY application.json /usr/src/app
COPY yarn.lock /usr/src/app

RUN yarn

COPY . /usr/src/app

EXPOSE 4000
CMD [ "yarn", "start"]