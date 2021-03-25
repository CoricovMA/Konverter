FROM mhart/alpine-node:latest

COPY package.json package.json

RUN npm install -g npm@7.7.4

# Add your source files
COPY . .

RUN npm run build
RUN npm install -g serve

CMD ["npm","start"]