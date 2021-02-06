const http = require('http')

const port = 8080;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/plain');
  res.end('Hello world!');
});

server.listen(port, '127.0.0.1', () => {
  console.log(`Server running at localhost:${port}`);
});