const { Client } = require("pg");

const client = new Client({
  user: "postgres",
  host: "127.0.0.1",
  database: "test_db",
  password: "1234",
  port: 5432,
});

client.connect();

client.query("SELECT * FROM test", (err, res) => {
    console.log(err);
    console.log(JSON.stringify(res.rows));
    client.end();
});