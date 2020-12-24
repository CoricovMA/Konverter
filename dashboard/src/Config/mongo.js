const MongoClient = require('mongodb').MongoClient;
const uri = "mongodb+srv://main:<password>@cluster0.sl64j.mongodb.net/<dbname>?retryWrites=true&w=majority";
const client = new MongoClient(uri, { useNewUrlParser: true });

export default client;

client.connect(err => {
    const collection = client.db("test").collection("devices");
    // perform actions on the collection object
    client.close();
});
