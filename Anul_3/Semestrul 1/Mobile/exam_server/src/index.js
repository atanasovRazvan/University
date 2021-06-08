var koa = require('koa');
var app = module.exports = new koa();
const server = require('http').createServer(app.callback());
const WebSocket = require('ws');
const wss = new WebSocket.Server({server});
const Router = require('koa-router');
const cors = require('@koa/cors');
const bodyParser = require('koa-bodyparser');

app.use(bodyParser());

app.use(cors());

app.use(middleware);

function middleware(ctx, next) {
  const start = new Date();
  return next().then(() => {
    const ms = new Date() - start;
    console.log(`${start.toLocaleTimeString()} ${ctx.request.method} ${ctx.request.url} ${ctx.response.status} - ${ms}ms`);
  });
}

app.use(async (ctx, next) => {
  await new Promise(resolve => setTimeout(resolve, 2000));
  await next();
});

const tasks = Array.from(Array(30).keys())
    .map(id => ({id, text: `task ${id}`, version: 1, tag: `tag${id % 3}`}));

const router = new Router();
router.get('/task', ctx => {
  ctx.response.body = tasks;
  ctx.response.status = 200;
});

router.get('/task/:id', ctx => {
  const id = ctx.params.id;
  const index = tasks.findIndex(t => t.id == id);
  if (index === -1) {
    ctx.response.body = {text: 'Invalid task id'};
    ctx.response.status = 404;
  } else {
    ctx.response.body = tasks[index];
    ctx.response.status = 200;
  }
});

const broadcast = (data) =>
    wss.clients.forEach((client) => {
      if (client.readyState === WebSocket.OPEN) {
        client.send(JSON.stringify(data));
      }
    });

router.put('/task/:id', async ctx => {
  const task = ctx.request.body;
  const id = parseInt(ctx.params.id);
  const index = tasks.findIndex(task => task.id === id);
  await new Promise(resolve => setTimeout(resolve, 5000));
  if (id !== task.id || index === -1) {
    ctx.response.body = { text: 'Task not found' };
    ctx.response.status = 400;
  } else if (task.version < tasks[index].version) {
    ctx.response.body = { text: 'Version conflict' };
    ctx.response.status = 409;
  } else {
    task.version++;
    tasks[index] = task;
    ctx.response.body = task;
    ctx.response.status = 200;
    broadcast(task);
  }
});

app.use(router.routes());
app.use(router.allowedMethods());

let taskIndex = -1;

setInterval(() => {
  taskIndex++;
  if (taskIndex >= tasks.length) {
    taskIndex = 0;
  }
  const task = tasks[taskIndex];
  task.version++;
  task.text = `${task.text}!`;
  console.log(`broadcast ${JSON.stringify(task)}`);
  broadcast(task);
}, 10000);

server.listen(3000);
