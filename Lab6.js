const express = require('express');
const app = express();
const port = 60666;
app.use("/", express.static(__dirname+"/public"))
app.listen(port, function (error) {
    if (error) throw error
    console.log("Server created Successfully")
});


let counter=0
app.use("/time", function (req, res) {
    timeOfDayPage(res);
    });            
    function timeOfDayPage(res) {
    counter++;
    let date = new Date();
    res.write(`<html><body>Counter ${counter}`);
    res.write(`<br>Date ${date.toLocaleString()}`);
    res.end("</body></html>");
}
